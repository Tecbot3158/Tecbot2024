package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class EditableShooterVelocity extends CommandBase{
    public EditableShooterVelocity() {
        // Use addRequirements() here to declare subsystem dependencies.
        //addRequirements(Robot.getRobotContainer().getSolenoidTest());
      }
    
      // Called when the command is initially scheduled.
      @Override
      public void initialize() {
    
      }
    
      // Called every time the scheduler runs while the command is scheduled.S
      @Override
      public void execute() {
        Robot.getRobotContainer().getShooter().editableShooterVelocity();
      }
    
      // Called once the command ends or is interrupted.
      @Override
      public void end(boolean interrupted) {}
    
      // Returns true when the command should end.
      @Override
      public boolean isFinished() {
        return true;
    } 
}
