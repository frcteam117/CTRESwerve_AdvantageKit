// Copyright (c) 2021-2026 Littleton Robotics
// http://github.com/Mechanical-Advantage
//
// Use of this source code is governed by a BSD
// license that can be found in the LICENSE file
// at the root directory of this project.

package frc.robot.subsystems.vision;

import static frc.robot.subsystems.vision.VisionConstants.*;

import edu.wpi.first.math.geometry.Pose3d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Transform3d;
import edu.wpi.first.networktables.DoubleArraySubscriber;
import edu.wpi.first.networktables.DoubleSubscriber;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import org.photonvision.PhotonCamera;

/** IO implementation for real PhotonVision hardware. */
public class VisionIOPhotonVision implements VisionIO {
  protected final PhotonCamera camera;
  protected final Transform3d robotToCamera;

  /**
   * Creates a new VisionIOPhotonVision.
   *
   * @param name The configured name of the camera.
   * @param robotToCamera The 3D position of the camera relative to the robot.
   */
    private final NetworkTableInstance nt = NetworkTableInstance.getDefault();
  NetworkTable table = nt.getTable("RubikPiDetectionData");
  
  NetworkTable jessie1SubTable = table.getSubTable("Jessie1Results");
            DoubleSubscriber numResultsSub1 = jessie1SubTable.getDoubleTopic("NumberOfResults").subscribe(0.0);
            DoubleArraySubscriber confidencesSub1 = jessie1SubTable.getDoubleArrayTopic("Confidences").subscribe(new double[0]);
            DoubleArraySubscriber class_idsSub1 = jessie1SubTable.getDoubleArrayTopic("ClassIDs").subscribe(new double[0]);
            DoubleArraySubscriber widthsSub1 = jessie1SubTable.getDoubleArrayTopic("ResultWidths").subscribe(new double[0]);
            DoubleArraySubscriber heightsSub1 = jessie1SubTable.getDoubleArrayTopic("ResultHeights").subscribe(new double[0]);
            // DoubleArraySubscriber angles = jessie1SubTable.getDoubleArrayTopic("ResultsAngles").subscribe(new double[0]); 
            // not really important for a game where the pieces are spheres ^^^
            DoubleArraySubscriber centerXsSub1 = jessie1SubTable.getDoubleArrayTopic("ResultCenterXs").subscribe(new double[0]);
            DoubleArraySubscriber centerYsSub1 = jessie1SubTable.getDoubleArrayTopic("ResultCenterYs").subscribe(new double[0]);
            //===
  NetworkTable jessie2SubTable = table.getSubTable("Jessie2Results");
            DoubleSubscriber numResultsSub2 = jessie2SubTable.getDoubleTopic("NumberOfResults").subscribe(0.0);
            DoubleArraySubscriber confidencesSub2 = jessie2SubTable.getDoubleArrayTopic("Confidences").subscribe(new double[0]);
            DoubleArraySubscriber class_idsSub2 = jessie2SubTable.getDoubleArrayTopic("ClassIDs").subscribe(new double[0]);
            DoubleArraySubscriber widthsSub2 = jessie2SubTable.getDoubleArrayTopic("ResultWidths").subscribe(new double[0]);
            DoubleArraySubscriber heightsSub2 = jessie2SubTable.getDoubleArrayTopic("ResultHeights").subscribe(new double[0]);
            // DoubleArraySubscriber angles = jessie2SubTable.getDoubleArrayTopic("ResultsAngles").subscribe(new double[0]); 
            // not really important for a game where the pieces are spheres ^^^
            DoubleArraySubscriber centerXsSub2 = jessie2SubTable.getDoubleArrayTopic("ResultCenterXs").subscribe(new double[0]);
            DoubleArraySubscriber centerYsSub2 = jessie2SubTable.getDoubleArrayTopic("ResultCenterYs").subscribe(new double[0]);
  //


  public VisionIOPhotonVision(String name, Transform3d robotToCamera) {
    camera = new PhotonCamera(name);
    this.robotToCamera = robotToCamera;
  }

