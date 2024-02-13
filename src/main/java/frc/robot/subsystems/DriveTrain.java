package frc.robot.subsystems;

import com.revrobotics.REVLibError;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.IdleMode;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;
import frc.robot.resources.TecbotSpeedController;
import frc.robot.RobotMap;

public class DriveTrain extends SubsystemBase {
    TecbotSpeedController m1;
    TecbotSpeedController m2;

    RelativeEncoder e1, e2;

    double metersM1, metersM2;

   PIDController pidController;

    public DriveTrain(){
        m1 = new TecbotSpeedController(RobotMap.driveTrainPorts[0], RobotMap.chassisMotor[0]);
        m2 = new TecbotSpeedController(RobotMap.driveTrainPorts[1], RobotMap.chassisMotor[0]);

        m1.getCANSparkMax().setIdleMode(IdleMode.kBrake);
        m2.getCANSparkMax().setIdleMode(IdleMode.kBrake);

        e1 = m1.getCANSparkMax().getEncoder();
        e2 = m2.getCANSparkMax().getEncoder();

        e1.setPositionConversionFactor(1 * (Math.PI * RobotMap.wheelDiameter) / 8.7);

        pidController = new PIDController(metersM1, metersM2, metersM1);
      }

    @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  public void drive(double x, double y){
    double rightSpeed = (-x - y);
    double leftSpeed = (-x + y);
    m1.set(leftSpeed);
    m2.set(rightSpeed);

    SmartDashboard.putNumber("Postion", e1.getPosition());
    SmartDashboard.putNumber("Position Convertion Factor", e1.getPositionConversionFactor());
    


    SmartDashboard.putNumber("Motor Inches", e1.getPosition());
  } 

  public void goForward(){
    m1.set(-0.1);
    m2.set(0.1);
  }

  public void turnL(){
    m1.set(.1);
    m2.set(.1);
  }

  public void turnR(){
    m1.set(-.1);
    m2.set(-.1);
  }

  public void goBacwards(){
    m1.set(.1);
    m2.set(-.1);
  }

  public void getPosition(){

    //metersM1 = (e1.getCountsPerRevolution()/(RobotMap.wheelDiameter * Math.PI)) / 39.3701;

    SmartDashboard.putNumber("Inches of motor", e1.getPosition());
    SmartDashboard.putNumber("Conversion factor", e1.getPositionConversionFactor());
  }

   public void 
   zgoForwardWithE(){
    SmartDashboard.putNumber("Cool position", e1.getPosition());
   

    if(e1.getPosition() <= -66.92){
    m1.set(0);
    m2.set(0);  
    }else{
      m1.set(-0.7);
      m2.set(0.7);
    }
   }

   public void resetEncoders(){
    e1.setPosition(0);
    e2.setPosition(0);
   }

   public void regreso(){
    SmartDashboard.putNumber("Shit Position", e1.getPosition());
    m1.set(0.1);
    m2.set(-0.1);

    if(e1.getPosition() >= 0){
      m1.set(0);
      m2.set(0);
    }
   }
}

