// Copyright (c) 2021-2026 Littleton Robotics
// http://github.com/Mechanical-Advantage
//
// Use of this source code is governed by a BSD
// license that can be found in the LICENSE file
// at the root directory of this project.

package frc.robot.subsystems.elevatorSuperstructure.elevator;

import lombok.Getter;
import org.littletonrobotics.junction.AutoLog;

public interface ElevatorIO {
  public static interface ElevatorInputs {
    public boolean isConnected();

    public double getPositionRotations();

    public double getVelocityRotationsPerSec();

    public double getAppliedVolts();

    public double getCurrentAmps();

    public double[] getOdometryTimestamps();

    public double[] getOdometryPositionsRotations();
  }

  @Getter
  @AutoLog
  public static class ElevatorMutInputs implements ElevatorInputs {
    public boolean connected = false;
    public double positionRotations = 0.0;
    public double velocityRotationsPerSec = 0.0;
    public double appliedVolts = 0.0;
    public double currentAmps = 0.0;

    // public boolean encoderConnected = false;
    // public Rotation2d absolutePosition = Rotation2d.kZero;

    public double[] odometryTimestamps = new double[] {};
    public double[] odometryPositionsRotations = new double[] {};
  }

  /** Updates the set of loggable inputs. */
  public default void updateInputs(ElevatorMutInputs inputs) {}

  /** request rotation amount */
  public default void setPosition(double rotations) {}

  public default void setVelocity(double velocityRadPerSec) {}
}
