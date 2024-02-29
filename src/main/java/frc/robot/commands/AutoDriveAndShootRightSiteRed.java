package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.RampSubsystem;

public class AutoDriveAndShootRightSiteRed extends SequentialCommandGroup{
    
    public AutoDriveAndShootRightSiteRed (DriveTrain driveTrain, RampSubsystem ramp){

        addCommands(
            new AutoDriveToPosition(driveTrain , 0, 0,.50,0,0,0, 1.6),
            new Sequence1( ramp, 1 , 0.5),
            new ZeroRamp(ramp),
            new AutoDriveToPosition(driveTrain, .50, 0, 1.3, 0, 0, 0, 1.6),
            new AutoDriveToPosition(driveTrain, 1.3, 0, 3.7, 5.1, 0, 0, 2)
            
        );

    }
}
