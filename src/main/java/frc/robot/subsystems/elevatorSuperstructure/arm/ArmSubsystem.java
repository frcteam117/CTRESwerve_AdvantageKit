package frc.robot.subsystems.elevatorSuperstructure.arm;

import frc.robot.subsystems.elevatorSuperstructure.arm.ArmIO.ArmInputs;
import org.littletonrobotics.junction.Logger;

public class ArmSubsystem {
  private final ArmIO io;
  private final ArmMutInputsAutoLogged inputs = new ArmMutInputsAutoLogged();

  public ArmSubsystem(ArmIO io) {
    this.io = io;
  }

  public void periodic() {
    io.updateInputs(inputs);
    Logger.processInputs("Arm", inputs);
  }

  public ArmInputs getInputs() {
    return inputs;
  }

  public ArmIO getIO() {
    return io;
  }
}
