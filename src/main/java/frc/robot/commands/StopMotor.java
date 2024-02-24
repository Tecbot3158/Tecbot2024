package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.RampSubsystem;

public class StopMotor extends Command {
  /** Creates a new IntakeOn. */
  Timer t1;
  double timeConstant;
  RampSubsystem subsystem;
  
  
  public StopMotor(RampSubsystem ramp, double t) {
    // Use addRequirements() here to declare subsystem dependencies.
    subsystem = ramp;
    timeConstant = t;
    t1 = new Timer();
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    t1.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double timerPercentage = (t1.get()/timeConstant);
    double voltage = (1 - timerPercentage);

    subsystem.getBottomMotor().set(-voltage);
    
  }
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    subsystem.getBottomMotor().set(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (t1.get() >= timeConstant){
      return true;
    }
    return false;
  }
}
