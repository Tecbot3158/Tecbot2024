package frc.robot.subsystems;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.SystemMenuBar;

import com.revrobotics.CANSparkBase.IdleMode;

import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;
import frc.robot.resources.TecbotSpeedController;
import frc.robot.resources.TecbotSpeedController.TypeOfMotor;


public class RampSubsystem extends SubsystemBase {
  TecbotSpeedController sm1, sm2, im1;
  XboxController xbox, xbox2;

  public RampSubsystem(){
   sm1 = new TecbotSpeedController(RobotMap.shooterPorts[0],TypeOfMotor.CAN_SPARK_BRUSHLESS);
   sm2 = new TecbotSpeedController(RobotMap.shooterPorts[1],TypeOfMotor.CAN_SPARK_BRUSHLESS);

   im1 = new TecbotSpeedController(RobotMap.intakePort[0], TypeOfMotor.VICTOR_SPX);

   xbox = new XboxController(0);
   xbox2 = new XboxController(1);

   sm1.getCANSparkMax().setIdleMode(IdleMode.kCoast);
   sm2.getCANSparkMax().setIdleMode(IdleMode.kCoast);


  }
    

    @Override
  public void periodic() {
    // This method will be called once per scheduler run
    im1.set(Math.min(1,xbox2.getRightTriggerAxis() + xbox.getRightTriggerAxis())-Math.min(1,xbox.getLeftTriggerAxis() + xbox2.getLeftTriggerAxis()));


    SmartDashboard.putNumber("Top Position", sm1.getCANSparkMax().getEncoder().getPosition());
    SmartDashboard.putNumber("Bottom Position", sm2.getCANSparkMax().getEncoder().getPosition());

    SmartDashboard.putNumber("Top Velocity", sm1.getCANSparkMax().getEncoder().getVelocity());
    SmartDashboard.putNumber("Bottom Velocity", sm2.getCANSparkMax().getEncoder().getVelocity());

    //m_rangeFinder.ping();
  }


  public void getRamp(double sTopSpeed, double sBottomSpeed, double intakeSeed){
    System.out.println(sTopSpeed + " // "  +  sBottomSpeed + " // " + intakeSeed  );
    sm1.set(sTopSpeed);
    sm2.set(-sBottomSpeed);
    im1.set(intakeSeed);
  }

  public TecbotSpeedController getBottomMotor(){
    return sm2;
  }
}

