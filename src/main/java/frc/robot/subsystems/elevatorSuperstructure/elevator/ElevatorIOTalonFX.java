// Copyright (c) 2021-2026 Littleton Robotics
// http://github.com/Mechanical-Advantage
//
// Use of this source code is governed by a BSD
// license that can be found in the LICENSE file
// at the root directory of this project.

package frc.robot.subsystems.elevatorSuperstructure.elevator;

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

    leaderTalon.getConfigurator().apply(ElevatorConstants.talonFXConfigs);
    followerTalon.getConfigurator().apply(ElevatorConstants.talonFXConfigs);
  }

  @Override
  public void updateInputs(ElevatorMutInputs inputs) {

    // Update elevator inputs
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
