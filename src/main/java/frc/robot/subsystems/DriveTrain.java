// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;
import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.commands.PathfindHolonomic;
import com.pathplanner.lib.path.PathConstraints;
import com.pathplanner.lib.util.HolonomicPathFollowerConfig;
import com.pathplanner.lib.util.ReplanningConfig;
import com.swervedrivespecialties.swervelib.MkSwerveModuleBuilder;
import com.swervedrivespecialties.swervelib.MotorType;
import com.swervedrivespecialties.swervelib.SdsModuleConfigurations;
import com.swervedrivespecialties.swervelib.SwerveModule;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.estimator.SwerveDrivePoseEstimator;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.Odometry;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveDriveOdometry;
import edu.wpi.first.math.kinematics.SwerveDriveWheelPositions;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInLayouts;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.resources.Navx;

import static frc.robot.Constants.*;

import java.security.cert.TrustAnchor;

public class DriveTrain extends PIDSubsystem {

  /**
   * The maximum voltage that will be delivered to the drive motors.
   * <p>
   * This can be reduced to cap the robot's maximum speed. Typically, this is useful during initial testing of the robot.
   */
  public static final double MAX_VOLTAGE = 12.0;
  // FIXED!!!  FIXME Measure the drivetrain's maximum velocity or calculate the theoretical.
  //  The formula for calculating the theoretical maximum velocity is:
  //   <Motor free speed RPM> / 60 * <Drive reduction> * <Wheel diameter meters> * pi
  //  By default this value is setup for a Mk3 standard module using Falcon500s to drive.
  //  An example of this constant for a Mk4 L2 module with NEOs to drive is:
  //   5880.0 / 60.0 / SdsModuleConfigurations.MK4_L2.getDriveReduction() * SdsModuleConfigurations.MK4_L2.getWheelDiameter() * Math.PI
  /**
   * The maximum velocity of the robot in meters per second.
   * <p>
   * This is a measure of how fast the robot should be able to drive in a straight line.
   */
  public static final double MAX_VELOCITY_METERS_PER_SECOND = 6000.0 / 60.0 *
          SdsModuleConfigurations.MK4I_L3.getDriveReduction() *
          SdsModuleConfigurations.MK4I_L3.getWheelDiameter()* Math.PI;
  /**
   * The maximum angular velocity of the robot in radians per second.
   * <p>
   * This is a measure of how fast the robot can rotate in place.
   */
  // Here we calculate the theoretical maximum angular velocity. You can also replace this with a measured amount.
  public static final double MAX_ANGULAR_VELOCITY_RADIANS_PER_SECOND = MAX_VELOCITY_METERS_PER_SECOND /
          Math.hypot(DRIVETRAIN_TRACKWIDTH_METERS / 2.0, DRIVETRAIN_WHEELBASE_METERS / 2.0);

  private final SwerveDriveKinematics m_kinematics = new SwerveDriveKinematics(
          // Front left
          new Translation2d(DRIVETRAIN_TRACKWIDTH_METERS / 2.0, DRIVETRAIN_WHEELBASE_METERS / 2.0),
          // Front right
          new Translation2d(DRIVETRAIN_TRACKWIDTH_METERS / 2.0, -DRIVETRAIN_WHEELBASE_METERS / 2.0),
          // Back left
          new Translation2d(-DRIVETRAIN_TRACKWIDTH_METERS / 2.0, DRIVETRAIN_WHEELBASE_METERS / 2.0),
          // Back right
          new Translation2d(-DRIVETRAIN_TRACKWIDTH_METERS / 2.0, -DRIVETRAIN_WHEELBASE_METERS / 2.0)
  );

  // By default we use a Pigeon for our gyroscope. But if you use another gyroscope, like a NavX, you can change this.
  // The important thing about how you configure your gyroscope is that rotating the robot counter-clockwise should
  // cause the angle reading to increase until it wraps back over to zero.
  
  // Fixed FIXME Uncomment if you are using a NavX
  private final AHRS m_navx = new AHRS(SPI.Port.kMXP, (byte) 200); // NavX connected over MXP

