// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveTrain;

public class AutoDriveToPosition extends Command {
  /** Creates a new AutoDriveToPosition. */
  double initialX;
  double finalX;

  double initialY;
  double finalY;

  double initialR;
  double finalR;

  double totalMagnitude;
  double currentMagnitude;
  double totalRotation;

  double speedMultiplier;

  DriveTrain driveTrain;

  public AutoDriveToPosition(DriveTrain dt, double xi, double yi, double xf, double yf, double ri , double rf, double sm){
    // Use addRequirements() here to declare subsystem dependencies.
    
    addRequirements(dt);
    driveTrain = dt;
   initialX = xi;
   finalX = xf;

   initialY = yi;
   finalY = yf;

   initialR =ri;
   finalR = rf;
   speedMultiplier = sm;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

    double dx = finalX - initialX;
    double dy = finalY - initialY;
    totalMagnitude = (new Translation2d(dx, dy) ).getNorm();

    double dr = finalR - initialR;
    totalRotation = (new Rotation2d(dr)).getDegrees();

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    double currentangle = driveTrain.getGyroscopeRotation().getDegrees();

    Translation2d currentTranslation = driveTrain.getPose2d().getTranslation();
    // determine magnitude from origin pos to final pos
    double dx = finalX - currentTranslation.getX();
    double dy = finalY - currentTranslation.getY();

    double dr = finalR - currentangle;

    SmartDashboard.putNumber("Sx", dx);
    SmartDashboard.putNumber("Sy", dy);

    Translation2d tr = new Translation2d(dx, dy);
    double advance = tr.getNorm()/totalMagnitude;

    Rotation2d rt = new Rotation2d(dr);
    double rotationAdvance = rt.getDegrees()/totalRotation;
    
    currentMagnitude = tr.getNorm(); // vector size
    tr = tr.div(currentMagnitude);
    
    // determine magnitude from current por to final pos
    
    System.out.println(currentMagnitude);
    SmartDashboard.putNumber("CurrentMagnitude", currentMagnitude);

    System.out.println(totalMagnitude);
    
    System.out.println("Advance : " + advance);
    // determine percentage. 
    
    if( advance > 0.05){

      //advance = 1; // is a percentual control. 
      if( advance< 0.9 )
        advance = 0.5;
      else
        advance = 0.9;
      
    
    }else{
      advance = Math.max(0.2,advance); // minimum advance
    }

    if( rotationAdvance > 0.05){

      //advance = 1; // is a percentual control. 
      if( rotationAdvance < 0.9 )
        rotationAdvance = 0.5;
      else
        rotationAdvance = 0.9;
      
    
    }else{
      rotationAdvance = Math.max(0.2,advance); // minimum advance
    }


    System.out.println("Vx : " + tr.getX()*advance );
    System.out.println("Vy : " + tr.getY()*advance );
    
    double xSpeed = tr.getX()*advance;
    double ySpeed = tr.getY()*advance;

    double rSpeed = rt.getDegrees()*rotationAdvance;

    
    /* 
    if(xSpeed > 3)
    xSpeed = 4;
    else if (xSpeed > 1) 
    xSpeed = xSpeed + 1*speedMultiplier;
    else 
    xSpeed = xSpeed + 0.2*speedMultiplier;

    if(ySpeed > 3)
    ySpeed = 4;
    else if (ySpeed > 1) 
    ySpeed = ySpeed + 1*speedMultiplier;
    else 
    ySpeed = ySpeed + 0.2*speedMultiplier;
    */

    driveTrain.drive(
                ChassisSpeeds.fromFieldRelativeSpeeds(
                        xSpeed,
                        ySpeed,
                        rSpeed,
                        driveTrain.getGyroscopeRotation()
                )
        );
    // 


  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {

      driveTrain.drive(
                ChassisSpeeds.fromFieldRelativeSpeeds(
                        0,
                        0,
                        0,
                        driveTrain.getGyroscopeRotation()
                )
        );
    // 



  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {

    return currentMagnitude < 0.05  ; // not percentage but cm...
  }
}
