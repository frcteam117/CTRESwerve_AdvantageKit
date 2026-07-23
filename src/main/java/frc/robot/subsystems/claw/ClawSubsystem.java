package frc.robot.subsystems.claw;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import org.littletonrobotics.junction.Logger;

public class ClawSubsystem extends SubsystemBase {

  // change to get IDs from constants once ported into actual repo vvv
  private final ClawIO io;
  // private final ElevatorIOInputs inputs = new ElevatorIOInputs();
  private final ClawIOInputsAutoLogged inputs = new ClawIOInputsAutoLogged();

  public ClawSubsystem(ClawIO io) {
    this.io = io;
  }

  public void periodic() {
    io.updateInputs(inputs);
    Logger.processInputs("Claw", inputs);
  }

  // TODO: ADJUST ONCE GEAR RATIOS ARE KNOWN!!!
  // also idk if requesting every loop even when not moving is best? but we shall find out
}
