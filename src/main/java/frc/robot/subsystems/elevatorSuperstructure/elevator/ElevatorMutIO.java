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

public class ElevatorMutIO implements ElevatorIO {

  /** Updates the set of loggable inputs. */
  public void updateInputs(ElevatorIOInputs inputs) {}

  /** Run the drive motor at the specified open loop value. */
  // public default void setDriveOpenLoop(double output) {}

  /** Run the turn motor at the specified open loop value. */
  // public default void setTurnOpenLoop(double output) {}

  /** request rotation amount */
  public void setPosition(double rotations) {}

  public void setVelocity(double velocityRadPerSec) {}

  @Override
  // is this a safe way to do this? vvv
  public TalonFXSimState getTalonSimState() {
    return null;
  }
  @Override
  public DCMotorSim getTalonSimModel() {
    return null;
  }

  // public default void setKPGain(double value) {}

  // public default void setKDGain(double value) {}

  // public default void setKVGain(double value) {}
}
