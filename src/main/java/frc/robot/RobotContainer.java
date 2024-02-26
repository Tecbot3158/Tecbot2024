// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.util.function.DoubleSupplier;
import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.event.EventLoop;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.commands.DefaultDriveCommand;
import frc.robot.commands.ZeroGyroCommand;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.RampSensorSubsystem;
import frc.robot.subsystems.RampSubsystem;


/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final DriveTrain m_drivetrainSubsystem = new DriveTrain();

  //private final Climber climber;
  private final RampSubsystem rampSubsystem;
  private final Climber climber;


  private final RampSensorSubsystem rampSensorSubsystem;

  private final  CommandXboxController m_controller = new CommandXboxController(0);

  private final CommandXboxController m_controller2 = new CommandXboxController(1);

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    
    // Set up the default command for the drivetrain.
    // The controls are for field-oriented driving:
    // Left stick Y axis -> forward and backwards movement
    // Left stick X axis -> left and right movement
    // Right stick X axis -> rotation

    //climber = new Climber();
    rampSubsystem = new RampSubsystem(this);
    climber = new Climber(this);

    rampSensorSubsystem = new RampSensorSubsystem();
    //climber.setController(m_controller.getHID() );
    
    DefaultDriveCommand ddc = new DefaultDriveCommand(
      m_drivetrainSubsystem,
      () -> -modifyAxis(m_controller.getLeftY()) * DriveTrain.MAX_VELOCITY_METERS_PER_SECOND,
      () -> -modifyAxis(m_controller.getLeftX()) * DriveTrain.MAX_VELOCITY_METERS_PER_SECOND,
      () -> -modifyAxis(m_controller.getRightX()) * DriveTrain.MAX_ANGULAR_VELOCITY_RADIANS_PER_SECOND );
    m_drivetrainSubsystem.setDefaultCommand(ddc);

    // Configure the button bindings
  }

  public DriveTrain getDriveTrain(){

      return m_drivetrainSubsystem;

  }

  public CommandXboxController getPilot(){

    return m_controller;

  }

  public CommandXboxController getCopilot(){
    return m_controller2;
  }

 /*public Climber getClimber(){
    return climber;
  }*/

  public RampSubsystem getRampSubsystem(){
    return rampSubsystem;
  }

  public RampSensorSubsystem getRampSensorSubsystem(){
    return rampSensorSubsystem;
  }
  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return new InstantCommand();
  }

  private static double deadband(double value, double deadband) {
    if (Math.abs(value) > deadband) {
      if (value > 0.0) {
        return (value - deadband) / (1.0 - deadband);
      } else {
        return (value + deadband) / (1.0 - deadband);
      }
    } else {
      return 0.0;
    }
  }

  private static double modifyAxis(double value) {
    // Deadband
    value = deadband(value, 0.05);

    // Square the axis
    value = Math.copySign(value * value, value);

    return value;
  }
}