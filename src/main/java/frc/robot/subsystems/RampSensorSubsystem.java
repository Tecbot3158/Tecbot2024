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

      private double pastMatchTime = 0;
    public RampSensorSubsystem(){


    }
        
    public void setNoteSensor(boolean doStart){
      doSense = doStart;
    }

      @Override
    public void periodic() {
     
    }

}
