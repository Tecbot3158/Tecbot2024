package frc.robot.subsystems;
import com.revrobotics.CANSparkMax.IdleMode;

import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.OI;
import frc.robot.RobotMap;
import frc.robot.resources.TecbotController;
import frc.robot.resources.TecbotSpeedController;
import frc.robot.resources.TecbotController.TypeOfController;
import frc.robot.resources.TecbotSpeedController.TypeOfMotor;


public class Intake extends SubsystemBase {
  TecbotSpeedController im1;
  public static TecbotController xbox;

  /*private ShuffleboardTab tab = Shuffleboard.getTab("Intake"); 
  private GenericEntry intakeSpeed =
      tab.add("Intake Motor Speed", 0.1)
         .getEntry();*/

  public Intake(){
   im1 = new TecbotSpeedController(15,TypeOfMotor.VICTOR_SPX);
   xbox = OI.getPilot();
   

  }
      

    @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void onForward(){
    if(xbox.getRightTrigger()<=0.3){
      im1.set(0.3);
      System.out.println("is working r");
    }
    else{
      im1.set(0);
    }
  }
  
  public void onBackwards(){
    if(xbox.getLeftTrigger()<=0.3){
      im1.set(-0.3);
      System.out.println("is working l");
    }
    else{
      im1.set(0);
    }
  }

  /*public void editableIntakeVelocity(){
    double iSpeed = intakeSpeed.getDouble(.1);

    im1.set(iSpeed);
  }*/
}

