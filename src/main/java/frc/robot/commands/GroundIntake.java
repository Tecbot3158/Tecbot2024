package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class GroundIntake extends CommandBase {
  /** Creates a new IntakeOn. */
  double sTS, sBS, iS;
  public GroundIntake(double sts, double sbs, double is){
    // Use addRequirements() here to declare subsystem dependencies.
    //addRequirements(Robot.getRobotContainer().getSolenoidTest());
    sTS = sts;
    sBS = sbs;
    iS = is;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    Robot.getRobotContainer().getRampSubsystem().getRamp(sTS, sBS, iS);
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
