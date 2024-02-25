package frc.robot.resources;

import edu.wpi.first.wpilibj.Ultrasonic;

public class UltrasonicThread extends Thread{
    
    private Ultrasonic ultrasonicSensor;
    private double distance;

    public UltrasonicThread(Ultrasonic sensor){
        this.ultrasonicSensor = sensor;
    }

    public void run(){
        while(!Thread.interrupted()){
           distance = ultrasonicSensor.getRangeInches(); 

           try{
            Thread.sleep(10);
           }catch (InterruptedException e){
            break;
           }
        }     
    }

    public double getDistance(){
        return distance;
    }
}
