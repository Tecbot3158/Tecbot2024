package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.RampSubsystem;

public class AutoDriveAndShootRightSite extends SequentialCommandGroup{
    
    public AutoDriveAndShootRightSite (DriveTrain driveTrain, RampSubsystem ramp){

        addCommands(
            new AutoDriveToPosition(driveTrain , 0, 0,.60,0,0,0, 1.6),
            new Sequence1( ramp, 1 , 0.5),
            new ZeroRamp(ramp),
            new AutoDriveToPosition(driveTrain, .60, 0, 1.5, 0, 0, 0, 1.6),
            new AutoDriveToPosition(driveTrain, 1.5, 0, 4.5, -5, 0, 0, 1.6),
            new AutoDriveToPosition(driveTrain, 4.5, -5, 3.6, -6.2, 0, 0, 1.6),
            new AutoDriveToPositionWhileRolling(driveTrain, ramp, 3.6, -6.2, 4.9, -6.2, 0, 0, 0.6, 0.6, 1.6)
            
        );

    }
}
