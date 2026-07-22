package frc.robot.subsystems.elevatorSuperstructure.superstructure;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.subsystems.elevatorSuperstructure.elevator.*;

public class SuperstructureSubsystem extends SubsystemBase {
  // private final ArmSubsystem arm;
  private final ElevatorSubsystem elevator;
  // private final WristSubsystem wrist;

  public SuperstructureSubsystem() {
    switch (Constants.currentMode) {
      case REAL:
        elevator = new ElevatorSubsystem(new ElevatorIOTalonFX() {});
        // arm = new ArmSubsystem(new ArmIOTalonFX() {});
        // wrist = new WristSubsystem(new WristIOTalonFX() {});

        break;

      case SIM:
        elevator =
            new ElevatorSubsystem(
                new ElevatorIOSim()); // new ElevatorSubsystem(new ElevatorIOSim());
        // arm =
        //     new ArmSubsystem(
        //         new ArmIOSim());
        // wrist =
        //     new WristSubsystem(
        //         new WristIOSim());
        break;

      default:
        elevator = new ElevatorSubsystem(new ElevatorIO() {});
        // arm = new ArmSubsystem(new ArmIO() {});
        // wrist = new WristSubsystem(new WristIO() {});

        break;
    }
  }
  //
  @Override
  public void periodic() {
    // should this not be called every loop or called once at start?? ask!
    elevator.periodic();
    // arm.periodic();
    // wrist.periodic();
  }

  public Command ElevatorTop(SuperstructureSubsystem soup) {
    return soup.run(
        () -> {
          elevator.getIO().setPosition(ElevatorConstants.topRotations);
        });
  }

  public Command ElevatorMid(SuperstructureSubsystem soup) {
    return soup.run(
        () -> {
          elevator.getIO().setPosition(ElevatorConstants.midRotations);
        });
  }

  public Command ElevatorBottom(SuperstructureSubsystem soup) {
    return soup.run(
        () -> {
          elevator.getIO().setPosition(ElevatorConstants.bottomRotations);
        });
  }
}
