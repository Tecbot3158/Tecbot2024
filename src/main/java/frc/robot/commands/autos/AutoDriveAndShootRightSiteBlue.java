package frc.robot.commands.autos;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.Sequence1;
import frc.robot.commands.ZeroRamp;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.RampSubsystem;

public class AutoDriveAndShootRightSiteBlue extends SequentialCommandGroup{
    
    public AutoDriveAndShootRightSiteBlue(DriveTrain driveTrain, RampSubsystem ramp){

        addCommands(
            new AutoDriveToPosition(driveTrain , 0, 0, .60 ,0,0,0, 1.6),
            new Sequence1( ramp, 0.5 , 0.65),
            new ZeroRamp(ramp),
            new AutoDriveToPosition(driveTrain, .60, 0, 4.8, 0, 0, 0, 1.6)
           // new AutoDriveToPosition(driveTrain, 4.8, 0, 7.5, 3.5, 0, 0, 1.6)
        );

    }
}
