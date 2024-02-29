package frc.robot.subsystems;

import edu.wpi.first.math.filter.LinearFilter;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.resources.TecbotPWMLEDStrip;
import frc.robot.resources.UltrasonicThread;

public class RampSensorSubsystem extends SubsystemBase {
      private TecbotPWMLEDStrip ledStrip;
       private Ultrasonic m_rangeFinder;
       private UltrasonicThread ultrasonicThread;
       private DriverStation driverStation;

      private int state = 0;
      private double stateTimer = 0;
       private boolean doSense;
  
      LinearFilter filter;
      private double pastMatchTime = 0;
    public RampSensorSubsystem(){
        
        ledStrip = new TecbotPWMLEDStrip(9, 36);

        m_rangeFinder = new Ultrasonic(0, 1);
        ultrasonicThread = new UltrasonicThread(m_rangeFinder);
        Ultrasonic.setAutomaticMode(true);
        ultrasonicThread.start();
      filter = LinearFilter.movingAverage(3);
    }
        
    public void setNoteSensor(boolean doStart){
      doSense = doStart;
    }
  
      @Override
    public void periodic() {
      // This method will be called once per scheduler run
      int hue = 120;
      int saturation = 255;
      int value = 255;

      if(doSense){
        SmartDashboard.putBoolean("Ultrasonic", m_rangeFinder.isEnabled());
        SmartDashboard.putBoolean("Ultrasonic a", m_rangeFinder.isRangeValid());
        SmartDashboard.putNumber("Ultrasonic range", m_rangeFinder.getRangeInches());


        SmartDashboard.putNumber("Ultrasonic range", m_rangeFinder.getRangeInches());
        
        double v = filter.calculate(ultrasonicThread.getDistance());

        if(v <= 7){
            hue = 0;
        }else {
            if(v > 7 && v < 19  ){
                hue = 70 ;
            }else{
              hue = 120;
            }
        }
      }

      double matchTime  = DriverStation.getMatchTime();

      if( matchTime < 15 &&  matchTime > 12){
        blink(pastMatchTime-matchTime , hue, saturation, value);
      }else{
        
        if ( !doSense ){
            ledStrip.setSolidHSV(0, 0, 0);
        }else{
          ledStrip.setSolidHSV(hue, saturation, value);
        }
      }
      pastMatchTime = matchTime;
      
    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    NetworkTableEntry camerapose_targetspace = table.getEntry("camerapose_targetspace");
    
    Double values[] = new Double[]{0.0,0.0,0.0,0.0,0.0,0.0};
    values = camerapose_targetspace.getDoubleArray(values);

    SmartDashboard.putNumber("CAMERA X ", values[0]);
    SmartDashboard.putNumber("CAMERA Z ", values[2]);
    SmartDashboard.putNumber("Match time ", matchTime);


    }

    public void blink(double dt, int hue, int saturation, int value){
    
      if(stateTimer >= 10){
        // change state
        stateTimer = 0;
        if(state == 0){
          state = 1;

        }else{
          state = 0;
        }
      }
      SmartDashboard.putNumber("State ", state);
      SmartDashboard.putNumber("State timer ", stateTimer);
      stateTimer += dt;
      
      if( state == 0){
        
        ledStrip.setSolidHSV(hue, saturation, 0);

      }

      if( state == 1){
        ledStrip.setSolidHSV(120, saturation, value);
      }
     

    }
}
