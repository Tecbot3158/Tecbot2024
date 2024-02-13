// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.Autonomous1;
import frc.robot.commands.Autonomous2;
import frc.robot.commands.Autonomous3;
import frc.robot.commands.Autos;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.GoForwardWithE;
import frc.robot.commands.Regreso;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();

  // Replace with CommandPS4Controller or CommandJoystick if needed
  private final CommandXboxController m_driverController =
      new CommandXboxController(OperatorConstants.kDriverControllerPort);

      public static DriveTrain driveTrain;
      public static OI oi;
      public static Shooter shooter;
      public static Intake intake;

      public static Autonomous1 a1;
      public static Autonomous2 a2;
      public static Autonomous3 a3;

      private SendableChooser<Command> m_auto_Chooser;

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    driveTrain = new DriveTrain();
    shooter = new Shooter();
    intake = new Intake();
    oi = new OI();
    a1 = new Autonomous1();
    a2 = new Autonomous2();
    a3 = new Autonomous3();
    configureBindings();  

    m_auto_Chooser = new SendableChooser<Command>();

    m_auto_Chooser.setDefaultOption("Autonomous 1", new Autonomous1());
    m_auto_Chooser.addOption("Autonomous 2", new Autonomous2());
    m_auto_Chooser.addOption("Autonomous 3", new Autonomous3());
    m_auto_Chooser.addOption("Encoders", new GoForwardWithE());
    m_auto_Chooser.addOption("Regreso", new Regreso());
 
    SmartDashboard.putData(m_auto_Chooser);
    
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    OI.getInstance().configureButtonBindings();
    // Schedule `ExampleCommand` when `exampleCondition` changes to `true`
    new Trigger(m_exampleSubsystem::exampleCondition)
        .onTrue(new ExampleCommand(m_exampleSubsystem));

    // Schedule `exampleMethodCommand` when the Xbox controller's B button is pressed,
    // cancelling on release.
    m_driverController.b().whileTrue(m_exampleSubsystem.exampleMethodCommand());
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */


  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return m_auto_Chooser.getSelected();
  }

  

  



  public static DriveTrain getDriveTrain(){
    return driveTrain;
  }

  public static OI getOI(){
    return oi;
  }

  public static Shooter getShooter(){
    return shooter;
  }

  public static Intake getIntake(){
    return intake;
  }
}
