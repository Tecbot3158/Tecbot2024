package frc.robot.subsystems;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.SystemMenuBar;

import com.revrobotics.CANSparkBase.IdleMode;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.RobotMap;
import frc.robot.resources.TecbotSpeedController;
import frc.robot.resources.TecbotSpeedController.TypeOfMotor;


public class RampSubsystem extends SubsystemBase {
  RobotContainer robotContainer;
  TecbotSpeedController topMotor, bottomMotor, rollersMotor;

  PIDController topPIDController;
  PIDController bottomPIDController;

  SimpleMotorFeedforward topfeedforward;
  SimpleMotorFeedforward bottomfeedforward;


  public RampSubsystem(RobotContainer rc){
   robotContainer = rc;
   
   topMotor = new TecbotSpeedController(RobotMap.shooterPorts[0],TypeOfMotor.CAN_SPARK_BRUSHLESS);
   bottomMotor = new TecbotSpeedController(RobotMap.shooterPorts[1],TypeOfMotor.CAN_SPARK_BRUSHLESS);

   rollersMotor = new TecbotSpeedController(RobotMap.intakePort[0], TypeOfMotor.VICTOR_SPX);

   topMotor.getCANSparkMax().setIdleMode(IdleMode.kCoast);
   bottomMotor.getCANSparkMax().setIdleMode(IdleMode.kCoast);
   
   topPIDController = new PIDController(Constants.SHOOTER_TOP_PID_P, 
      Constants.SHOOTER_TOP_PID_I, Constants.SHOOTER_TOP_PID_D);

   bottomPIDController = new PIDController(Constants.SHOOTER_BOTTOM_PID_P, 
      Constants.SHOOTER_BOTTOM_PID_I, Constants.SHOOTER_BOTTOM_PID_D);

   topfeedforward = new SimpleMotorFeedforward(Constants.SHOOTER_TOP_FEEDFORWARD_S, 
      Constants.SHOOTER_TOP_FEEDFORWARD_V);
   bottomfeedforward = new SimpleMotorFeedforward(Constants.SHOOTER_BOTTOM_FEEDFORWARD_S, 
      Constants.SHOOTER_BOTTOM_FEEDFORWARD_V);

  }
    

    @Override
  public void periodic() {
    // This method will be called once per scheduler run

    SmartDashboard.putBoolean("Is Auto", DriverStation.isAutonomous() );
    if(!DriverStation.isAutonomous()){
      rollersMotor.set(Math.min(1,robotContainer.getCopilot().getRightTriggerAxis() + robotContainer.getPilot().getRightTriggerAxis())-Math.min(1,robotContainer.getPilot().getLeftTriggerAxis() + robotContainer.getCopilot().getLeftTriggerAxis()));
    }
      

    SmartDashboard.putNumber("Top Position", topMotor.getCANSparkMax().getEncoder().getPosition());
    SmartDashboard.putNumber("Bottom Position", bottomMotor.getCANSparkMax().getEncoder().getPosition());

    SmartDashboard.putNumber("Top Velocity", topMotor.getCANSparkMax().getEncoder().getVelocity());
    SmartDashboard.putNumber("Bottom Velocity", bottomMotor.getCANSparkMax().getEncoder().getVelocity());

    //m_rangeFinder.ping();
  }


  public void getRamp(double sTopSpeed, double sBottomSpeed, double intakeSeed){
    System.out.println(sTopSpeed + " // "  +  sBottomSpeed + " // " + intakeSeed  );
    topMotor.set(sTopSpeed);
    bottomMotor.set(-sBottomSpeed);
    rollersMotor.set(intakeSeed);
  }

  public void driveShooter(double topSpeed, double bottomSpeed)
  {
    topMotor.set(topSpeed);
    bottomMotor.set(bottomSpeed);
  }

  public TecbotSpeedController getBottomMotor(){
    return bottomMotor;
  }

  public void controlShooter(double topRPM, double bottomRPM)
  {
    double topSpeed = topfeedforward.calculate(topRPM);
    topSpeed += topPIDController.calculate(topMotor.getEncVelocity(), topRPM);

    double bottomSpeed = bottomfeedforward.calculate(bottomRPM);
    bottomSpeed += bottomPIDController.calculate(bottomMotor.getEncVelocity(), bottomRPM);

    driveShooter(topSpeed, bottomSpeed);

    SmartDashboard.putNumber("TopSpeed", topMotor.getEncVelocity());
    SmartDashboard.putNumber("BottomSpeed", bottomMotor.getEncVelocity());

    SmartDashboard.putNumber("Top Output", topSpeed);
    SmartDashboard.putNumber("Bottom Output", bottomSpeed);
  }
}

