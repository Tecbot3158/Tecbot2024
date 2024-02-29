// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.RampSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class AutoDriveAndShootRed extends SequentialCommandGroup {
  /** Creates a new AutoDriveAndShoot. */
  public AutoDriveAndShootRed(DriveTrain driveTrain, RampSubsystem ramp) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());

/* 
    firsst 1
    addCommands(
    new AutoDriveToPosition(driveTrain , 0, 0,.60,0,0,0, 1.6),
    new Sequence1( ramp, 1 , 0.5),
    new AutoDriveToPosition(driveTrain , 0.60, 0,2.30,0,0,0, 1.6),
    new RampActionEternal(ramp,0.0, 0, 0.3, 0.25),
    new RampActionEternal(ramp,0.0, 0, 0.0, 0.1),    
    new AutoDriveToPosition(driveTrain , 2.30, 0,.60,0,0,0, 1.6),
    new Sequence1( ramp, 1 , 1.0),
    new AutoDriveToPosition(driveTrain , 0.60, 0,5.0,0.63,0,0, 1.6),
    new AutoDriveToPosition(driveTrain , 5.0, 1.6,8.0,0.63,0,0, 1.6),
    new RampActionEternal(ramp,0.0, 0, 0.3, 0.25),
    new RampActionEternal(ramp,0.0, 0, 0.0, 0.1),
    new AutoDriveToPosition(driveTrain , 8.0, 0.63,0.60,0.63,0,0, 1.6)
    );
*/
    
    addCommands(
    new AutoDriveToPosition(driveTrain , 0, 0,.40,0,0,0, 1.7),
    new Sequence1( ramp, 0.50 , 0.65),
    new ZeroRamp(ramp),
    new AutoDriveToPositionWhileRolling(driveTrain, ramp, 0.4, 0, 2.00, 0, 0, 0, 0.30, 0.40, 1.6),
    new AutoDriveToPosition(driveTrain , 2.00, 0,.40,0,0,0, 1.7),
    new Sequence1( ramp, 0.40 , 0.70),
    new ZeroRamp(ramp),
    new AutoDriveToPosition(driveTrain , 0.40, 0,1.00,-1.55,0,0, 1.7),
    new AutoDriveToPositionWhileRolling(driveTrain, ramp, 1.00, -1.55, 2.00, -1.55, 0, 0, .25, .40, 1.6),
    new AutoDriveToPosition(driveTrain , 2.00, -1.55,0.30,0.0,0,0, 1.7),
    new Sequence1( ramp, 0.70 , 1.0),
    new ZeroRamp(ramp)
    );
    


    /* 
    addCommands(
    new AutoDriveToPosition(driveTrain , 0, 0,.40,0,0,0, 1.7),
    new Sequence1( ramp, 0.50 , 0.65),
    new ZeroRamp(ramp),
    new AutoDriveToPositionWhileRolling(driveTrain, ramp, 0.4, 0, 2.00, 0, 0, 0, 0.30, 0.40, 1.6),
    new AutoDriveToPosition(driveTrain , 2.00, 0,.40,0,0,0, 1.7),
    new Sequence1( ramp, 0.40 , 0.70),
    new ZeroRamp(ramp),
    new AutoDriveToPosition(driveTrain , 0.40, 0,1.00,-1.55,0,0, 1.7),
    new AutoDriveToPositionWhileRolling(driveTrain, ramp, 1.00, -1.55, 2.00, -1.55, 0, 0, .25, .40, 1.6),
    new AutoDriveToPosition(driveTrain , 2.00, -1.55,0.30,0.0,0,0, 1.7),
    new Sequence1( ramp, 0.70 , 1.0),
    new ZeroRamp(ramp)
    );
    */
    
  }
}


