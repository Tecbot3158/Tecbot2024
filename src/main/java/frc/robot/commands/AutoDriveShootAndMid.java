package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.RampSubsystem;

public class AutoDriveShootAndMid extends SequentialCommandGroup {

    public AutoDriveShootAndMid(DriveTrain driveTrain, RampSubsystem ramp) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    
    addCommands(
    new AutoDriveToPosition(driveTrain , 0, 0,.60,0,0,0,1.6),
    new Sequence1( ramp, 1 , 0.5),
    new AutoDriveToPositionWhileRolling(driveTrain, ramp , 0.60, 0, 2.30, 0, 0, 0, 0.6, 0.6, 1.6),
    new RampActionEternal(ramp,0.0, 0, 0.3, 0.25),
    new RampActionEternal(ramp,0.0, 0, 0.0, 0.1),    
    new AutoDriveToPosition(driveTrain , 2.30, 0,.60,0,0,0,1.6),
    new Sequence1( ramp, 1 , 1.0),
    new AutoDriveToPosition(driveTrain , 0.60, 0,5.0,0.63,0,0,1.60),
    new AutoDriveToPosition(driveTrain , 5.0, 1.6,8.0,0.63,0,0,1.6),
    new RampActionEternal(ramp,0.0, 0, 0.3, 0.25),
    new RampActionEternal(ramp,0.0, 0, 0.0, 0.1),
    new AutoDriveToPosition(driveTrain , 8.0, 0.63,0.60,0.63,0,0,1.6)
    );
  }
}