  @Override
  public void updateInputs(VisionIOInputs inputs) {
    inputs.connected = camera.isConnected();

    // Read new camera observations
    Set<Short> tagIds = new HashSet<>();
    List<PoseObservation> poseObservations = new LinkedList<>();
    for (var result : camera.getAllUnreadResults()) {
      // Update latest target observation
      if (result.hasTargets()) {
        inputs.latestTargetObservation =
            new TargetObservation(
                Rotation2d.fromDegrees(result.getBestTarget().getYaw()),
                Rotation2d.fromDegrees(result.getBestTarget().getPitch()));
      } else {
        inputs.latestTargetObservation = new TargetObservation(Rotation2d.kZero, Rotation2d.kZero);
      }

      // Add pose observation
      if (result.multitagResult.isPresent()) { // Multitag result
        var multitagResult = result.multitagResult.get();

        // Calculate robot pose
        Transform3d fieldToCamera = multitagResult.estimatedPose.best;
        Transform3d fieldToRobot = fieldToCamera.plus(robotToCamera.inverse());
        Pose3d robotPose = new Pose3d(fieldToRobot.getTranslation(), fieldToRobot.getRotation());

        // Calculate average tag distance
        double totalTagDistance = 0.0;
        for (var target : result.targets) {
          totalTagDistance += target.bestCameraToTarget.getTranslation().getNorm();
        }

        // Add tag IDs
        tagIds.addAll(multitagResult.fiducialIDsUsed);

        // Add observation
        poseObservations.add(
            new PoseObservation(
                result.getTimestampSeconds(), // Timestamp
                robotPose, // 3D pose estimate
                multitagResult.estimatedPose.ambiguity, // Ambiguity
                multitagResult.fiducialIDsUsed.size(), // Tag count
                totalTagDistance / result.targets.size(), // Average tag distance
                PoseObservationType.PHOTONVISION)); // Observation type

      } else if (!result.targets.isEmpty()) { // Single tag result
        var target = result.targets.get(0);

        // Calculate robot pose
        var tagPose = aprilTagLayout.getTagPose(target.fiducialId);
        if (tagPose.isPresent()) {
          Transform3d fieldToTarget =
              new Transform3d(tagPose.get().getTranslation(), tagPose.get().getRotation());
          Transform3d cameraToTarget = target.bestCameraToTarget;
          Transform3d fieldToCamera = fieldToTarget.plus(cameraToTarget.inverse());
          Transform3d fieldToRobot = fieldToCamera.plus(robotToCamera.inverse());
          Pose3d robotPose = new Pose3d(fieldToRobot.getTranslation(), fieldToRobot.getRotation());

          // Add tag ID
          tagIds.add((short) target.fiducialId);

          // Add observation
          poseObservations.add(
              new PoseObservation(
                  result.getTimestampSeconds(), // Timestamp
                  robotPose, // 3D pose estimate
                  target.poseAmbiguity, // Ambiguity
                  1, // Tag count
                  cameraToTarget.getTranslation().getNorm(), // Average tag distance
                  PoseObservationType.PHOTONVISION)); // Observation type
        }
      }

      // object detection:
      // if else bc camera 0 is dead and to separate the results by camera
       if (camera.getName().equals("Jessie1")) {
        List<TFLiteResult> results1 = new ArrayList<>();
        inputs.numDetections_Jessie1 = (int) numResultsSub1.get();
        // declaring lists here to safe memory
        double[] confs = confidencesSub1.get();
        double[] class_ids = class_idsSub1.get();
        double[] widths = widthsSub1.get();
        double[] heights = heightsSub1.get();
        double[] centerXs = centerXsSub1.get();
        double[] centerYs = centerYsSub1.get();
        for (int i = 0; i < numResultsSub1.get()-1; i++) {
          results1.add(new TFLiteResult(
            confs[i],
            class_ids[i], 
            widths[i], 
            heights[i],
            centerXs[i], 
            centerYs[i]));
        }

        inputs.detectionResults_Jessie1 = results1;
       }
       else if (camera.getName().equals("Jessie2")) {
        List<TFLiteResult> results2 = new ArrayList<>();
        inputs.numDetections_Jessie2 = (int) numResultsSub2.get();
        // declaring lists here to safe memory
        double[] confs = confidencesSub2.get();
        double[] class_ids = class_idsSub2.get();
        double[] widths = widthsSub2.get();
        double[] heights = heightsSub2.get();
        double[] centerXs = centerXsSub2.get();
        double[] centerYs = centerYsSub2.get();
        for (int i = 0; i < numResultsSub2.get()-1; i++) {
          results2.add(new TFLiteResult(
            confs[i],
            class_ids[i], 
            widths[i], 
            heights[i],
            centerXs[i], 
            centerYs[i]));
        }

        inputs.detectionResults_Jessie2 = results2;
       }
      //
    }

    // Save pose observations to inputs object
    inputs.poseObservations = new PoseObservation[poseObservations.size()];
    for (int i = 0; i < poseObservations.size(); i++) {
      inputs.poseObservations[i] = poseObservations.get(i);
    }

    // Save tag IDs to inputs objects
    inputs.tagIds = new int[tagIds.size()];
    int i = 0;
    for (int id : tagIds) {
      inputs.tagIds[i++] = id;
    }
  }
}
