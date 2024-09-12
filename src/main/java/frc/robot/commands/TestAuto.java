
package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.RampSubsystem;

public class TestAuto extends SequentialCommandGroup {

    public TestAuto (DriveTrain driveTrain){
        addCommands(
            new ZeroGyroCommand(driveTrain),
            new AutoDriveToPosition(driveTrain , 0, 0,0,0,0, Math.PI, 1)
            );

        //new AutoDriveToPosition(driveTrain, 0.1, 0.1, 0, 0, 180, 0, 0);
    }
    
}
