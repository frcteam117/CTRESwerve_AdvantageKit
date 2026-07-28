package frc.robot.subsystems.elevatorSuperstructure.arm;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.subsystems.arm.ArmIOInputsAutoLogged;

import org.littletonrobotics.junction.Logger;

public class ArmSubsystem {
   private final ArmIO io;
   private final ArmIOInputsAutoLogged inputs = new ArmIOInputsAutoLogged();

   public ArmSubsystem(ArmIO io) {
    this.io = io;
   }

   @Override
   public void periodic() {
    io.updateInputs(inputs);
    Logger.processInputs("Arm", inputs);
   }

   //change all of this later
   public Command ArmUp {
    return arm.run(
        () -> {
          io.setPosition(100);
        });
   }
}
