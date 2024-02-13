package frc.robot.subsystems;
import com.revrobotics.CANSparkMax.IdleMode;

import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;
import frc.robot.resources.TecbotSpeedController;
import frc.robot.resources.TecbotController.TypeOfController;
import frc.robot.resources.TecbotSpeedController.TypeOfMotor;


public class Shooter extends SubsystemBase {
  TecbotSpeedController sm1, sm2;
  private ShuffleboardTab tab = Shuffleboard.getTab("Shooter Speeds"); 
  private GenericEntry topSpeed =
      tab.add("Bottom Speed", 0.1)
         .getEntry();
   
  private GenericEntry bottomSpeed =
   tab.add("Top Speed", 0.1)
        .getEntry();
         

  public Shooter(){
   sm1 = new TecbotSpeedController(RobotMap.shooterPorts[0],TypeOfMotor.CAN_SPARK_BRUSHLESS);
   sm2 = new TecbotSpeedController(RobotMap.shooterPorts[1],TypeOfMotor.CAN_SPARK_BRUSHLESS);

   sm1.getCANSparkMax().setIdleMode(IdleMode.kCoast);
   sm2.getCANSparkMax().setIdleMode(IdleMode.kCoast);
  }
      

    @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void onShooter(){
    System.out.println("on Shooter");
    sm1.set(.7);
    sm2.set(-1);
  }
  
  public void onShooter2(){
    sm1.set(.1);
    sm2.set(-.3);
  }

  public void oneShooter3(){
    sm1.set(.9);
    sm2.set(-.6);
  }

  public void offShooter(){
    System.out.println("off shooter");
    sm1.set(0);
    sm2.set(0);
  }
  
  public void editableShooterVelocity(){
    double top = topSpeed.getDouble(0.1);
    double bottom = bottomSpeed.getDouble(0.1);

    sm1.set(top);
    sm2.set(-bottom);
  }
}

