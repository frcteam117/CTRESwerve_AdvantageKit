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

    // Logger.recordOutput(ArmConstants.tuningLogStart + "kS", ArmConstants.tunable_kS);
    // ArmConstants.talonFXConfigs.Slot0.kS = ArmConstants.tunable_kS.getAsDouble();
    // ArmConstants.talonFXConfigs.Slot0.kV = ArmConstants.tunable_kV.getAsDouble();
    // ArmConstants.talonFXConfigs.Slot0.kA = ArmConstants.tunable_kA.getAsDouble();
    // ArmConstants.talonFXConfigs.Slot0.kP = ArmConstants.tunable_kP.getAsDouble();
    // ArmConstants.talonFXConfigs.Slot0.kD = ArmConstants.tunable_kD.getAsDouble();
    // ArmConstants.talonFXConfigs.Slot0.kG = ArmConstants.tunable_kG.getAsDouble();

    // io.updateConfig();
  }

  public ArmInputs getInputs() {
    return inputs;
  }

  public ArmIO getIO() {
    return io;
  }

  public void setNextPosition(double safeRotations) {
    inputs.nextPositionRotations = safeRotations;
  }
}
