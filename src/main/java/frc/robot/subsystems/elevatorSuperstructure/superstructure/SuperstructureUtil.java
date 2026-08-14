package frc.robot.subsystems.elevatorSuperstructure.superstructure;

import frc.robot.subsystems.elevatorSuperstructure.elevator.ElevatorConstants;
import org.littletonrobotics.junction.Logger;

public class SuperstructureUtil {

  // TODO: adjust from being in inches -> rotations (get measurements from CAD!)
  public static final double[] piecewiseIntervals = {
    0.063829787234 * ElevatorConstants.topRotations, // 3/47 inches * ...
    0.187234042553 * ElevatorConstants.topRotations, // 8.8/47 inches * ...
    0.276595744681 * ElevatorConstants.topRotations, // 13/47 inches * ...
    0.612765957447 * ElevatorConstants.topRotations // 28.8/47 inches * ...
  };
  // used for smoothing some of the intervals for the *WRIST*
  /** squaring scale */
  public static final double scale1(double n) {
    return Math.pow(n, 2);
  }
  /** abs(n) to the power of 1/6 */
  public static final double scale2(double n) {
    return Math.pow(Math.abs(n), 1 / 6);
  }
  // TODO: adjust these for their zero offsets when the mechanism is built! & add
  // offsets to constants
  public static double calcSafeWristPosition(
      double requestedRots, double elevatorRotations, double armRotations) {
    // calculate next safe position that's closest to the requested rots
    double[] intervals = piecewiseIntervals;
    double safeRotations = requestedRots;
    // convert from rotations -> degrees (i wrote the functions in degrees)
    double hDeg = elevatorRotations * 360;
    double aDeg = armRotations * 360;
    double min = 0;
    double max = 0;
    //
    // NOTE: this just simply converts deg -> rot, not accounting for gear ratio
    // because its part of the motor configs, but i need to figure out whether
    // the CAD was accounting for the 3x gearing up and if so add a division by 3
    if (hDeg < intervals[0]) {
      min = (299 + (13 * ((277 - aDeg) / -130))) / 360;
      max = (245 + (140 * scale1((277 - aDeg) / -130))) / 360;
    } else if (hDeg < intervals[1]) {
      min = (312 + (128 * ((270 - aDeg) / -137))) / 360;
      max = (245 + (95 * scale1((270 - aDeg) / -137))) / 360;
    } else if (hDeg < intervals[2]) {
      min = (230 - (215 * -scale2((245 - aDeg) / 170))) / 360;
      max = (220 + (60 * scale1((245 - aDeg) / -175))) / 360;
    } else if (hDeg < intervals[3]) {
      min = (360 - (70 * -scale2((245 - aDeg) / 205))) / 360;
      max = (220 + (25 * scale1((245 - aDeg) / -205))) / 360;
    } else { // if intervals[3] < hDeg < top
      min = (340 - (90 * -scale2((245 - aDeg) / 215))) / 360;
      max = (220 + (20 * ((245 - aDeg) / -215))) / 360;
    }
    //
    if (requestedRots < min) {
      safeRotations = min;
    }
    if (requestedRots > max) {
      safeRotations = max;
    }

    Logger.recordOutput("Wrist/State/requestedRots", requestedRots);
    Logger.recordOutput("Wrist/State/safeRotations", safeRotations);
    Logger.recordOutput("Wrist/State/minRotations", min);
    Logger.recordOutput("Wrist/State/maxRotations", max);

    return safeRotations;
  }

