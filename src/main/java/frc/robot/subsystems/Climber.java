package frc.robot.subsystems;

import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;
import frc.robot.resources.TecbotSpeedController;
import frc.robot.resources.TecbotSpeedController.TypeOfMotor;


public class Climber extends SubsystemBase {
  TecbotSpeedController cmL, cmR;
  XboxController xbox;

  ShuffleboardTab tab = Shuffleboard.getTab("Climber");
    

  public Climber(){
   cmL = new TecbotSpeedController(RobotMap.climberPorts[0],TypeOfMotor.CAN_SPARK_BRUSHLESS);
   cmR = new TecbotSpeedController(RobotMap.climberPorts[1],TypeOfMotor.CAN_SPARK_BRUSHLESS);
  }
      
  public void setController(XboxController c1){
    xbox = c1;
  }

    @Override
  public void periodic() {
    // This method will be called once per scheduler run
    if(xbox != null){
      double axisLeft = xbox.getLeftTriggerAxis();
      double axisRight = xbox.getRightTriggerAxis();
      cmL.set(axisLeft * .1);
      cmR.set(-axisRight * .1);

      boolean bumperLeft = xbox.getLeftBumper();
      boolean bumperRight = xbox.getRightBumper();

      if(bumperLeft){
        cmL.set(axisLeft * -.1);
      }

      if(bumperRight){
        cmR.set(axisRight * .1);
      }
    }

    SmartDashboard.putNumber("Climber/LeftEncoder", cmL.getCANSparkMax().getEncoder().getPosition());
    SmartDashboard.putNumber("Climber/RightEncoder", cmR.getCANSparkMax().getEncoder().getPosition());

    SmartDashboard.putNumber("Climber/LeftEncoder Velocity", cmL.getCANSparkMax().getEncoder().getVelocity());
    SmartDashboard.putNumber("Climber/RightEncoder Velocity", cmR.getCANSparkMax().getEncoder().getVelocity());
  }

  /*public void onL(){
    if(xbox.getRightTriggerAxis()<=0.3){
      cm1.set(-.5);
    }
    else{
      cm1.set(0);
    }
 
  }

  public void onR(){
    if(xbox.getRightTriggerAxis()<=0.3){
      cm2.set(.5);
    }
    else{
      cm2.set(0);
    }

  }

  public void offL(){
    cm1.set(0);
    cm2.set(0);
  }

  public void offR(){
    cm2.set(0);
  }

  public void triggerClimber(){
    cm1.set(0.5);
    cm2.set(0.5);
  }*/
}


