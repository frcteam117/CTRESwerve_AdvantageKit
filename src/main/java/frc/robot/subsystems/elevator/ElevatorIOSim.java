// // Copyright (c) 2021-2026 Littleton Robotics
// TODO: figure out simulation later
// // http://github.com/Mechanical-Advantage
// //
// // Use of this source code is governed by a BSD
// // license that can be found in the LICENSE file
// // at the root directory of this project.

// package frc.robot.subsystems.elevator;

// import edu.wpi.first.math.MathUtil;
// import edu.wpi.first.math.geometry.Rotation2d;
// import edu.wpi.first.wpilibj.Timer;

// import org.littletonrobotics.junction.AutoLog;

// public class ElevatorIOSim implements ElevatorIO {
//   @AutoLog
//   public static class ElevatorIOInputs {
//     public boolean connected = false;
//     public double positionRotations = 0.0;
//     public double velocityRadPerSec = 0.0;
//     public double appliedVolts = 0.0;
//     public double currentAmps = 0.0;

//     public boolean encoderConnected = false;
//     public Rotation2d absolutePosition = Rotation2d.kZero;

//     public double[] odometryTimestamps = new double[] {};
//     public double[] odometryelevatorPositionsRad = new double[] {};
//     public Rotation2d[] odometryTurnPositions = new Rotation2d[] {};
//   }

//   @Override
//   public void updateInputs(ElevatorIOInputs inputs) {

//     // Update simulation state
//     // elevatorSim.setInputVoltage(MathUtil.clamp(elevatorAppliedVolts, -12.0, 12.0));
//     // elevatorSim.update(0.02);

//     // Update elevator inputs
//     inputs.connected = true;
//     inputs.positionRotations = elevatorSim.getAngularPositionRad();
//     inputs.elevatorVelocityRadPerSec = elevatorSim.getAngularVelocityRadPerSec();
//     inputs.elevatorAppliedVolts = elevatorAppliedVolts;
//     inputs.elevatorCurrentAmps = Math.abs(elevatorSim.getCurrentDrawAmps());

//     // Update turn inputs
//     inputs.turnConnected = true;
//     inputs.turnEncoderConnected = true;
//     inputs.turnAbsolutePosition = new Rotation2d(turnSim.getAngularPositionRad());
//     inputs.turnPosition = new Rotation2d(turnSim.getAngularPositionRad());
//     inputs.turnVelocityRadPerSec = turnSim.getAngularVelocityRadPerSec();
//     inputs.turnAppliedVolts = turnAppliedVolts;
//     inputs.turnCurrentAmps = Math.abs(turnSim.getCurrentDrawAmps());

//     // Update odometry inputs (50Hz because high-frequency odometry in sim doesn't
//     // matter)
//     inputs.odometryTimestamps = new double[] {Timer.getFPGATimestamp()};
//     inputs.odometryelevatorPositionsRad = new double[] {inputs.elevatorPositionRad};
//     inputs.odometryTurnPositions = new Rotation2d[] {inputs.turnPosition};
//   }

//   @Override
//   public void setelevatorOpenLoop(double output) {
//     elevatorClosedLoop = false;
//     elevatorAppliedVolts = output;
//   }

//   @Override
//   public void setTurnOpenLoop(double output) {
//     turnClosedLoop = false;
//     turnAppliedVolts = output;
//   }

//   @Override
//   public void setelevatorVelocity(double velocityRadPerSec) {
//     elevatorClosedLoop = true;
//     elevatorFFVolts = elevator_KS * Math.signum(velocityRadPerSec) + elevator_KV *
// velocityRadPerSec;
//     elevatorController.setSetpoint(velocityRadPerSec);
//   }

//   @Override
//   public void setTurnPosition(Rotation2d rotation) {
//     turnClosedLoop = true;
//     turnController.setSetpoint(rotation.getRadians());
//   }
// }
