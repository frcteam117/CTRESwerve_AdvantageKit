package frc.robot.subsystems.elevatorSuperstructure.wrist;

import frc.robot.subsystems.elevatorSuperstructure.wrist.WristIO.WristInputs;
import org.littletonrobotics.junction.Logger;

public class WristSubsystem {
  private final WristIO io;
  private final WristMutInputsAutoLogged inputs = new WristMutInputsAutoLogged();
  public double nextPositionRotations = 0.0;

  public WristSubsystem(WristIO io) {
    this.io = io;
  }

  public void periodic() {
    io.updateInputs(inputs);
    Logger.processInputs("Wrist", inputs);

    // WristConstants.talonFXConfigs.Slot0.kS = WristConstants.tunable_kS.getAsDouble();
    // WristConstants.talonFXConfigs.Slot0.kV = WristConstants.tunable_kV.getAsDouble();
    // WristConstants.talonFXConfigs.Slot0.kA = WristConstants.tunable_kA.getAsDouble();
    // WristConstants.talonFXConfigs.Slot0.kP = WristConstants.tunable_kP.getAsDouble();
    // WristConstants.talonFXConfigs.Slot0.kD = WristConstants.tunable_kD.getAsDouble();
    // WristConstants.talonFXConfigs.Slot0.kG = WristConstants.tunable_kG.getAsDouble();

    // io.updateConfig();
  }

  public WristInputs getInputs() {
    return inputs;
  }

  public WristIO getIO() {
    return io;
  }

  public void setNextPosition(double safeRotations) {
    inputs.nextPositionRotations = safeRotations;
  }
}