  // These are our modules. We initialize them in the constructor.
  private final SwerveModule m_frontLeftModule;
  private final SwerveModule m_frontRightModule;
  private final SwerveModule m_backLeftModule;
  private final SwerveModule m_backRightModule;
  public static SwerveDriveOdometry odometer;

  private SwerveDrivePoseEstimator poseEstimator;

  private Field2d m_field;

  private double maxPIDTurnSpeed;
  private boolean onPIDTurnTarget;
  private double turnSetpoint;

  private ChassisSpeeds m_chassisSpeeds = new ChassisSpeeds(0.0, 0.0, 0.0);

  public DriveTrain() {
    super(new PIDController(Constants.DRIVETRAIN_TURN_KP, 
      Constants.DRIVETRAIN_TURN_KI, Constants.DRIVETRAIN_TURN_KD));

    ShuffleboardTab tab = Shuffleboard.getTab("Drivetrain");

    // There are 4 methods you can call to create your swerve modules.
    // The method you use depends on what motors you are using.
    //
    // Mk3SwerveModuleHelper.createFalcon500(...)
    //   Your module has two Falcon 500s on it. One for steering and one for driving.
    //
    // Mk3SwerveModuleHelper.createNeo(...)
    //   Your module has two NEOs on it. One for steering and one for driving.
    //
    // Mk3SwerveModuleHelper.createFalcon500Neo(...)
    //   Your module has a Falcon 500 and a NEO on it. The Falcon 500 is for driving and the NEO is for steering.
    //
    // Mk3SwerveModuleHelper.createNeoFalcon500(...)
    //   Your module has a NEO and a Falcon 500 on it. The NEO is for driving and the Falcon 500 is for steering.
    //
    // Similar helpers also exist for Mk4 modules using the Mk4SwerveModuleHelper class.

    // By default we will use Falcon 500s in standard configuration. But if you use a different configuration or motors
    // you MUST change it. If you do not, your code will crash on startup.
    // FIXME Setup motor configuration
    
     m_frontLeftModule = new MkSwerveModuleBuilder()
                .withLayout(tab.getLayout("Front Left Module", BuiltInLayouts.kList)
                .withSize(2, 4)
                .withPosition(0, 0))
                .withGearRatio(SdsModuleConfigurations.MK4I_L3)
                .withDriveMotor(MotorType.FALCON, Constants.FRONT_LEFT_MODULE_DRIVE_MOTOR)
                .withSteerMotor(MotorType.NEO, Constants.FRONT_LEFT_MODULE_STEER_MOTOR)
                .withSteerEncoderPort(Constants.FRONT_LEFT_MODULE_STEER_ENCODER)
                .withSteerOffset(Constants.FRONT_LEFT_MODULE_STEER_OFFSET)
                .build();
     // zero should be facing straight forward on original code.... Do follow instructions on original Readme

  
    // We will do the same for the other modules
   m_frontRightModule = new MkSwerveModuleBuilder()
                .withLayout(tab.getLayout("Front Right Module", BuiltInLayouts.kList)
                .withSize(2, 4)
                .withPosition(2, 0))
                .withGearRatio(SdsModuleConfigurations.MK4I_L3)
                .withDriveMotor(MotorType.FALCON, Constants.FRONT_RIGHT_MODULE_DRIVE_MOTOR)
                .withSteerMotor(MotorType.NEO, Constants.FRONT_RIGHT_MODULE_STEER_MOTOR)
                .withSteerEncoderPort(Constants.FRONT_RIGHT_MODULE_STEER_ENCODER)
                .withSteerOffset(Constants.FRONT_RIGHT_MODULE_STEER_OFFSET)
                .build();

    m_backLeftModule = new MkSwerveModuleBuilder()
                .withLayout(tab.getLayout("Back Left Module", BuiltInLayouts.kList)
                .withSize(2, 4)
                .withPosition(4, 0))
                .withGearRatio(SdsModuleConfigurations.MK4I_L3)
                .withDriveMotor(MotorType.FALCON, Constants.BACK_LEFT_MODULE_DRIVE_MOTOR)
                .withSteerMotor(MotorType.NEO, Constants.BACK_LEFT_MODULE_STEER_MOTOR)
                .withSteerEncoderPort(Constants.BACK_LEFT_MODULE_STEER_ENCODER)
                .withSteerOffset(Constants.BACK_LEFT_MODULE_STEER_OFFSET)
                .build();

    m_backRightModule = new MkSwerveModuleBuilder()
                .withLayout(tab.getLayout("Back Right Module", BuiltInLayouts.kList)
                .withSize(2, 4)
                .withPosition(6, 0))
                .withGearRatio(SdsModuleConfigurations.MK4I_L3)
                .withDriveMotor(MotorType.FALCON, Constants.BACK_RIGHT_MODULE_DRIVE_MOTOR)
                .withSteerMotor(MotorType.NEO, Constants.BACK_RIGHT_MODULE_STEER_MOTOR)
                .withSteerEncoderPort(Constants.BACK_RIGHT_MODULE_STEER_ENCODER)
                .withSteerOffset(Constants.BACK_RIGHT_MODULE_STEER_OFFSET)
                .build();

    m_navx.zeroYaw();
    odometer = new SwerveDriveOdometry(m_kinematics, getGyroscopeRotation(),
                new SwerveModulePosition[]{ 
                  m_frontLeftModule.getPosition(),
                  m_frontRightModule.getPosition(),
                  m_backLeftModule.getPosition(),
                  m_backRightModule.getPosition()
                }
              );
    poseEstimator = new SwerveDrivePoseEstimator(m_kinematics, getGyroscopeRotation(),
                new SwerveModulePosition[]{ 
                  m_frontLeftModule.getPosition(),
                  m_frontRightModule.getPosition(),
                  m_backLeftModule.getPosition(),
                  m_backRightModule.getPosition()
                }, new Pose2d()
              );


    m_field = new Field2d();
    SmartDashboard.putData("Field", m_field);

    
    //pathFollowerConfig = new HolonomicPathFollowerConfig(Constants.MAX_MODULE_SPEED, 
    //Constants.DRIVE_BASE_RADIUS, new ReplanningConfig());

    AutoBuilder.configureHolonomic(this::getPose2d, this::resetPose, this::getChassisSpeeds, 
    this::drive,    Constants.pathFollowerConfig, () -> {
              var alliance = DriverStation.getAlliance();
              if (alliance.isPresent()) {
                return alliance.get() == DriverStation.Alliance.Red;
              }
              return false;
            }, this);

    this.disable();
    this.setSetpoint(0);

  }

