package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;

public class Autonomous1 extends SequentialCommandGroup {
    public Autonomous1(){
        addCommands(new GoForward(), new WaitCommand(3), new TurnR(), new WaitCommand(2));
    }
}
