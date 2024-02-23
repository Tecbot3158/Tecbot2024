package frc.robot.commands.Autos;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Robot;
import frc.robot.commands.AutoCommmands.Go;

public class Auto1 extends SequentialCommandGroup  {
    public Auto1 Auto1;
      /** Creates a new DriveForwardEncoders. */
    public Auto1(){
        addCommands(new Go(Robot.getRobotContainer().getDriveTrain(), 1,1,0));
    }
}