  /**
   * Sets the gyroscope angle to zero. This can be used to set the direction the robot is currently facing to the
   * 'forwards' direction.
   */
  public void zeroGyroscope() {
      m_navx.zeroYaw();
  }

  public Rotation2d getGyroscopeRotation() {
    
    if (m_navx.isMagnetometerCalibrated()) {
      // We will only get valid fused headings if the magnetometer is calibrated
      return Rotation2d.fromDegrees(m_navx.getFusedHeading());
    }

    // We have to invert the angle of the NavX so that rotating the robot counter-clockwise makes the angle increase.
   // return Rotation2d.fromDegrees(360.0 - m_navx.getYaw());
    return Rotation2d.fromDegrees(- m_navx.getYaw());
  }

  public void drive(ChassisSpeeds chassisSpeeds) {
    m_chassisSpeeds = chassisSpeeds;
  }

  public ChassisSpeeds getChassisSpeeds()
  {
    return m_chassisSpeeds;
  }


  public Pose2d getPose2d(){
    //return odometer.getPoseMeters();
    return poseEstimator.getEstimatedPosition();
  }

  public SwerveModulePosition[] getWheelPositions()
  {
    SwerveModulePosition positions[] = new SwerveModulePosition[4];
    positions[0] = m_frontLeftModule.getPosition();
    positions[1] = m_frontRightModule.getPosition();
    positions[2] = m_backLeftModule.getPosition();
    positions[3] = m_backRightModule.getPosition();

    return positions;
  }

  public void resetPose(Pose2d pose)
  {
    poseEstimator.resetPosition(getGyroscopeRotation(), getWheelPositions(), pose);
  }

  public Command pathFindToPose(Pose2d targetPose, PathConstraints constrains)
  {
    return new PathfindHolonomic(targetPose, constrains, 
    this::getPose2d, this::getChassisSpeeds, this::drive, Constants.pathFollowerConfig, this);
  }

