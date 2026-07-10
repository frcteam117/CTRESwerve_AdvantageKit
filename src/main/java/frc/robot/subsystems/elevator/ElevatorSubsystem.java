package frc.robot.subsystems.elevator;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.subsystems.elevator.ElevatorIO.ElevatorIOInputs;

public class ElevatorSubsystem extends SubsystemBase {

  // change to get IDs from constants once ported into actual repo vvv
  private final ElevatorIO io;
  private final ElevatorIOInputs inputs = new ElevatorIOInputs();

  public ElevatorSubsystem(ElevatorIO io) {
    this.io = io;
  }

  public void periodic() {
    io.updateInputs(inputs);
  }

  // TODO: ADJUST ONCE GEAR RATIOS ARE KNOWN!!!
  // also idk if requesting every loop even when not moving is best? but we shall find out
  public Command ElevatorUp(ElevatorSubsystem elevator) {
    return elevator.run(
        () -> {
          io.setPosition(100);
        });
  }

  public Command ElevatorMid(ElevatorSubsystem elevator) {
    return elevator.run(
        () -> {
          io.setPosition(50);
        });
  }

  public Command ElevatorDown(ElevatorSubsystem elevator) {
    return elevator.run(
        () -> {
          io.setPosition(0);
        });
  }
}
