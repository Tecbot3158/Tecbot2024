// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Robot;

public class ControlShooter extends Command {
  double topRPM, bottomRPM;
  /** Creates a new ControlShooter. */
  public ControlShooter(double topRPM, double bottomRPM) 
  {
    this.bottomRPM = bottomRPM;
    this.topRPM = topRPM;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    SmartDashboard.putBoolean("ShooterOnTarget",
      Robot.getRobotContainer().getRampSubsystem().controlShooter(topRPM, bottomRPM));
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) 
  {
    Robot.getRobotContainer().getRampSubsystem().driveShooter(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
