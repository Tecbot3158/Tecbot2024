package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.resources.TecbotPWMLEDStrip;

public class RampSensorSubsystem extends SubsystemBase {
      private TecbotPWMLEDStrip ledStrip;
       private Ultrasonic m_rangeFinder;

       private boolean doSense;
  
    public RampSensorSubsystem(){
        
        ledStrip = new TecbotPWMLEDStrip(0, 20);
        m_rangeFinder = new Ultrasonic(0, 1);
        m_rangeFinder.setAutomaticMode(true);
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

      if(m_rangeFinder.getRangeInches() >= 3){
        ledStrip.setSolidHSV(0, 255, 255);
      }
      else{
        ledStrip.setSolidHSV(90, 255, 255);
      }
      }
    }
}
