package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class GoForwardWithE extends CommandBase {
    public GoForwardWithE(){

    }

    @Override
    public void initialize(){
        Robot.getRobotContainer().getDriveTrain().resetEncoders();
    }

    @Override
    public void execute(){
        Robot.getRobotContainer().getDriveTrain().zgoForwardWithE();
    }

    @Override
    public void end(boolean interrupted) {}

    @Override
    public boolean isFinished(){
        return false;
    }
}

