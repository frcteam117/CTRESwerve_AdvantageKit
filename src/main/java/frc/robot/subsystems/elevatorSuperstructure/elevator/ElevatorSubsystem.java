package frc.robot.subsystems.elevatorSuperstructure.elevator;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.subsystems.elevator.ElevatorIOInputsAutoLogged;

import org.littletonrobotics.junction.Logger;

public class ElevatorSubsystem {

  private final ElevatorIO io;
  // private final ElevatorIOInputs inputs = new ElevatorIOInputs();
  private final ElevatorIOInputsAutoLogged inputs = new ElevatorIOInputsAutoLogged();
// we're just gonna trust that the other subsystems wont edit the io :3
  public ElevatorSubsystem(ElevatorIO io) {
    this.io = io;
  }

  public void periodic() {
    io.updateInputs(inputs);
    Logger.processInputs("Elevator", inputs);
  }

  public ElevatorIO getIO() { // READ ONLY PRETTY PLS!!!
    return io;
  }
  // TODO: ADJUST ONCE GEAR RATIOS ARE KNOWN!!!
  // also idk if requesting every loop even when not moving is best? but we shall find out
  // public Command ElevatorUp(ElevatorSubsystem elevator) {
  //   return elevator.run(
  //       () -> {
  //         io.setPosition(100);
  //       });
  // }

  // public Command ElevatorMid(ElevatorSubsystem elevator) {
  //   return elevator.run(
  //       () -> {
  //         io.setPosition(50);
  //       });
  // }

  // public Command ElevatorDown(ElevatorSubsystem elevator) {
  //   return elevator.run(
  //       () -> {
  //         io.setPosition(0);
  //       });
  // }

  // public Command Stop(ElevatorSubsystem elevator) {
  //   return elevator.run(
  //       () -> {
  //         io.setVelocity(0);
  //       });
  // }
}
