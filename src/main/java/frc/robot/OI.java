package frc.robot;

import java.security.interfaces.RSAMultiPrimePrivateCrtKey;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.PS4Controller.Button;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.commands.RampAction;
import frc.robot.commands.Sequence1;
import frc.robot.commands.Sequence2;
import frc.robot.commands.IntakeIn;
import frc.robot.commands.OffL;
/*import frc.robot.commands.S1F;
import frc.robot.commands.S2F;
import frc.robot.commands.S3F;*/
import frc.robot.commands.OnL;
import frc.robot.commands.StopMotor;
import frc.robot.commands.ZeroGyroCommand;
import frc.robot.commands.RampAction;
/*import frc.robot.commands.S1B;
import frc.robot.commands.S2B;
import frc.robot.commands.S3B;*/
import frc.robot.resources.TecbotConstants;

public class OI {
    public static OI instance;
    private CommandXboxController pilot, copilot;
    public RobotContainer robotContainer;

    public OI(RobotContainer rc, CommandXboxController c1, CommandXboxController c2){
        pilot = c1;
        copilot = c2;
        robotContainer = rc;
    }

    public void configureButtonBindings(){
        //pilot.whileTrue(ButtonType.A, new RampAction(robotContainer.getRampSubsystem(), 0, 0, 0.1));
        // pilot.whileHeld(ButtonType.B, new RampAction(robotContainer.getRampSubsystem(), 0.4, 0.4, 0.1));
        pilot.rightBumper().whileTrue( new Sequence1(robotContainer.getRampSubsystem(), 0.8, 10 ));
        pilot.leftBumper().whileTrue( new Sequence2(robotContainer.getRampSubsystem(), 1, 10));
        pilot.b().whileTrue( new RampAction(robotContainer.getRampSubsystem(),-0.3, 0.3, 0.0)  );
        pilot.y().whileTrue( new RampAction(robotContainer.getRampSubsystem(), 0.6, -1, 0)  );
       // pilot.button(6).whileTrue(new ZeroGyroCommand(robotContainer.getDriveTrain()));

        pilot.x().whileTrue(new IntakeIn());

        copilot.a().whileTrue(new RampAction(robotContainer.getRampSubsystem(), .6, -1, 0));
        copilot.b().whileTrue(new RampAction(robotContainer.getRampSubsystem(), .08, -.31, 0));
        copilot.x().whileTrue(new RampAction(robotContainer.getRampSubsystem(), 0.55, -.95, 0));
    
        /*pilot.whenPressed(ButtonType.LB, new S3F());
        pilot.whenPressed(ButtonType.POV_UP, new S3B());*/
      


    }
}
