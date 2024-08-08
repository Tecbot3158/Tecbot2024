package frc.robot.commands.autos;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.Sequence1;
import frc.robot.commands.ZeroRamp;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.RampSubsystem;

public class AutoDriveAndShootLeftSiteBlue extends SequentialCommandGroup{
    
    public AutoDriveAndShootLeftSiteBlue (DriveTrain driveTrain, RampSubsystem ramp){

        addCommands(
            new AutoDriveToPosition(driveTrain , 0, 0,.60,0,0,0, 1.6),
            new Sequence1( ramp, 1 , 0.5),
            new ZeroRamp(ramp),
            new AutoDriveToPosition(driveTrain, .60, 0, 1.25, 0.40, 0, 0, 1.6),
            new AutoDriveToPosition(driveTrain, 1.25, 0.40, 2.1,-1, 0, 0, 2)
        );

    }
}
