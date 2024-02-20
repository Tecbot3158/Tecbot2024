package frc.robot;

import java.security.interfaces.RSAMultiPrimePrivateCrtKey;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.PS4Controller.Button;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.commands.RampAction;
import frc.robot.commands.Sequence1;
import frc.robot.commands.Sequence2;
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

public class OI {
    public static OI instance;
    private CommandXboxController pilot;
    public RobotContainer robotContainer;

    public OI(RobotContainer rc, CommandXboxController c1){
        pilot = c1;
        robotContainer = rc;
    }


    public void configureButtonBindings(){
        //pilot.whileTrue(ButtonType.A, new RampAction(robotContainer.getRampSubsystem(), 0, 0, 0.1));
        // pilot.whileHeld(ButtonType.B, new RampAction(robotContainer.getRampSubsystem(), 0.4, 0.4, 0.1));
        pilot.a().whileTrue( new Sequence1());
        pilot.x().whileTrue( new Sequence2());
        pilot.b().whileTrue( new RampAction(robotContainer.getRampSubsystem(), 0., 0, 0.5)  );
        pilot.y().whileTrue( new RampAction(robotContainer.getRampSubsystem(), 0.6, -1, 0)  );
        /*pilot.whenPressed(ButtonType.LB, new S3F());
        pilot.whenPressed(ButtonType.POV_UP, new S3B());*/
      


    }
}
