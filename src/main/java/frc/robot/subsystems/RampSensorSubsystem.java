package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.resources.TecbotPWMLEDStrip;
import frc.robot.resources.UltrasonicThread;

public class RampSensorSubsystem extends SubsystemBase {
      private TecbotPWMLEDStrip ledStrip;
       private Ultrasonic m_rangeFinder;
       private UltrasonicThread ultrasonicThread;

       private boolean doSense;
  
    public RampSensorSubsystem(){
        
        ledStrip = new TecbotPWMLEDStrip(0, 20);

        m_rangeFinder = new Ultrasonic(0, 1);
        ultrasonicThread = new UltrasonicThread(m_rangeFinder);
        m_rangeFinder.setAutomaticMode(true);
        ultrasonicThread.start();
  
    }
        
    public void setNoteSensor(boolean doStart){
      doSense = doStart;
    }
  
      @Override
    public void periodic() {
      // This method will be called once per scheduler run
      if(doSense == true){
      
        SmartDashboard.putBoolean("Ultrasonic", m_rangeFinder.isEnabled());
        SmartDashboard.putBoolean("Ultrasonic a", m_rangeFinder.isRangeValid());
        SmartDashboard.putNumber("Ultrasonic range", m_rangeFinder.getRangeInches());


        SmartDashboard.putNumber("Ultrasonic range", m_rangeFinder.getRangeInches());

        if(ultrasonicThread.getDistance() >= 3){
          ledStrip.setSolidHSV(0, 255, 255);
        }
        else{
          ledStrip.setSolidHSV(90, 255, 255);
        }
      }

      

    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    NetworkTableEntry camerapose_targetspace = table.getEntry("camerapose_targetspace");
    
    Double values[] = new Double[]{0.0,0.0,0.0,0.0,0.0,0.0};
    values = camerapose_targetspace.getDoubleArray(values);

    SmartDashboard.putNumber("CAMERA X ", values[0]);
    SmartDashboard.putNumber("CAMERA Z ", values[2]);


    }
}
