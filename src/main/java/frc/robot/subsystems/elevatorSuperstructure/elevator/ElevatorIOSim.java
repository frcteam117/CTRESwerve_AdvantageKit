// Copyright (c) 2021-2026 Littleton Robotics
// http://github.com/Mechanical-Advantage
//
// Use of this source code is governed by a BSD
// license that can be found in the LICENSE file
// at the root directory of this project.

package frc.robot.subsystems.elevatorSuperstructure.elevator;

import static edu.wpi.first.units.Units.Rotations;
import static edu.wpi.first.units.Units.Volts;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
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

public class ElevatorIOSim extends ElevatorMutIO {
  // it feels weird to init the real motors in the sim class???
  // TODO: is this safe???? its what was in the docs!
  private final TalonFX
      leaderTalon; // = new TalonFX(ElevatorConstants.leaderCANID, TunerConstants.kCANBus);
  // private final TalonFX
  //     followerTalon; // = new TalonFX(ElevatorConstants.followerCANID,
  // TunerConstants.kCANBus);

  private final DCMotorSim talonSimModel =
      new DCMotorSim(
          LinearSystemId.createDCMotorSystem(
              DCMotor.getKrakenX60Foc(1), 0.001, ElevatorConstants.gear_ratio),
          DCMotor.getKrakenX60Foc(1));
  // TalonFXSimState rightTalonFXSim;// = leaderTalon.getSimState();

  public ElevatorIOSim() {
    leaderTalon = new TalonFX(ElevatorConstants.leaderCANID, TunerConstants.kCANBus);
    // followerTalon = new TalonFX(ElevatorConstants.followerCANID, TunerConstants.kCANBus);

    // followerTalon.setControl(
    //     new Follower(ElevatorConstants.leaderCANID, MotorAlignmentValue.Aligned));

    // talonFXSim = leaderTalon.getSimState();

    var talonFXConfigs = new TalonFXConfiguration();

    // set slot 0 gains
    var slot0Configs = talonFXConfigs.Slot0;
    slot0Configs.kS = 0.25; // Add 0.25 V output to overcome static friction
    slot0Configs.kV = 0.12; // A velocity target of 1 rps results in 0.12 V output
    slot0Configs.kA = 0.01; // An acceleration of 1 rps/s requires 0.01 V output
    slot0Configs.kP = 4.8; // A position error of 2.5 rotations results in 12 V output
    slot0Configs.kI = 0; // no output for integrated error
    slot0Configs.kD = 0.1; // A velocity error of 1 rps results in 0.1 V output

    // set Motion Magic settings
    var motionMagicConfigs = talonFXConfigs.MotionMagic;
    motionMagicConfigs.MotionMagicCruiseVelocity = 80; // Target cruise velocity of 80 rps
    motionMagicConfigs.MotionMagicAcceleration =
        160; // Target acceleration of 160 rps/s (0.5 seconds)
    motionMagicConfigs.MotionMagicJerk = 1600; // Target jerk of 1600 rps/s/s (0.1 seconds)

    leaderTalon.getConfigurator().apply(talonFXConfigs);
    // followerTalon.getConfigurator().apply(talonFXConfigs);

    var talonFXSim = leaderTalon.getSimState();
    talonFXSim.Orientation = ChassisReference.CounterClockwise_Positive;
    talonFXSim.setMotorType(TalonFXSimState.MotorType.KrakenX60);
  }

  @Override
  public void updateInputs(ElevatorIOInputs inputs) {
    var talonFXSim = getTalonSimState();
    var talonSimModel = getTalonSimModel();

    // Update simulation state
    // elevatorSim.setInputVoltage(MathUtil.clamp(elevatorAppliedVolts, -12.0, 12.0));
    // elevatorSim.update(0.02);

    // inputs.connected = true;
    // inputs.positionRotations = talonFXSim.getPosition().getValue();
    // inputs.velocityRotationsPerSec = talonFXSim.getVelocity().getValue();
    // inputs.appliedVolts = talonFXSim.getSupplyVoltage().getValue();
    // // -> should these be thru the simModel? theres no way to get these values from the
    // TalonFXSimState class
    // inputs.currentAmps = Math.abs(talonFXSim.getSupplyCurrent().getValueAsDouble());
    // inputs.odometryTimestamps = new double[] {Timer.getFPGATimestamp()};
    // inputs.odometryPositionsRotations = new double[] {inputs.positionRotations};

    inputs.connected = true;
    inputs.positionRotations = talonSimModel.getAngularPosition().in(Rotations);
    // this is in RPS, is RPM better?
    inputs.velocityRotationsPerSec =
        talonSimModel.getAngularVelocityRPM() * 60; // convert RPM -> RPS
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

  //   @Override
  //   public void setOpenLoop(double output) {
  //     elevatorClosedLoop = false;
  //     elevatorAppliedVolts = output;
  //   }

  @Override
  public void setVelocity(double velocityRotationsPerSec) {
    VelocityVoltage velocityRequest = new VelocityVoltage(velocityRotationsPerSec);

    leaderTalon.setControl(velocityRequest);
  }

  @Override
  public void setPosition(double rotations) {
    // create a Motion Magic request, voltage output
    final MotionMagicVoltage m_request = new MotionMagicVoltage(0);

    // configure using gear ratios on real elevator, then make specific commands
    leaderTalon.setControl(m_request.withPosition(rotations));
  }

  // is this a safe way to do this? vvv
  public TalonFXSimState getTalonSimState() {
    return leaderTalon.getSimState();
  }

  public DCMotorSim getTalonSimModel() {
    return talonSimModel;
  }
}
// TODO: ask for help on the sim part, this IO structure isnt one im familiar
// with and idk what is right/wrong
