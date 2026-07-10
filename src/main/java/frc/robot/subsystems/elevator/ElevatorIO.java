// Copyright (c) 2021-2026 Littleton Robotics
// http://github.com/Mechanical-Advantage
//
// Use of this source code is governed by a BSD
// license that can be found in the LICENSE file
// at the root directory of this project.

package frc.robot.subsystems.elevator;

import edu.wpi.first.math.geometry.Rotation2d;
import org.littletonrobotics.junction.AutoLog;

public interface ElevatorIO {
  @AutoLog
  public static class ElevatorIOInputs {
    public boolean connected = false;
    public double positionRotations = 0.0;
    public double velocityRadPerSec = 0.0;
    public double appliedVolts = 0.0;
    public double currentAmps = 0.0;

    public boolean encoderConnected = false;
    public Rotation2d absolutePosition = Rotation2d.kZero;

    public double[] odometryTimestamps = new double[] {};
    public double[] odometryPositionsRotations = new double[] {};
  }

  /** Updates the set of loggable inputs. */
  public default void updateInputs(ElevatorIOInputs inputs) {}

  /** Run the drive motor at the specified open loop value. */
  // public default void setDriveOpenLoop(double output) {}

  /** Run the turn motor at the specified open loop value. */
  // public default void setTurnOpenLoop(double output) {}

  /** request rotation amount */
  public default void setPosition(double rotations) {}

  // public default void setKPGain(double value) {}

  // public default void setKDGain(double value) {}

  // public default void setKVGain(double value) {}
}
