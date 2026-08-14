package frc.robot.subsystems.elevatorSuperstructure.elevator;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.signals.GravityTypeValue;

public class ElevatorConstants {
  public static final int leaderCANID = 14;
  public static final int followerCANID = 15;
  // TODO: adjust these for the in -> rot calculation
  public static final double topRotations = 21.22;
  //   public static final int midRotations = 50; // these 3 are in mechanism rotations (NOT geared
  // down)
  public static final double bottomRotations = 0;
  public static final double rotRate = 25.0; // TODO: adjust when testing for vel changes

  public static final double gear_ratio =
      0.375; // TODO: change, and add rads -> mps conversion to elevator inputs
  public static final double mechanismMOI = 0.005; // the carriage mass in kg * pulley radius^2

  public static final TalonFXConfiguration talonFXConfigs = new TalonFXConfiguration();

  // set slot 0 gains
  public ElevatorConstants() {
    var slot0Configs = talonFXConfigs.Slot0;
    slot0Configs.kS = 0.25; // Add 0.25 V output to overcome static friction
    slot0Configs.kV = 0.12; // A velocity target of 1 rps results in 0.12 V output
    slot0Configs.kA = 0.01; // An acceleration of 1 rps/s requires 0.01 V output
    slot0Configs.kP = 4.3; // A position error of 2.5 rotations results in 12 V output
    slot0Configs.kI = 0; // no output for integrated error
    slot0Configs.kD = 0.1; // A velocity error of 1 rps results in 0.1 V output
    slot0Configs.kG = 0.4;

    slot0Configs.GravityType = GravityTypeValue.valueOf(0);

    var feedbackConfigs = talonFXConfigs.Feedback;
    feedbackConfigs.SensorToMechanismRatio =
        ElevatorConstants.gear_ratio; // to fix the mechanism rotations conversion issue :)
    // System.out.println("ABCDEFG " + talonFXConfigs.Slot0.kS);

    var motionMagicConfigs = talonFXConfigs.MotionMagic;
    motionMagicConfigs.MotionMagicCruiseVelocity = rotRate; // Target cruise velocity of 80 rps
    motionMagicConfigs.MotionMagicAcceleration =
        rotRate * 2; // Target acceleration of 160 rps/s (0.5 seconds)
    motionMagicConfigs.MotionMagicJerk =
        rotRate * 20; // Target jerk of 1600 rps/s/s (0.1 seconds) (acceleration*10)
  }
}
