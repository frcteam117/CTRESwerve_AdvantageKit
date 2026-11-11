// Copyright (c) 2021-2026 Littleton Robotics
// http://github.com/Mechanical-Advantage
//
// Use of this source code is governed by a BSD
// license that can be found in the LICENSE file
// at the root directory of this project.

package frc.robot.subsystems.vision;

import edu.wpi.first.math.geometry.Pose3d;
import edu.wpi.first.math.geometry.Rotation2d;
import org.littletonrobotics.junction.AutoLog;

public interface VisionIO {
  @AutoLog
  public static class VisionIOInputs {
    public boolean connected = false;
    // public TargetObservation latestTargetObservation =
    //     new TargetObservation(Rotation2d.kZero, Rotation2d.kZero);
    // public PoseObservation[] poseObservations = new PoseObservation[0];
    // public TFLiteResult[] detectionResults_Jessie0 = new TFLiteResult[0]; // jessie 0 is dead rn
    // rip
    // public TFLiteResult[] detectionResults_Jessie1 = new TFLiteResult[0];
    // public TFLiteResult[] detectionResults_Jessie2 = new TFLiteResult[0];
    public TFLiteResult[] detectionResults = new TFLiteResult[0];
    // public int numDetections_Jessie1 = 0;
    // public int numDetections_Jessie2 = 0;
    public int numDetections = 0;
    // public int[] tagIds = new int[0];
  }

  /** Represents the angle to a simple target, not used for pose estimation. */
  public static record TargetObservation(Rotation2d tx, Rotation2d ty) {}

  /** Represents a robot pose sample used for pose estimation. */
  public static record PoseObservation(
      double timestamp,
      Pose3d pose,
      double ambiguity,
      int tagCount,
      double averageTagDistance,
      PoseObservationType type) {}
  /**
   * a TFLite result: confidence, class id, ambiguity, width, height (screen coords), centerX,
   * centerY (screen coords)
   */
  public static record TFLiteResult(
      double conf,
      double class_id,
      double ambiguity,
      double boxWidth,
      double boxHeight,
      double centerX,
      double centerY,
      double distFromCamera,
      double distFromRobot) {}

  public static enum PoseObservationType {
    MEGATAG_1,
    MEGATAG_2,
    PHOTONVISION
  }

  public default void updateInputs(VisionIOInputs inputs) {}
}
