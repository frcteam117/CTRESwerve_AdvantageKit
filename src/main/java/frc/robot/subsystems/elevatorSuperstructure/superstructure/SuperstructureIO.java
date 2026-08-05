package frc.robot.subsystems.elevatorSuperstructure.superstructure;

import frc.robot.subsystems.elevatorSuperstructure.arm.ArmIO.ArmInputs;
import frc.robot.subsystems.elevatorSuperstructure.elevator.ElevatorIO.ElevatorInputs;
import frc.robot.subsystems.elevatorSuperstructure.wrist.WristIO.WristInputs;
import lombok.Getter;

public class SuperstructureIO {
  @Getter
  public static class SuperstructureInputs {
    private final ElevatorInputs elevatorInputs;
    private final ArmInputs armInputs;
    private final WristInputs wristInputs;

    public SuperstructureInputs(
        ElevatorInputs elevatorInputs, ArmInputs armInputs, WristInputs wristInputs) {
      this.elevatorInputs = elevatorInputs;
      this.armInputs = armInputs;
      this.wristInputs = wristInputs;
    }

    // TODO: add forward kinematics getter methods
  }
}
