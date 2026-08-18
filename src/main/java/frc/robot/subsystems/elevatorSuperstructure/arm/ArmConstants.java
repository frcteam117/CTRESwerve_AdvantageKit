package frc.robot.subsystems.elevatorSuperstructure.arm;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.signals.GravityTypeValue;
import org.littletonrobotics.junction.networktables.LoggedNetworkNumber;

// All values are incorrect, if it breaks thats not my fault
// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

public class ArmConstants {
  public static final int CANID = 17;
  public static final double gear_ratio = 3.0;
  public static final double maxRotations = 1.278;
  // 460 / 360; // these 2 are in mechanism rotations (NOT geared up)
  public static final double minRotations = 0.681; // 245 / 360;
  public static final double rotRate = 0.1; // TODO: adjust when testing for vel changes
  // this is desired cruise velocity

  // change later, value is incorrect :(
  // length of arm = ???
  public static final double mechanismMOI = 0.005; // the carriage mass in kg * length of the arm^2

  public static TalonFXConfiguration talonFXConfigs = new TalonFXConfiguration();

  public static final String tuningPrefix = "/Tuning/";

  public static final LoggedNetworkNumber tunable_kS =
      new LoggedNetworkNumber(tuningPrefix + "kS", 0.007);
  public static final LoggedNetworkNumber tunable_kV =
      new LoggedNetworkNumber(tuningPrefix + "kV", 0.7);
  public static final LoggedNetworkNumber tunable_kA =
      new LoggedNetworkNumber(tuningPrefix + "kA", 0.01);
  public static final LoggedNetworkNumber tunable_kP =
      new LoggedNetworkNumber(tuningPrefix + "kP", 9); // higher = more accurate (less error)
  // no kI
  public static final LoggedNetworkNumber tunable_kD =
      new LoggedNetworkNumber(tuningPrefix + "kD", 0.0001);
  public static final LoggedNetworkNumber tunable_kG =
      new LoggedNetworkNumber(tuningPrefix + "kG", 0.2);
  // set slot 0 gains
  public ArmConstants() {
    var slot0Configs = talonFXConfigs.Slot0;
    // FIX ALL THE VALUES THEY ARE WRONG
    slot0Configs.kS = tunable_kS.getAsDouble(); // Add 0.25 V output to overcome static friction
    slot0Configs.kV =
        tunable_kV.getAsDouble(); // A velocity target of 1 rps results in 0.12 V output
    slot0Configs.kA = tunable_kA.getAsDouble(); // An acceleration of 1 rps/s requires 0.01 V output
    slot0Configs.kP =
        tunable_kP.getAsDouble(); // A position error of 2.5 rotations results in 12 V output
    slot0Configs.kI = 0; // no output for integrated error
    slot0Configs.kD = tunable_kD.getAsDouble(); // A velocity error of 1 rps results in 0.1 V output
    slot0Configs.kG = tunable_kG.getAsDouble();

    slot0Configs.GravityType = GravityTypeValue.valueOf(1);

    var feedbackConfigs = talonFXConfigs.Feedback;
    feedbackConfigs.SensorToMechanismRatio =
        ArmConstants.gear_ratio; // to fix the mechanism rotations conversion issue :)
    // System.out.println("ABCDEFG " + talonFXConfigs.Slot0.kS);

    var motionMagicConfigs = talonFXConfigs.MotionMagic;
    motionMagicConfigs.MotionMagicCruiseVelocity = rotRate; // Target cruise velocity of 3 rps
    motionMagicConfigs.MotionMagicAcceleration =
        rotRate * 2; // Target acceleration of 160 rps/s (0.5 seconds)
    motionMagicConfigs.MotionMagicJerk =
        rotRate * 20; // Target jerk of 1600 rps/s/s (0.1 seconds) (acceleration*10)
  }
}

// ADD MOTION MAGIC
