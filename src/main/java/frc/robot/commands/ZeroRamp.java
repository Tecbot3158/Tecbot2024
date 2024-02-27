package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.RampSubsystem;

public class ZeroRamp extends Command {
  /** Creates a new IntakeOn. */
  
  
  RampSubsystem subsystem;
  
  
  public ZeroRamp(RampSubsystem ramp) {
    // Use addRequirements() here to declare subsystem dependencies.
    subsystem = ramp;
  
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    subsystem.getRamp(0, 0, 0);
    
  }
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    subsystem.getRamp(0, 0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
      return true;
  }
}
