// Copyright (c) 2021-2026 Littleton Robotics
// http://github.com/Mechanical-Advantage
//
// Use of this source code is governed by a BSD
// license that can be found in the LICENSE file
// at the root directory of this project.

package frc.robot.subsystems.vision;

import edu.wpi.first.apriltag.AprilTagFieldLayout;
import edu.wpi.first.apriltag.AprilTagFields;
import edu.wpi.first.math.geometry.Rotation3d;
import edu.wpi.first.math.geometry.Transform3d;
import edu.wpi.first.networktables.DoubleArraySubscriber;
import edu.wpi.first.networktables.DoubleSubscriber;

public class VisionConstants {
  // AprilTag layout
  public static AprilTagFieldLayout aprilTagLayout =
      AprilTagFieldLayout.loadField(AprilTagFields.kDefaultField);

  // Camera names, must match names configured on coprocessor
  public static String camera0Name = "Jessie0";
  public static String camera1Name = "Jessie1";
  public static String camera2Name = "Jessie2";

  // Robot to camera transforms
  // (Not used by Limelight, configure in web UI instead)
  // in meters
  public static Transform3d robotToCamera0 =
      new Transform3d(0.292, 0.252, 0.220, new Rotation3d(0, Math.PI / 3, Math.toRadians(180)));
  public static Transform3d robotToCamera1 =
      new Transform3d(0.265, -0.244, 0.220, new Rotation3d(0, Math.PI / 3, -Math.PI / 2));
  public static Transform3d robotToCamera2 = new Transform3d(); // NEED TO ADD

  // Basic filtering thresholds
  public static double maxAmbiguity = 0.3;
  public static double maxZError = 0.75;

  // Standard deviation baselines, for 1 meter distance and 1 tag
  // (Adjusted automatically based on distance and # of tags)
  public static double linearStdDevBaseline = 0.02; // Meters
  public static double angularStdDevBaseline = 0.06; // Radians

  // Standard deviation multipliers for each camera
  // (Adjust to trust some cameras more than others)
  public static double[] cameraStdDevFactors =
      new double[] {
        1.0, // Camera 0
        1.0, // Camera 1
        1.0 // Camera 2
      };

    //   DoubleSubscriber numResultsSub1 = jessie1SubTable.getDoubleTopic("NumberOfResults").subscribe(0.0);
    //         DoubleArraySubscriber confidencesSub1 = jessie1SubTable.getDoubleArrayTopic("Confidences").subscribe(new double[0]);
    //         DoubleArraySubscriber class_idsSub1 = jessie1SubTable.getDoubleArrayTopic("ClassIDs").subscribe(new double[0]);
    //         DoubleArraySubscriber widthsSub1 = jessie1SubTable.getDoubleArrayTopic("ResultWidths").subscribe(new double[0]);
    //         DoubleArraySubscriber heightsSub1 = jessie1SubTable.getDoubleArrayTopic("ResultHeights").subscribe(new double[0]);
    //         // DoubleArraySubscriber angles = jessie1SubTable.getDoubleArrayTopic("ResultsAngles").subscribe(new double[0]); 
    //         // not really important for a game where the pieces are spheres ^^^
    //         DoubleArraySubscriber centerXsSub1 = jessie1SubTable.getDoubleArrayTopic("ResultCenterXs").subscribe(new double[0]);
    //         DoubleArraySubscriber centerYsSub1 = jessie1SubTable.getDoubleArrayTopic("ResultCenterYs").subscribe(new double[0]);
            //===

  // Multipliers to apply for MegaTag 2 observations
  public static double linearStdDevMegatag2Factor = 0.5; // More stable than full 3D solve
  public static double angularStdDevMegatag2Factor =
      Double.POSITIVE_INFINITY; // No rotation data available

}
