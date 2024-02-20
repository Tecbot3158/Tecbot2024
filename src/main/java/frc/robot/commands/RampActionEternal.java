package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.RampSubsystem;

public class RampActionEternal extends CommandBase {
  /** Creates a new IntakeOn. */
  double sTS, sBS, iS;
  RampSubsystem rampSubsystem;
  Timer timer1;
  double time;

  public RampActionEternal(RampSubsystem subsystem, double sts, double sbs, double is, double t){
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
    sTS = sts;
    sBS = sbs;
    iS = is;
    time = t;
    rampSubsystem = subsystem;

    timer1 = new Timer();
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    timer1.restart();


  }


  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    rampSubsystem.getRamp(sTS, sBS, iS);
  }
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    timer1.stop();
    if(interrupted){
      rampSubsystem.getRamp(0, 0, 0);
    }
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return timer1.get()>time;
  }
}
