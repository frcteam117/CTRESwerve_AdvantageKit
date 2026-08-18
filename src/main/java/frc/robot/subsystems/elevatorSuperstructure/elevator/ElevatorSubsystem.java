package frc.robot.subsystems.elevatorSuperstructure.elevator;

import frc.robot.subsystems.elevatorSuperstructure.elevator.ElevatorIO.ElevatorInputs;
import org.littletonrobotics.junction.Logger;

public class ElevatorSubsystem {

  private final ElevatorIO io;
  // private final ElevatorIOInputs inputs = new ElevatorIOInputs();
  private final ElevatorMutInputsAutoLogged inputs = new ElevatorMutInputsAutoLogged();
  // we're just gonna trust that the other subsystems wont edit the io :3
  public ElevatorSubsystem(ElevatorIO io) {
    this.io = io;
  }

  public void periodic() {
    io.updateInputs(inputs);
    Logger.processInputs("Elevator", inputs);

    ElevatorConstants.talonFXConfigs.Slot0.kS = ElevatorConstants.tunable_kS.getAsDouble();
    ElevatorConstants.talonFXConfigs.Slot0.kV = ElevatorConstants.tunable_kV.getAsDouble();
    ElevatorConstants.talonFXConfigs.Slot0.kA = ElevatorConstants.tunable_kA.getAsDouble();
    ElevatorConstants.talonFXConfigs.Slot0.kP = ElevatorConstants.tunable_kP.getAsDouble();
    ElevatorConstants.talonFXConfigs.Slot0.kD = ElevatorConstants.tunable_kD.getAsDouble();
    ElevatorConstants.talonFXConfigs.Slot0.kG = ElevatorConstants.tunable_kG.getAsDouble();

    io.updateConfig();
  }

  public ElevatorInputs getInputs() {
    return inputs;
  }

  public ElevatorIO getIO() {
    return io;
  }

  public void setNextPosition(double safeRotations) {
    inputs.nextPositionRotations = safeRotations;
  }
}
