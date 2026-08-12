package frc.robot.subsystems.elevatorSuperstructure.arm;

import static edu.wpi.first.units.Units.Rotations;
import static edu.wpi.first.units.Units.Volts;

import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.controls.VelocityVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.sim.ChassisReference;
import com.ctre.phoenix6.sim.TalonFXSimState;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.math.system.plant.LinearSystemId;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.simulation.DCMotorSim;
import frc.robot.TunerConstants;

public class ArmIOSim implements ArmIO {
  private final TalonFX talon;

  private final DCMotorSim talonSimModel =
      new DCMotorSim(
          LinearSystemId.createDCMotorSystem(
              DCMotor.getKrakenX60Foc(1), ArmConstants.mechanismMOI, ArmConstants.gear_ratio),
          DCMotor.getKrakenX60Foc(1));

  public ArmIOSim() {
    talon = new TalonFX(ArmConstants.CANID, TunerConstants.kCANBus);
    talon.getConfigurator().apply(ArmConstants.talonFXConfigs);
    var talonFXSim = talon.getSimState();
    talonFXSim.Orientation = ChassisReference.CounterClockwise_Positive;
    talonFXSim.setMotorType(TalonFXSimState.MotorType.KrakenX60);
  }

  @Override
  public void updateInputs(ArmMutInputs inputs) {
    var talonFXSim = getTalonSimState();
    var talonSimModel = getTalonSimModel();

    inputs.connected = true;
    inputs.positionRotations = talonSimModel.getAngularPosition().in(Rotations);
    // this is in RPS, is RPM better?
    inputs.velocityRotationsPerSec =
        talonSimModel.getAngularVelocityRPM() / 60; // convert RPM -> RPS
    // the interwebs says appliedVolts is the same as InputVoltage here, i hope it's right
    inputs.appliedVolts = talonSimModel.getInputVoltage();
    inputs.currentAmps = Math.abs(talonSimModel.getCurrentDrawAmps());
    inputs.odometryTimestamps = new double[] {Timer.getFPGATimestamp()};
    inputs.odometryPositionsRotations = new double[] {inputs.positionRotations};
    //

    // set the supply voltage of the TalonFX
    talonFXSim.setSupplyVoltage(RobotController.getBatteryVoltage());

    // get the motor voltage of the TalonFX
    var motorVoltage = talonFXSim.getMotorVoltageMeasure();

    // use the motor voltage to calculate new position and velocity
    // using WPILib's DCMotorSim class for physics simulation
    if (inputs.positionRotations > ArmConstants.maxRotations) {
      inputs.positionRotations = ArmConstants.maxRotations;
      talonSimModel.setInputVoltage(0);
    } else if (inputs.positionRotations < ArmConstants.minRotations) {
      inputs.positionRotations = ArmConstants.minRotations;
      talonSimModel.setInputVoltage(0);
    } else {
      talonSimModel.setInputVoltage(motorVoltage.in(Volts));
    }
    talonSimModel.update(0.020); // assume 20 ms loop time
    talonSimModel.setInputVoltage(motorVoltage.in(Volts));

    // apply the new rotor position and velocity to the TalonFX;
    // note that this is rotor position/velocity (before gear ratio), but
    // DCMotorSim returns mechanism position/velocity (after gear ratio)
    talonFXSim.setRawRotorPosition(
        talonSimModel.getAngularPosition().times(ArmConstants.gear_ratio));
    talonFXSim.setRotorVelocity(talonSimModel.getAngularVelocity().times(ArmConstants.gear_ratio));
  }

  //   @Override
  //   public void setOpenLoop(double output) {
  //     elevatorClosedLoop = false;
  //     elevatorAppliedVolts = output;
  //   }

  @Override
  public void setVelocity(double velocityRotationsPerSec) {
    VelocityVoltage velocityRequest = new VelocityVoltage(velocityRotationsPerSec);

    talon.setControl(velocityRequest);
  }

  @Override
  public void setPosition(double rotations) {

    final MotionMagicVoltage m_request = new MotionMagicVoltage(0);
    // configure using gear ratios on real elevator, then make specific commands
    talon.setControl(m_request.withPosition(rotations));
  }

  // is this a safe way to do this? vvv
  public TalonFXSimState getTalonSimState() {
    return talon.getSimState();
  }

  public DCMotorSim getTalonSimModel() {
    return talonSimModel;
  }

  @Override
  public void updateConfig() {
    talon.getConfigurator().apply(ArmConstants.talonFXConfigs);
  }
}
