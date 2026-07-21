// Copyright (c) 2021-2026 Littleton Robotics
// http://github.com/Mechanical-Advantage
//
// Use of this source code is governed by a BSD
// license that can be found in the LICENSE file
// at the root directory of this project.

package frc.robot.subsystems.elevatorSuperstructure.elevator;

import com.ctre.phoenix6.sim.TalonFXSimState;
import edu.wpi.first.wpilibj.simulation.DCMotorSim;
import org.littletonrobotics.junction.AutoLog;

public interface ElevatorIO {
  @AutoLog
  public static class ElevatorIOInputs {
    // changed to doubles because the autologging cant process non-doubles
    // public boolean connected = false;
    // public Angle positionRotations = Radians.of(0);
    // public AngularVelocity velocityRotationsPerSec = RotationsPerSecond.of(0);
    // public Voltage appliedVolts = Volts.of(0.0);
    // public Current currentAmps = Amps.of(0);

    // public boolean encoderConnected = false;
    // public Rotation2d absolutePosition = Rotation2d.kZero;

    // public double[] odometryTimestamps = new double[] {};
    // public Angle[] odometryPositionsRotations = new Angle[] {};

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
  public default void updateInputs(ElevatorIOInputs inputs) {}

  /** Run the drive motor at the specified open loop value. */
  // public default void setDriveOpenLoop(double output) {}

  /** Run the turn motor at the specified open loop value. */
  // public default void setTurnOpenLoop(double output) {}

  /** request rotation amount */
  public default void setPosition(double rotations) {}

  public default void setVelocity(double velocityRadPerSec) {}

  // is this a safe way to do this? vvv
  public default TalonFXSimState getTalonSimState() {
    return null;
  }

  public default DCMotorSim getTalonSimModel() {
    return null;
  }

  // public default void setKPGain(double value) {}

  // public default void setKDGain(double value) {}

  // public default void setKVGain(double value) {}
}