  public Command pathFindToSpeaker(PathConstraints constraints)
  {
    return pathFindToPose(Constants.SHOOTING_POSITION, constraints);
  }




  @Override
  public void periodic() {

    //System.out.println("Chassis speeds " + m_chassisSpeeds );
    SwerveModuleState[] states = m_kinematics.toSwerveModuleStates(m_chassisSpeeds);
    SwerveDriveKinematics.desaturateWheelSpeeds(states, MAX_VELOCITY_METERS_PER_SECOND);

    m_frontLeftModule.set(states[0].speedMetersPerSecond / MAX_VELOCITY_METERS_PER_SECOND * MAX_VOLTAGE, states[0].angle.getRadians());
    m_frontRightModule.set(states[1].speedMetersPerSecond / MAX_VELOCITY_METERS_PER_SECOND * MAX_VOLTAGE, states[1].angle.getRadians());
    m_backLeftModule.set(states[2].speedMetersPerSecond / MAX_VELOCITY_METERS_PER_SECOND * MAX_VOLTAGE, states[2].angle.getRadians());
    m_backRightModule.set(states[3].speedMetersPerSecond / MAX_VELOCITY_METERS_PER_SECOND * MAX_VOLTAGE, states[3].angle.getRadians());


    SwerveModulePosition positions[] = new SwerveModulePosition[4];
    positions[0] = m_frontLeftModule.getPosition();
    positions[1] = m_frontRightModule.getPosition();
    positions[2] = m_backLeftModule.getPosition();
    positions[3] = m_backRightModule.getPosition();
    
    odometer.update(getGyroscopeRotation(),positions);
    poseEstimator.update(getGyroscopeRotation(),positions);

    double PositionX = odometer.getPoseMeters().getX();
    double PositionY = odometer.getPoseMeters().getY();
    double PositionR = getGyroscopeRotation().getDegrees();
    SmartDashboard.putNumber("X", PositionX);
    SmartDashboard.putNumber("Y", PositionY);
    SmartDashboard.putNumber("R", PositionR);
    SmartDashboard.putNumber("Odo R", poseEstimator.getEstimatedPosition().getRotation().getDegrees());

    SmartDashboard.putNumber("Navx", m_navx.getYaw());
    
    SmartDashboard.putNumber("X Speed", getChassisSpeeds().vxMetersPerSecond);
    SmartDashboard.putNumber("Y Speed", getChassisSpeeds().vyMetersPerSecond);

    if(Robot.getRobotContainer().getVision().hasPose())
    {
      var estimatedVisionPose = Robot.getRobotContainer().getVision().getEstimatedPosition();
      poseEstimator.addVisionMeasurement(estimatedVisionPose.estimatedPose.toPose2d(), estimatedVisionPose.timestampSeconds);
    }

    m_field.setRobotPose(getPose2d());

    super.periodic();
  }

  /**
   * @param speed (rad/s)
   */
  public void setMaxPIDTurnSpeed(double speed)
  {
    maxPIDTurnSpeed = speed;
  }


  @Override
  protected void useOutput(double output, double setpoint) 
  {
    output = frc.robot.resources.Math.clamp(output, -maxPIDTurnSpeed, maxPIDTurnSpeed);
    ChassisSpeeds speeds = new ChassisSpeeds(0, 0, output);

    drive(speeds);
    if(Math.abs(frc.robot.resources.Math.deltaAngle(m_navx.getYaw(), turnSetpoint)) < Constants.DRIVETRAIN_TURN_ARRIVE_OFFSET)
      onPIDTurnTarget = true;
    else 
      onPIDTurnTarget = false;

    System.out.println(frc.robot.resources.Math.deltaAngle(m_navx.getYaw(), turnSetpoint));
    
  }

  @Override
  protected double getMeasurement() 
  {
    return frc.robot.resources.Math.deltaAngle(m_navx.getYaw(), turnSetpoint);
  }

  public boolean onPIDTurnTarget()
  {
    return onPIDTurnTarget;
  }
  public void setPIDTurnSetpoint(double setpoint)
  {
    turnSetpoint = setpoint;
  }
}