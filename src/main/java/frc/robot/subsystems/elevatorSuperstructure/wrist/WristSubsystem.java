package frc.robot.subsystems.elevatorSuperstructure.wrist;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.subsystems.wrist.WristIOInputsAutoLogged;

import org.littletonrobotics.junction.Logger;

public class WristSubsystem {
   private final WristIO io;
   private final WristIOInputsAutoLogged inputs = new WristIOInputsAutoLogged();

   public WristSubsystem(WristIO io) {
    this.io = io;
   }

   @Override
   public void periodic() {
    io.updateInputs(inputs);
    Logger.processInputs("Wrist", inputs);
   }

   //change all of this later
   public Command WristUp {
    return wrist.run(
        () -> {
          io.setPosition(100);
        });
   }
}

