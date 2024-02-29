package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.RampSubsystem;

public class AutoDriveAndShootLeftSiteRed extends SequentialCommandGroup {
    
    public AutoDriveAndShootLeftSiteRed(DriveTrain driveTrain, RampSubsystem ramp){

        addCommands(
            new AutoDriveToPosition(driveTrain , 0, 0,.60,0,0,0, 1.6),
            new Sequence1( ramp, 0.5 , 0.65),
            new ZeroRamp(ramp),
            new AutoDriveToPosition(driveTrain, .60, 0, 4, 0, 0, 0, 1.6),
            new AutoDriveToPosition(driveTrain, 4, 0, 7.5, -3.5, 0, 0, 1.6)
        );

    }

}
