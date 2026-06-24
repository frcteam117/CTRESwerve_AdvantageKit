// Copyright (c) 2021-2026 Littleton Robotics
// http://github.com/Mechanical-Advantage
//
// Use of this source code is governed by a BSD
// license that can be found in the LICENSE file
// at the root directory of this project.

package frc.robot.subsystems.drive;

import static edu.wpi.first.units.Units.Radians;
import static edu.wpi.first.units.Units.RadiansPerSecond;
import static edu.wpi.first.units.Units.RotationsPerSecond;
import static edu.wpi.first.units.Units.Volts;

import com.ctre.phoenix6.configs.CANcoderConfiguration;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.swerve.SwerveModuleConstants;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.units.measure.Distance;
import edu.wpi.first.units.measure.LinearVelocity;
import edu.wpi.first.wpilibj.Alert;
import edu.wpi.first.wpilibj.Alert.AlertType;
import org.littletonrobotics.junction.Logger;

public class Module {
  private final ModuleIO io;
  private final ModuleIOInputsAutoLogged inputs = new ModuleIOInputsAutoLogged();
  private final int index;
  private final SwerveModuleConstants<
          TalonFXConfiguration, TalonFXConfiguration, CANcoderConfiguration>
      constants;

  private final Alert driveDisconnectedAlert;
  private final Alert turnDisconnectedAlert;
  private final Alert turnEncoderDisconnectedAlert;
  private SwerveModulePosition[] odometryPositions = new SwerveModulePosition[] {};

  public Module(
      ModuleIO io,
      int index,
      SwerveModuleConstants<TalonFXConfiguration, TalonFXConfiguration, CANcoderConfiguration>
          constants) {
    this.io = io;
    this.index = index;
    this.constants = constants;
    driveDisconnectedAlert =
        new Alert(
            "Disconnected drive motor on module " + Integer.toString(index) + ".",
            AlertType.kError);
    turnDisconnectedAlert =
        new Alert(
            "Disconnected turn motor on module " + Integer.toString(index) + ".", AlertType.kError);
    turnEncoderDisconnectedAlert =
        new Alert(
            "Disconnected turn encoder on module " + Integer.toString(index) + ".",
            AlertType.kError);
  }

  public void periodic() {
    io.updateInputs(inputs);
    Logger.processInputs("Drive/Module" + Integer.toString(index), inputs);

    // Calculate positions for odometry
    int sampleCount = inputs.odometryTimestamps.length; // All signals are sampled together
    odometryPositions = new SwerveModulePosition[sampleCount];
    for (int i = 0; i < sampleCount; i++) {
      double positionMeters = inputs.odometryDrivePositionsRad[i] * constants.WheelRadius;
      Rotation2d angle = inputs.odometryTurnPositions[i];
      odometryPositions[i] = new SwerveModulePosition(positionMeters, angle);
    }

    // Update alerts
    driveDisconnectedAlert.set(!inputs.driveConnected);
    turnDisconnectedAlert.set(!inputs.turnConnected);
    turnEncoderDisconnectedAlert.set(!inputs.turnEncoderConnected);
  }

  /** Runs the module with the specified setpoint state. Mutates the state to optimize it. */
  public void runSetpoint(SwerveModuleState state) {
    // Optimize velocity setpoint
    state.optimize(inputs.turnAbsolutePosition);
    state.cosineScale(inputs.turnAbsolutePosition);

    // Apply setpoints
    io.setDriveVelocity(
        RadiansPerSecond.of(state.speedMetersPerSecond / constants.WheelRadius)
            .minus(
                // TODO: use turn position instead of velocity if possible
                inputs.turnVelocity.times(
                    constants.CouplingGearRatio / constants.DriveMotorGearRatio)));
    io.setTurnPosition(state.angle);
  }

  /** Runs module with specified output volts while controlling to zero degrees. */
  public void runCharacterization(double output) {
    io.setDriveOpenLoop(Volts.of(output));
    io.setTurnPosition(Rotation2d.kZero);
  }

  /** Disables all outputs to motors. */
  public void stop() {
    io.setDriveOpenLoop(Volts.zero());
    io.setTurnOpenLoop(Volts.zero());
  }

  // /** Returns current turn angle of module. */
  // public Rotation2d getAngle() {
  //   return inputs.turnAbsolutePosition;
  // }

  /**
   * Returns current drive position of module as {@code Distance}. Accounts for coupling between
   * drive and turn.
   */
  public Distance getDrivePosition() {
    return (inputs.drivePosition.plus(
            inputs.turnPosition.times(constants.CouplingGearRatio / constants.DriveMotorGearRatio)))
        .timesConversionFactor(DriveConstants.WHEEL_RADIUS_CONVERSION_FACTOR);
  }

  /** Returns current drive velocity of module as LinearVelocity. */
  public LinearVelocity getDriveVelocity() {
    return inputs.driveVelocity.timesConversionFactor(
        DriveConstants.WHEEL_RADIUS_VELOCITY_CONVERSION_FACTOR);
  }

  /** Returns current module position (turn angle and drive position). */
  public SwerveModulePosition getPosition() {
    return new SwerveModulePosition(getDrivePosition(), inputs.turnAbsolutePosition);
  }

  /** Returns current module state (turn angle and drive velocity). */
  public SwerveModuleState getState() {
    return new SwerveModuleState(getDriveVelocity(), inputs.turnAbsolutePosition);
  }

  /** Returns module positions received this cycle. */
  public SwerveModulePosition[] getOdometryPositions() {
    return odometryPositions;
  }

  /** Returns timestamps of samples received this cycle. */
  public double[] getOdometryTimestamps() {
    return inputs.odometryTimestamps;
  }

  /** Returns module position in radians. */
  public double getWheelRadiusCharacterizationPosition() {
    return inputs.drivePosition.in(Radians);
  }

  /** Returns module velocity in rotations/sec (Phoenix native units). */
  public double getFFCharacterizationVelocity() {
    return inputs.driveVelocity.in(RotationsPerSecond);
  }
}
