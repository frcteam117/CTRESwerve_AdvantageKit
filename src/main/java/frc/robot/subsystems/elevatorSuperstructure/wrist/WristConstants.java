package frc.robot.subsystems.elevatorSuperstructure.wrist;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.signals.GravityTypeValue;
import frc.robot.subsystems.elevatorSuperstructure.elevator.ElevatorConstants;
import org.littletonrobotics.junction.networktables.LoggedNetworkNumber;

public class WristConstants {
  public static final int CANID = 19;
  public static final double gear_ratio = 3;

  public static final double maxRotations = 1.069;
  // 385 / 360; // these 3 are in mechanism rotations (NOT geared up)
  public static final double minRotations = 0.638; // 230/360;
  public static final double rotRate = 0.2; // TODO: adjust when testing for vel changes

  // TODO: adjust from being in inches -> rotations (get measurements from CAD!)
  public static final double[] piecewiseIntervals = {
    0.063829787234 * ElevatorConstants.topRotations, // 3/47 inches
    0.187234042553 * ElevatorConstants.topRotations, // 8.8/47 inches
    0.276595744681 * ElevatorConstants.topRotations, // 13/47 inches
    0.612765957447 * ElevatorConstants.topRotations // 28.8/47 inches
  };

  public static final String tuningPrefix = "/Tuning/";

  // change later, value is incorrect :(
  // length of arm = ???
  public static final double mechanismMOI = 0.005; // the carriage mass in kg * length of the arm^2

  public static final TalonFXConfiguration talonFXConfigs = new TalonFXConfiguration();

  public static final LoggedNetworkNumber tunable_kS =
      new LoggedNetworkNumber(tuningPrefix + "kS", 0.009);
  public static final LoggedNetworkNumber tunable_kV =
      new LoggedNetworkNumber(tuningPrefix + "kV", .25);
  public static final LoggedNetworkNumber tunable_kA =
      new LoggedNetworkNumber(tuningPrefix + "kA", 0);
  public static final LoggedNetworkNumber tunable_kP =
      new LoggedNetworkNumber(tuningPrefix + "kP", 50); // higher = more accurate (less error)
  // no kI
  public static final LoggedNetworkNumber tunable_kD =
      new LoggedNetworkNumber(tuningPrefix + "kD", 1);
  public static final LoggedNetworkNumber tunable_kG =
      new LoggedNetworkNumber(tuningPrefix + "kG", 0.2);

  // set slot 0 gains
  public WristConstants() {
    var slot0Configs = talonFXConfigs.Slot0;
    // FIX ALL THE VALUES THEY ARE WRONG
    slot0Configs.kS = tunable_kS.getAsDouble();
    ; // Add 0.25 V output to overcome static friction
    slot0Configs.kV = tunable_kV.getAsDouble();
    ; // A velocity target of 1 rps results in 0.12 V output
    slot0Configs.kA = tunable_kA.getAsDouble();
    ; // An acceleration of 1 rps/s requires 0.01 V output
    slot0Configs.kP = tunable_kP.getAsDouble();
    ; // A position error of 2.5 rotations results in 12 V output
    slot0Configs.kI = 0; // no output for integrated error
    slot0Configs.kD = tunable_kD.getAsDouble();
    ; // A velocity error of 1 rps results in 0.1 V output
    slot0Configs.kG = tunable_kG.getAsDouble();
    ;

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
