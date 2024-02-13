package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;

public class Autonomous2 extends SequentialCommandGroup {
    public Autonomous2(){
        addCommands(new GoForward(), new WaitCommand(3), new TurnL(), new WaitCommand(2));
    }
}
