package frc.robot;

import edu.wpi.first.wpilibj.PS4Controller.Button;
import frc.robot.commands.EditableIntakeVelocity;
import frc.robot.commands.EditableShooterVelocity;
/*import frc.robot.commands.S1F;
import frc.robot.commands.S2F;
import frc.robot.commands.S3F;*/
import frc.robot.commands.OffShooter;
import frc.robot.commands.OnShooter;
import frc.robot.commands.OnShooter2;
import frc.robot.commands.OnShooter3;
/*import frc.robot.commands.S1B;
import frc.robot.commands.S2B;
import frc.robot.commands.S3B;*/
import frc.robot.resources.TecbotConstants;
import frc.robot.resources.TecbotController;
import frc.robot.resources.TecbotController.ButtonType;
import frc.robot.resources.TecbotController.TypeOfController;
import frc.robot.subsystems.Intake;

public class OI {
    public static OI instance;
    private static TecbotController pilot;
    public static Intake intake;

    public OI(){
        intake = new Intake();
        pilot = new TecbotController(0, TypeOfController.XBOX);
    }

    public void configureButtonBindings(){
        pilot.whenPressed(ButtonType.A, new OnShooter2());
        pilot.whenPressed(ButtonType.B, new OffShooter());
        pilot.whenPressed(ButtonType.X, new OnShooter3());
        pilot.whenPressed(ButtonType.Y, new OnShooter());
        pilot.whenPressed(ButtonType.POV_DOWN, new EditableShooterVelocity());


        /*pilot.whenPressed(ButtonType.LB, new S3F());
        pilot.whenPressed(ButtonType.POV_UP, new S3B());*/
      


    }

    public static TecbotController getPilot(){
        return pilot;
    }

    public static OI getInstance() {
        if (instance == null)
            instance = new OI();
  
        return instance;
    }
}
