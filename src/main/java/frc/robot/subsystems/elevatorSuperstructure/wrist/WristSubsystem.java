package frc.robot.subsystems.elevatorSuperstructure.wrist;

import frc.robot.subsystems.elevatorSuperstructure.wrist.WristIO.WristInputs;
import org.littletonrobotics.junction.Logger;

public class WristSubsystem {
  private final WristIO io;
  private final WristMutInputsAutoLogged inputs = new WristMutInputsAutoLogged();

  public WristSubsystem(WristIO io) {
    this.io = io;
  }

  public void periodic() {
    io.updateInputs(inputs);
    Logger.processInputs("Wrist", inputs);
  }

  public WristInputs getInputs() {
    return inputs;
  }

  public WristIO getIO() {
    return io;
  }
}
