// Copyright (c) 2021-2026 Littleton Robotics
// http://github.com/Mechanical-Advantage
//
// Use of this source code is governed by a BSD
// license that can be found in the LICENSE file
// at the root directory of this project.

package frc.robot.subsystems.drive;

import edu.wpi.first.math.geometry.Rotation2d;
import lombok.Getter;
import org.littletonrobotics.junction.AutoLog;

public interface GyroIO {
  public static interface GyroInputs {
    public boolean isConnected();

    public Rotation2d getYawPosition();

    public double getYawVelocityRadPerSec();

    public double[] getOdometryYawTimestamps();

    public Rotation2d[] getOdometryYawPositions();
  }

  @Getter
  @AutoLog
  public static class GyroMutInputs implements GyroInputs {
    public boolean connected = false;
    public Rotation2d yawPosition = Rotation2d.kZero;
    public double yawVelocityRadPerSec = 0.0;
    public double[] odometryYawTimestamps = new double[] {};
    public Rotation2d[] odometryYawPositions = new Rotation2d[] {};
  }

  public default void updateInputs(GyroMutInputs inputs) {}
}
