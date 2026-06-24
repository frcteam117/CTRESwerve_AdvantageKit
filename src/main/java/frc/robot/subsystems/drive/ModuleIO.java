// Copyright (c) 2021-2026 Littleton Robotics
// http://github.com/Mechanical-Advantage
//
// Use of this source code is governed by a BSD
// license that can be found in the LICENSE file
// at the root directory of this project.

package frc.robot.subsystems.drive;

import static edu.wpi.first.units.Units.Amps;
import static edu.wpi.first.units.Units.Radians;
import static edu.wpi.first.units.Units.RadiansPerSecond;
import static edu.wpi.first.units.Units.Volts;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.units.measure.AngularVelocity;
import edu.wpi.first.units.measure.Current;
import edu.wpi.first.units.measure.Voltage;
import org.littletonrobotics.junction.AutoLog;

public interface ModuleIO {
  @AutoLog
  public static class ModuleIOInputs {
    public boolean driveConnected = false;
    public Angle drivePosition = Radians.zero();
    public AngularVelocity driveVelocity = RadiansPerSecond.zero();
    public Voltage driveOutputVoltage = Volts.zero();
    public Current driveOutputCurrent = Amps.zero();
    // public TalonFXIOInputs driveTalon = null;

    public boolean turnConnected = false;
    public Angle turnPosition = Radians.zero();
    public AngularVelocity turnVelocity = RadiansPerSecond.zero();
    public Voltage turnOutputVoltage = Volts.zero();
    public Current turnOutputCurrent = Amps.zero();
    // public TalonFXIOInputs turnTalon = null;

    public boolean turnEncoderConnected = false;
    public Rotation2d turnAbsolutePosition = Rotation2d.kZero;
    // public CANcoderIOInputs cancoder = null;

    public double[] odometryTimestamps = new double[] {};
    public double[] odometryDrivePositionsRad = new double[] {};
    public Rotation2d[] odometryTurnPositions = new Rotation2d[] {};
  }

  /** Updates the set of loggable inputs. */
  public default void updateInputs(ModuleIOInputs inputs) {}

  /** Run drive motor at specified open loop voltage. */
  public default void setDriveOpenLoop(Voltage output) {}

  /** Run drive motor at specified open loop current. */
  public default void setDriveOpenLoop(Current output) {}

  /** Run turn motor at specified open loop voltage. */
  public default void setTurnOpenLoop(Voltage output) {}

  /** Run turn motor at specified open loop current. */
  public default void setTurnOpenLoop(Current output) {}

  /** Run drive motor at specified velocity. */
  public default void setDriveVelocity(AngularVelocity velocity) {}

  /** Run turn motor to specified heading. */
  public default void setTurnPosition(Rotation2d heading) {}
}
