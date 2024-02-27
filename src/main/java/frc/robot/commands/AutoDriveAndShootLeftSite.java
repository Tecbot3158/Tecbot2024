package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.RampSubsystem;

public class AutoDriveAndShootLeftSite extends SequentialCommandGroup{
    
    public AutoDriveAndShootLeftSite (DriveTrain driveTrain, RampSubsystem ramp){

        addCommands(
            new AutoDriveToPosition(driveTrain , 0, 0,.60,0,0,0, 1.6),
            new Sequence1( ramp, 1 , 0.5),
            new AutoDriveToPosition(driveTrain, .60, 0, 4, 0, 0, 0, 1.6),
            new AutoDriveToPosition(driveTrain, 4, 0, 6.5, 4.5, 0, 0, 1.6)
        );

    }
}
