package frc.robot.subsystems.elevatorSuperstructure.elevator;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.signals.GravityTypeValue;
import org.littletonrobotics.junction.networktables.LoggedNetworkNumber;

public class ElevatorConstants {
  public static final int leaderCANID = 14;
  public static final int followerCANID = 15;
  // TODO: adjust these for the in -> rot calculation
  public static final double topRotations = 21.22;
  //   public static final int midRotations = 50; // these 3 are in mechanism rotations (NOT geared
  // down)
  public static final double bottomRotations = 0;
  public static final double rotRate = 5.0; // TODO: adjust when testing for vel changes

  public static final double gear_ratio =
      0.375; // TODO: change, and add rads -> mps conversion to elevator inputs
  public static final double mechanismMOI = 0.005; // the carriage mass in kg * pulley radius^2

  public static final TalonFXConfiguration talonFXConfigs = new TalonFXConfiguration();

  public static final String tuningPrefix = "/Tuning/";

  public static final LoggedNetworkNumber tunable_kS =
      new LoggedNetworkNumber(tuningPrefix + "kS", 0.0004);
  public static final LoggedNetworkNumber tunable_kV =
      new LoggedNetworkNumber(tuningPrefix + "kV", 0.001);
  public static final LoggedNetworkNumber tunable_kA =
      new LoggedNetworkNumber(tuningPrefix + "kA", 0);
  public static final LoggedNetworkNumber tunable_kP =
      new LoggedNetworkNumber(tuningPrefix + "kP", 0.08); // higher = more accurate (less error)
  // no kI
  public static final LoggedNetworkNumber tunable_kD =
      new LoggedNetworkNumber(tuningPrefix + "kD", 0);
  public static final LoggedNetworkNumber tunable_kG =
      new LoggedNetworkNumber(tuningPrefix + "kG", 0.01); // 0.01
  // set slot 0 gains
  public ElevatorConstants() {
    var slot0Configs = talonFXConfigs.Slot0;
    // FIX ALL THE VALUES THEY ARE WRONG
    slot0Configs.kS = tunable_kS.getAsDouble();
    slot0Configs.kV = tunable_kV.getAsDouble();
    slot0Configs.kA = tunable_kA.getAsDouble();
    slot0Configs.kP = tunable_kP.getAsDouble();
    slot0Configs.kI = 0;
    slot0Configs.kD = tunable_kD.getAsDouble();
    slot0Configs.kG = tunable_kG.getAsDouble();

    slot0Configs.GravityType = GravityTypeValue.valueOf(0);

    var feedbackConfigs = talonFXConfigs.Feedback;
    feedbackConfigs.SensorToMechanismRatio =
        ElevatorConstants.gear_ratio; // to fix the mechanism rotations conversion issue :)
    // System.out.println("ABCDEFG " + talonFXConfigs.Slot0.kS);

    var motionMagicConfigs = talonFXConfigs.MotionMagic;
    motionMagicConfigs.MotionMagicCruiseVelocity = rotRate;
    motionMagicConfigs.MotionMagicAcceleration = rotRate * 2;
    motionMagicConfigs.MotionMagicJerk = rotRate * 20;
  }
}
