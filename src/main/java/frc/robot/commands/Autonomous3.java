package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;

public class Autonomous3 extends SequentialCommandGroup {
    public Autonomous3(){
        addCommands(new GoForward(), new WaitCommand(3), new GoBackwards(), new WaitCommand(2));
    }
}
