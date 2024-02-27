// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.RampSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class AutoDriveAndShoot extends SequentialCommandGroup {
  /** Creates a new AutoDriveAndShoot. */
  public AutoDriveAndShoot(DriveTrain driveTrain, RampSubsystem ramp) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
    new AutoDriveToPosition(driveTrain , 0, 0,.60,0,0,0, 1.6),
    new Sequence1( ramp, 1 , 0.5),
    new AutoDriveToPositionWhileRolling(driveTrain, ramp, 0.6, 0, 2.35, 0, 0, 0, 0.6, 0.6, 1.6),
    //new AutoDriveToPosition(driveTrain , 0.60, 0,2.35,0,0,0),
    new RampActionEternal(ramp,0.0, 0, 0.4, 0.2),
    new RampActionEternal(ramp,0.0, 0, 0.0, 0.1),    
    new AutoDriveToPosition(driveTrain , 2.35, 0,.60,0,0,0, 1.6),
    new Sequence1( ramp, 1 , 1.0),
    new AutoDriveToPosition(driveTrain , 0.60, 0,1.10,1.49,0,0, 1.6),
    new AutoDriveToPositionWhileRolling(driveTrain, ramp, 1.10, 1.49, 2.35, 1.49, 0, 0, .4, .6, 1.6),
   // new AutoDriveToPosition(driveTrain , 1.20, 1.65,2.30,1.25,0,0),
    new RampActionEternal(ramp,0.0, 0, 0.3, 0.25),
    new RampActionEternal(ramp,0.0, 0, 0.0, 0.1),
    new AutoDriveToPosition(driveTrain , 2.35, 1.49
    ,0.60,0.0,0,0, 1.6),
    new Sequence1( ramp, 1 , 1.0)
    );
  }
}
