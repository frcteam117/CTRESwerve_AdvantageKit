package frc.robot.subsystems.elevator;

import static edu.wpi.first.units.Units.Volts;

import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import org.littletonrobotics.junction.Logger;

public class ElevatorSubsystem extends SubsystemBase {

  // change to get IDs from constants once ported into actual repo vvv
  private final ElevatorIO io;
  // private final ElevatorIOInputs inputs = new ElevatorIOInputs();
  private final ElevatorIOInputsAutoLogged inputs = new ElevatorIOInputsAutoLogged();

  public ElevatorSubsystem(ElevatorIO io) {
    this.io = io;
  }

  @Override
  public void periodic() {
    io.updateInputs(inputs);
    Logger.processInputs("Elevator", inputs);
  }

  @Override
  public void simulationPeriodic() {
    io.updateInputs(inputs);
    Logger.processInputs("Elevator", inputs);
    var talonFXSim = io.getTalonSimState();
    var talonSimModel = io.getTalonSimModel();

    // set the supply voltage of the TalonFX
    talonFXSim.setSupplyVoltage(RobotController.getBatteryVoltage());

    // get the motor voltage of the TalonFX
    var motorVoltage = talonFXSim.getMotorVoltageMeasure();

    // use the motor voltage to calculate new position and velocity
    // using WPILib's DCMotorSim class for physics simulation
    talonSimModel.setInputVoltage(motorVoltage.in(Volts));
    talonSimModel.update(0.020); // assume 20 ms loop time

    // apply the new rotor position and velocity to the TalonFX;
    // note that this is rotor position/velocity (before gear ratio), but
    // DCMotorSim returns mechanism position/velocity (after gear ratio)
    talonFXSim.setRawRotorPosition(
        talonSimModel.getAngularPosition().times(ElevatorConstants.gear_ratio));
    talonFXSim.setRotorVelocity(
        talonSimModel.getAngularVelocity().times(ElevatorConstants.gear_ratio));
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

  public Command Stop(ElevatorSubsystem elevator) {
    return elevator.run(
        () -> {
          io.setVelocity(0);
        });
  }
}
