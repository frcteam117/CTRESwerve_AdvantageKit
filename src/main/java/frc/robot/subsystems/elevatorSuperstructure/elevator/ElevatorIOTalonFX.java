// Copyright (c) 2021-2026 Littleton Robotics
// http://github.com/Mechanical-Advantage
//
// Use of this source code is governed by a BSD
// license that can be found in the LICENSE file
// at the root directory of this project.

package frc.robot.subsystems.elevatorSuperstructure.elevator;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.controls.VelocityVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.MotorAlignmentValue;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.TunerConstants;

public class ElevatorIOTalonFX implements ElevatorIO {
  private final TalonFX
      leaderTalon; // = new TalonFX(ElevatorConstants.leaderCANID, TunerConstants.kCANBus);
  private final TalonFX
      followerTalon; // = new TalonFX(ElevatorConstants.followerCANID, TunerConstants.kCANBus);

  public ElevatorIOTalonFX() {
    leaderTalon = new TalonFX(ElevatorConstants.leaderCANID, TunerConstants.kCANBus);
    followerTalon = new TalonFX(ElevatorConstants.followerCANID, TunerConstants.kCANBus);

    followerTalon.setControl(
        new Follower(ElevatorConstants.leaderCANID, MotorAlignmentValue.Aligned));

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
    followerTalon.getConfigurator().apply(talonFXConfigs);
  }

  @Override
  public void updateInputs(ElevatorIOMutInputs inputs) {

    // Update simulation state
    // elevatorSim.setInputVoltage(MathUtil.clamp(elevatorAppliedVolts, -12.0, 12.0));
    // elevatorSim.update(0.02);

    // Update elevator inputs
    // should these all be doubles bc its simpler?
    inputs.connected = true;
    inputs.positionRotations = leaderTalon.getPosition().getValueAsDouble();
    inputs.velocityRotationsPerSec = leaderTalon.getVelocity().getValueAsDouble();
    inputs.appliedVolts = leaderTalon.getSupplyVoltage().getValueAsDouble();
    inputs.currentAmps = Math.abs(leaderTalon.getSupplyCurrent().getValueAsDouble());
    // TODO: does the encoder need separate stuff if its internal?
    // inputs.encoderConnected = true;
    // inputs.absolutePosition = new Rotation2d(leaderTalon.getAngularPositionRad());

    // Update odometry inputs (50Hz because high-frequency odometry in sim doesn't
    // matter)
    inputs.odometryTimestamps = new double[] {Timer.getFPGATimestamp()};
    inputs.odometryPositionsRotations = new double[] {inputs.positionRotations};
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
}
