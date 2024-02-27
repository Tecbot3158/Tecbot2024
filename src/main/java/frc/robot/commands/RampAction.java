package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.RampSubsystem;

public class RampAction extends Command {
  /** Creates a new IntakeOn. */
  double sTS, sBS, iS;
  RampSubsystem rampSubsystem;

  public RampAction(RampSubsystem subsystem, double sts, double sbs, double is){
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
    sTS = sts;
    sBS = sbs;
    iS = is;

    rampSubsystem = subsystem;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    rampSubsystem.getRamp(sTS, sBS, iS);
  }
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    
    rampSubsystem.getRamp(0, 0, 0);

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
