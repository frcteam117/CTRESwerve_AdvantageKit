// Copyright (c) 2021-2026 Littleton Robotics
// http://github.com/Mechanical-Advantage
//
// Use of this source code is governed by a BSD
// license that can be found in the LICENSE file
// at the root directory of this project.

package frc.robot.subsystems.drive;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.math.util.Units;
import frc.robot.generated.TunerConstants;
import lombok.Getter;
import org.littletonrobotics.junction.AutoLog;

public interface ModuleIO {
  public static interface ModuleInputs {
    public boolean isDriveConnected();

    public double getDrivePositionRad();

    public double getDriveVelocityRadPerSec();

    public double getDriveAppliedVolts();

    public double getDriveCurrentAmps();

    public boolean isTurnConnected();

    public boolean isTurnEncoderConnected();

    public Rotation2d getTurnAbsolutePosition();

    public Rotation2d getTurnPosition();

    public double getTurnVelocityRadPerSec();

    public double getTurnAppliedVolts();

    public double getTurnCurrentAmps();

    public double[] getOdometryTimestamps();

    public double[] getOdometryDrivePositionsRad();

    public Rotation2d[] getOdometryTurnPositions();

    /** Returns the current drive position of the module in meters. */
    public default double getPositionMeters() {
      return (getDrivePositionRad()
              + (getTurnPosition().getRadians()
                  * TunerConstants.BackLeft.CouplingGearRatio
                  / TunerConstants.BackLeft.DriveMotorGearRatio))
          * TunerConstants.BackLeft.WheelRadius;
    }

    /** Returns the current drive velocity of the module in meters per second. */
    public default double getVelocityMetersPerSec() {
      return getDriveVelocityRadPerSec() * TunerConstants.BackLeft.WheelRadius;
    }

    /** Returns the module position (turn angle and drive position). */
    public default SwerveModulePosition getPosition() {
      return new SwerveModulePosition(getPositionMeters(), getTurnAbsolutePosition());
    }

    /** Returns the module state (turn angle and drive velocity). */
    public default SwerveModuleState getState() {
      return new SwerveModuleState(getVelocityMetersPerSec(), getTurnAbsolutePosition());
    }

    /** Returns the module positions received this cycle. */
    public default SwerveModulePosition[] getOdometryPositions() {
      final int sampleCount = getOdometryTimestamps().length;
      SwerveModulePosition[] odometryPositions = new SwerveModulePosition[sampleCount];
      for (int i = 0; i < sampleCount; i++) {
        double positionMeters =
            (getOdometryDrivePositionsRad()[i]
                    + (getOdometryTurnPositions()[i].getRadians()
                        * TunerConstants.BackLeft.CouplingGearRatio
                        / TunerConstants.BackLeft.DriveMotorGearRatio))
                * TunerConstants.BackLeft.WheelRadius;
        Rotation2d angle = getOdometryTurnPositions()[i];
        odometryPositions[i] = new SwerveModulePosition(positionMeters, angle);
      }
      return odometryPositions;
    }

    /** Returns the module velocity in rotations/sec (Phoenix native units). */
    public default double getFFCharacterizationVelocity() {
      return Units.radiansToRotations(getDriveVelocityRadPerSec());
    }
  }

  @Getter
  @AutoLog
  public static class ModuleMutInputs implements ModuleInputs {
    public boolean driveConnected = false;
    public double drivePositionRad = 0.0;
    public double driveVelocityRadPerSec = 0.0;
    public double driveAppliedVolts = 0.0;
    public double driveCurrentAmps = 0.0;

    public boolean turnConnected = false;
    public boolean turnEncoderConnected = false;
    public Rotation2d turnAbsolutePosition = Rotation2d.kZero;
    public Rotation2d turnPosition = Rotation2d.kZero;
    public double turnVelocityRadPerSec = 0.0;
    public double turnAppliedVolts = 0.0;
    public double turnCurrentAmps = 0.0;

    public double[] odometryTimestamps = new double[] {};
    public double[] odometryDrivePositionsRad = new double[] {};
    public Rotation2d[] odometryTurnPositions = new Rotation2d[] {};
  }

  /** Updates the set of loggable inputs. */
  public default void updateInputs(ModuleMutInputs inputs) {}

  /** Run the drive motor at the specified open loop value. */
  public default void setDriveOpenLoop(double output) {}

  /** Run the turn motor at the specified open loop value. */
  public default void setTurnOpenLoop(double output) {}

  /** Run the drive motor at the specified velocity. */
  public default void setDriveVelocity(double velocityRadPerSec) {}

  /** Run the turn motor to the specified rotation. */
  public default void setTurnPosition(Rotation2d rotation) {}

  public default void setKPGain(double value) {}

  public default void setKDGain(double value) {}

  public default void setKVGain(double value) {}
}
