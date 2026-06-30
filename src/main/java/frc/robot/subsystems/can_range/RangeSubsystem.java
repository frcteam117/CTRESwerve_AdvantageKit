package frc.robot.subsystems.can_range;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.subsystems.can_range.RangeIO.RangeIOInputs;

public class RangeSubsystem extends SubsystemBase {
  RangeIO rangeIO = null;

  public RangeSubsystem(RangeIO rangeIO) {
    this.rangeIO = rangeIO;
  }

  @Override
  public void periodic() {
    RangeIOInputs inputs = new RangeIOInputs();
    rangeIO.updateInputs(inputs);
  }
}
