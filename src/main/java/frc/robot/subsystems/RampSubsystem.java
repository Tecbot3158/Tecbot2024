package frc.robot.subsystems;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.SystemMenuBar;

import com.revrobotics.CANSparkMax.IdleMode;

import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;
import frc.robot.resources.TecbotSpeedController;
import frc.robot.resources.TecbotController.TypeOfController;
import frc.robot.resources.TecbotSpeedController.TypeOfMotor;


public class RampSubsystem extends SubsystemBase {
  TecbotSpeedController sm1, sm2, im1;

  public RampSubsystem(){
   sm1 = new TecbotSpeedController(RobotMap.shooterPorts[0],TypeOfMotor.CAN_SPARK_BRUSHLESS);
   sm2 = new TecbotSpeedController(RobotMap.shooterPorts[1],TypeOfMotor.CAN_SPARK_BRUSHLESS);

   im1 = new TecbotSpeedController(RobotMap.intakePort[0], TypeOfMotor.VICTOR_SPX);

   sm1.getCANSparkMax().setIdleMode(IdleMode.kCoast);
   sm2.getCANSparkMax().setIdleMode(IdleMode.kCoast);
  }
      

    @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void getRamp(double sTopSpeed, double sBottomSpeed, double intakeSeed){
    sm1.set(sTopSpeed);
    sm2.set(-sBottomSpeed);
    im1.set(intakeSeed);
    System.out.println(sTopSpeed);
  }
}

