package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class Regreso extends CommandBase {
    public Regreso(){

    }

    @Override
    public void initialize(){
    }

    @Override
    public void execute(){
        Robot.getRobotContainer().getDriveTrain().regreso();
    }

    @Override
    public void end(boolean interrupted) {}

    @Override
    public boolean isFinished(){
        return false;
    }
}