  /// =======
  ///
  public static double calcSafeArmPosition(
      double requestedRots, double elevatorRotations, double wristRotations) {
    // calculate next safe position that's closest to the requested rots
    double[] intervals = piecewiseIntervals;
    double safeRotations = requestedRots;
    // convert from rotations -> degrees (i wrote the functions in degrees)
    double hDeg = elevatorRotations * 360;
    double aDeg = wristRotations * 360;
    double min = 0;
    double max = 0;
    //
    // NOTE: this just simply converts deg -> rot, not accounting for gear ratio
    // because its part of the motor configs, but i need to figure out whether
    // the CAD was accounting for the 3x gearing up and if so add a division by 3
    // if (hDeg < intervals[0]) {
    //   min = (299 + (13 * ((277 - aDeg) / -130))) / 360;
    //   max = (245 + (140 * scale1((277 - aDeg) / -130))) / 360;
    // } else if (hDeg < intervals[1]) {
    //   min = (312 + (128 * ((270 - aDeg) / -137))) / 360;
    //   max = (245 + (95 * scale1((270 - aDeg) / -137))) / 360;
    // } else if (hDeg < intervals[2]) {
    //   min = (230 - (215 * -scale2((245 - aDeg) / 170))) / 360;
    //   max = (220 + (60 * scale1((245 - aDeg) / -175))) / 360;
    // } else if (hDeg < intervals[3]) {
    //   min = (360 - (70 * -scale2((245 - aDeg) / 205))) / 360;
    //   max = (220 + (25 * scale1((245 - aDeg) / -205))) / 360;
    // } else { // if intervals[3] < hDeg < top
    //   min = (340 - (90 * -scale2((245 - aDeg) / 215))) / 360;
    //   max = (220 + (20 * ((245 - aDeg) / -215))) / 360;
    // }
    // //
    // if (requestedRots < min) {
    //   safeRotations = min;
    // }
    // if (requestedRots > max) {
    //   safeRotations = max;
    // }

    return safeRotations;
  }

  /// =========
  ///
  public static double calcSafeElevatorPosition(
      double requestedRots, double armRotations, double wristRotations) {
    // calculate next safe position that's closest to the requested rots
    double[] intervals = piecewiseIntervals;
    double safeRotations = requestedRots;
    // convert from rotations -> degrees (i wrote the functions in degrees)
    double aDeg = armRotations * 360;
    double wDeg = wristRotations * 360;
    double min = 0;
    double max = 0;
    //
    // NOTE: this just simply converts deg -> rot, not accounting for gear ratio
    // because its part of the motor configs, but i need to figure out whether
    // the CAD was accounting for the 3x gearing up and if so add a division by 3
    // if (hDeg < intervals[0]) {
    //   min = (299 + (13 * ((277 - aDeg) / -130))) / 360;
    //   max = (245 + (140 * scale1((277 - aDeg) / -130))) / 360;
    // } else if (hDeg < intervals[1]) {
    //   min = (312 + (128 * ((270 - aDeg) / -137))) / 360;
    //   max = (245 + (95 * scale1((270 - aDeg) / -137))) / 360;
    // } else if (hDeg < intervals[2]) {
    //   min = (230 - (215 * -scale2((245 - aDeg) / 170))) / 360;
    //   max = (220 + (60 * scale1((245 - aDeg) / -175))) / 360;
    // } else if (hDeg < intervals[3]) {
    //   min = (360 - (70 * -scale2((245 - aDeg) / 205))) / 360;
    //   max = (220 + (25 * scale1((245 - aDeg) / -205))) / 360;
    // } else { // if intervals[3] < hDeg < top
    //   min = (340 - (90 * -scale2((245 - aDeg) / 215))) / 360;
    //   max = (220 + (20 * ((245 - aDeg) / -215))) / 360;
    // }
    // //
    // if (requestedRots < min) {
    //   safeRotations = min;
    // }
    // if (requestedRots > max) {
    //   safeRotations = max;
    // }

    // Logger.recordOutput("Wrist/State/requestedRots", requestedRots);
    // Logger.recordOutput("Wrist/State/safeRotations", safeRotations);
    // Logger.recordOutput("Wrist/State/minRotations", min);
    // Logger.recordOutput("Wrist/State/maxRotations", max);

    return safeRotations;
  }
}
