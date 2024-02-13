package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class GoForward extends CommandBase {
    public GoForward(){

    }

    @Override
    public void initialize(){
   
    }

    @Override
    public void execute(){
        Robot.getRobotContainer().getDriveTrain().goForward();
    }

    @Override
    public void end(boolean interrupted) {}

    @Override
    public boolean isFinished(){
        return true;
    }
}

