package frc.robot.subsystems.elevatorSuperstructure.wrist;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.signals.GravityTypeValue;

public class WristConstants {
  public static final int CANID = 19;
  public static final double gear_ratio = 3;

  public static final double topRotations = 1;
  public static final double midRotations =
      0.5; // these 3 are in mechanism rotations (NOT geared up)
  public static final double bottomRotations = 0;

  // change later, value is incorrect :(
  // length of arm = ???
  public static final double mechanismMOI = 0.005; // the carriage mass in kg * length of the arm^2

  public static final TalonFXConfiguration talonFXConfigs = new TalonFXConfiguration();

  // set slot 0 gains
  public WristConstants() {
    var slot0Configs = talonFXConfigs.Slot0;
    // FIX ALL THE VALUES THEY ARE WRONG
    slot0Configs.kS = 0.25; // Add 0.25 V output to overcome static friction
    slot0Configs.kV = 0.12; // A velocity target of 1 rps results in 0.12 V output
    slot0Configs.kA = 0.01; // An acceleration of 1 rps/s requires 0.01 V output
    slot0Configs.kP = .1; // A position error of 2.5 rotations results in 12 V output
    slot0Configs.kI = 0; // no output for integrated error
    slot0Configs.kD = 0.001; // A velocity error of 1 rps results in 0.1 V output
    slot0Configs.kG = 0.4;

    slot0Configs.GravityType = GravityTypeValue.valueOf(1);

    var feedbackConfigs = talonFXConfigs.Feedback;
    feedbackConfigs.SensorToMechanismRatio =
        gear_ratio; // to fix the mechanism rotations conversion issue :)
    // System.out.println("ABCDEFG " + talonFXConfigs.Slot0.kS);

    var motionMagicConfigs = talonFXConfigs.MotionMagic;
    motionMagicConfigs.MotionMagicCruiseVelocity = 3; // Target cruise velocity of 3 rps
    motionMagicConfigs.MotionMagicAcceleration =
        6; // Target acceleration of 160 rps/s (0.5 seconds)
    motionMagicConfigs.MotionMagicJerk =
        60; // Target jerk of 1600 rps/s/s (0.1 seconds) (acceleration*10)
  }
}
