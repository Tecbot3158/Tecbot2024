package frc.robot;

import java.security.interfaces.RSAMultiPrimePrivateCrtKey;

import edu.wpi.first.wpilibj.PS4Controller.Button;
import frc.robot.commands.RampAction;
import frc.robot.commands.OffL;
/*import frc.robot.commands.S1F;
import frc.robot.commands.S2F;
import frc.robot.commands.S3F;*/
import frc.robot.commands.OnL;
import frc.robot.commands.OnR;
import frc.robot.commands.RampAction;
/*import frc.robot.commands.S1B;
import frc.robot.commands.S2B;
import frc.robot.commands.S3B;*/
import frc.robot.resources.TecbotConstants;
import frc.robot.resources.TecbotController;
import frc.robot.resources.TecbotController.ButtonType;
import frc.robot.resources.TecbotController.TypeOfController;

public class OI {
    public static OI instance;
    private TecbotController pilot;
    public RobotContainer robotContainer;

    public OI(RobotContainer rc){
        pilot = new TecbotController(0, TypeOfController.XBOX);
        robotContainer = rc;
    }


    public void configureButtonBindings(){
        pilot.whileHeld(ButtonType.A, new RampAction(robotContainer.getRampSubsystem(), 0, 0, 0.1));
        pilot.whileHeld(ButtonType.B, new RampAction(robotContainer.getRampSubsystem(), 0.4, 0.4, 0.1));



        /*pilot.whenPressed(ButtonType.LB, new S3F());
        pilot.whenPressed(ButtonType.POV_UP, new S3B());*/
      


    }
}
