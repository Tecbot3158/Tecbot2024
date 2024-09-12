package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.RampSubsystem;

public class AutoDriveAndShootRightSiteRed extends SequentialCommandGroup{
    
    public AutoDriveAndShootRightSiteRed (DriveTrain driveTrain, RampSubsystem ramp){

        addCommands(
            new AutoDriveToPosition(driveTrain , 0, 0,.60,0,0,0, 1.6),
            new Sequence1( ramp, 1 , 0.65),
            new ZeroRamp(ramp),
            new AutoDriveToPosition(driveTrain, .60, 0, 1.5, -0.65, 0, 0, 1.6),
            new AutoDriveToPosition(driveTrain, 1.25, -0.40, 2.1, -1, 0, 0, 2)
            
        );

    }
}
