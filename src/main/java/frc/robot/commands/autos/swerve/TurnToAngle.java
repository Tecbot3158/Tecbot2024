// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.autos.swerve;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Robot;

public class TurnToAngle extends Command {

  double angle;
  double maxSpeed;
  /** Creates a new TurnToAngle. */
  public TurnToAngle(double angle, double maxSpeed) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(Robot.getRobotContainer().getDriveTrain());
    this.angle = angle;
    this.maxSpeed = maxSpeed;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() 
  {
    Robot.getRobotContainer().getDriveTrain().setPIDTurnSetpoint(angle);
    Robot.getRobotContainer().getDriveTrain().setMaxPIDTurnSpeed(maxSpeed);
    Robot.getRobotContainer().getDriveTrain().enable();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) 
  {
    Robot.getRobotContainer().getDriveTrain().disable();
    Robot.getRobotContainer().getDriveTrain().drive(new ChassisSpeeds());
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return Robot.getRobotContainer().getDriveTrain().onPIDTurnTarget();
  }
}
