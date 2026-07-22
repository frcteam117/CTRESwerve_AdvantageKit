package frc.robot.subsystems.elevatorSuperstructure.elevator;

import frc.robot.subsystems.elevatorSuperstructure.elevator.ElevatorIO.ElevatorIOInputs;
import org.littletonrobotics.junction.Logger;

public class ElevatorSubsystem {

  private final ElevatorIO io;
  // private final ElevatorIOInputs inputs = new ElevatorIOInputs();
  private final ElevatorIOMutInputsAutoLogged inputs = new ElevatorIOMutInputsAutoLogged();
  // we're just gonna trust that the other subsystems wont edit the io :3
  public ElevatorSubsystem(ElevatorIO io) {
    this.io = io;
  }

  public void periodic() {
    io.updateInputs(inputs);
    Logger.processInputs("Elevator", inputs);
  }

  public ElevatorIOInputs getIOInputs() {
    return inputs;
  }

  public ElevatorIO getIO() {
    return io;
  }
}
