package frc.robot.subsystems;
import com.revrobotics.CANSparkMax.IdleMode;

import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;
import frc.robot.resources.TecbotSpeedController;
import frc.robot.resources.TecbotController.TypeOfController;
import frc.robot.resources.TecbotSpeedController.TypeOfMotor;


public class Climber extends SubsystemBase {
  TecbotSpeedController cm1, cm2;
  XboxController xbox;

  public Climber(){
   cm1 = new TecbotSpeedController(RobotMap.climberPorts[0],TypeOfMotor.CAN_SPARK_BRUSHLESS);
   cm2 = new TecbotSpeedController(RobotMap.climberPorts[1],TypeOfMotor.CAN_SPARK_BRUSHLESS);

   xbox = new XboxController(0);
  }
      

    @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void onL(){
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
  }
}


