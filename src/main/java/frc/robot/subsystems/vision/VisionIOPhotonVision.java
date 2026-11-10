// Copyright (c) 2021-2026 Littleton Robotics
// http://github.com/Mechanical-Advantage
//
// Use of this source code is governed by a BSD
// license that can be found in the LICENSE file
// at the root directory of this project.

package frc.robot.subsystems.vision;

import static frc.robot.subsystems.vision.VisionConstants.*;

import edu.wpi.first.math.geometry.Transform3d;
import edu.wpi.first.math.geometry.Translation3d;
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
import org.photonvision.targeting.PhotonPipelineResult;
import org.photonvision.targeting.PhotonTrackedTarget;

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
  DoubleSubscriber numResultsSub1 =
      jessie1SubTable.getDoubleTopic("NumberOfResults").subscribe(0.0);
  DoubleArraySubscriber confidencesSub1 =
      jessie1SubTable.getDoubleArrayTopic("Confidences").subscribe(new double[0]);
  DoubleArraySubscriber class_idsSub1 =
      jessie1SubTable.getDoubleArrayTopic("ClassIDs").subscribe(new double[0]);
  DoubleArraySubscriber widthsSub1 =
      jessie1SubTable.getDoubleArrayTopic("ResultWidths").subscribe(new double[0]);
  DoubleArraySubscriber heightsSub1 =
      jessie1SubTable.getDoubleArrayTopic("ResultHeights").subscribe(new double[0]);
  // DoubleArraySubscriber angles =
  // jessie1SubTable.getDoubleArrayTopic("ResultsAngles").subscribe(new double[0]);
  // not really important for a game where the pieces are spheres ^^^
  DoubleArraySubscriber centerXsSub1 =
      jessie1SubTable.getDoubleArrayTopic("ResultCenterXs").subscribe(new double[0]);
  DoubleArraySubscriber centerYsSub1 =
      jessie1SubTable.getDoubleArrayTopic("ResultCenterYs").subscribe(new double[0]);
  // ===
  NetworkTable jessie2SubTable = table.getSubTable("Jessie2Results");
  DoubleSubscriber numResultsSub2 =
      jessie2SubTable.getDoubleTopic("NumberOfResults").subscribe(0.0);
  DoubleArraySubscriber confidencesSub2 =
      jessie2SubTable.getDoubleArrayTopic("Confidences").subscribe(new double[0]);
  DoubleArraySubscriber class_idsSub2 =
      jessie2SubTable.getDoubleArrayTopic("ClassIDs").subscribe(new double[0]);
  DoubleArraySubscriber widthsSub2 =
      jessie2SubTable.getDoubleArrayTopic("ResultWidths").subscribe(new double[0]);
  DoubleArraySubscriber heightsSub2 =
      jessie2SubTable.getDoubleArrayTopic("ResultHeights").subscribe(new double[0]);
  // DoubleArraySubscriber angles =
  // jessie2SubTable.getDoubleArrayTopic("ResultsAngles").subscribe(new double[0]);
  // not really important for a game where the pieces are spheres ^^^
  DoubleArraySubscriber centerXsSub2 =
      jessie2SubTable.getDoubleArrayTopic("ResultCenterXs").subscribe(new double[0]);
  DoubleArraySubscriber centerYsSub2 =
      jessie2SubTable.getDoubleArrayTopic("ResultCenterYs").subscribe(new double[0]);
  //

  public VisionIOPhotonVision(String name, Transform3d robotToCamera) {
    camera = new PhotonCamera(name);
    this.robotToCamera = robotToCamera;
    camera.setFPSLimit(10);
  }

  @Override
  public void updateInputs(VisionIOInputs inputs) {
    inputs.connected = camera.isConnected();

    // Read new camera observations
    Set<Short> tagIds = new HashSet<>();
    List<PoseObservation> poseObservations = new LinkedList<>();
    // for object detection:
    List<TFLiteResult> results = new ArrayList<>();
    int curNum = 0;
    for (PhotonPipelineResult result : camera.getAllUnreadResults()) {
      List<PhotonTrackedTarget> targetList = result.getTargets();
      // inputs.latestTargetObservation =
      //     new TargetObservation(
      //         Rotation2d.fromDegrees(result.getBestTarget().getYaw()),
      //         Rotation2d.fromDegrees(result.getBestTarget().getPitch()));
      for (PhotonTrackedTarget target : targetList) {
        // TODO: should this be changed to define everything once to save memory?
        double boxWidth =
            Math.abs(
                target.getMinAreaRectCorners().get(0).x - target.getMinAreaRectCorners().get(1).x);
        double boxHeight =
            Math.abs(
                target.getMinAreaRectCorners().get(0).y - target.getMinAreaRectCorners().get(2).y);
        double centerX =
            (target.getMinAreaRectCorners().get(0).x + target.getMinAreaRectCorners().get(1).x) / 2;
        double centerY =
            (target.getMinAreaRectCorners().get(0).y + target.getMinAreaRectCorners().get(2).y) / 2;
        //

        double distFromCamera = 0.0;
        double fuelDiameter = VisionConstants.fuelDiameter; // works as height here
        // double avgLength = (widths[i] + heights[i]) / 2; // avg of length & width bc they
        // should be the same for spheres
        // dont know if correcting for that is good here, may implement l8r
        // assuming no camera warping, fingers crossed
        // distance formula: (using heights of everything, width could also be used)
        double focalLength;
        double sensorDiameter;
        double[] resolution;
        Transform3d robotToCamera;
        boolean cameraIdentified = false;
        if (camera.getName().equals(camera0Name)) {
          focalLength = VisionConstants.camera0FocalLength;
          sensorDiameter = VisionConstants.camera0SensorDiameter;
          robotToCamera = VisionConstants.robotToCamera0;
          resolution = VisionConstants.camera0Resolution;
          cameraIdentified = true;
        } else if (camera.getName().equals(camera1Name)) {
          focalLength = VisionConstants.camera1FocalLength;
          sensorDiameter = VisionConstants.camera1SensorDiameter;
          robotToCamera = VisionConstants.robotToCamera1;
          resolution = VisionConstants.camera1Resolution;
          cameraIdentified = true;
        } else if (camera.getName().equals(camera2Name)) {
          focalLength = VisionConstants.camera2FocalLength;
          sensorDiameter = VisionConstants.camera2SensorDiameter;
          resolution = camera2Resolution;
          robotToCamera = VisionConstants.robotToCamera2;
          cameraIdentified = true;
        } else {
          focalLength = -1;
          sensorDiameter = -1;
          robotToCamera = new Transform3d();
          resolution = VisionConstants.camera0Resolution;
        }
        double frameWidth = resolution[0]; // in pixels
        double frameHeight = resolution[1]; // in pixels

        if (cameraIdentified && focalLength != -1) {
          distFromCamera =
              (focalLength * fuelDiameter * frameHeight) / (boxHeight * sensorDiameter);

          // gets the distance between the camera and the center of the robot
          // (treating the center as 0,0 or the new translation3d)f1
          double toRobot = robotToCamera.getTranslation().getDistance(new Translation3d());
          double distFromRobot = distFromCamera + toRobot;

          results.add(
              new TFLiteResult(
                  target.getDetectedObjectConfidence(),
                  target.getDetectedObjectClassID(),
                  target.getPoseAmbiguity(),
                  boxWidth,
                  boxHeight,
                  centerX,
                  centerY,
                  distFromRobot));
          curNum += 1;
        }
      }

      // Add pose observation
      // if (result.multitagResult.isPresent()) { // Multitag result
      //   var multitagResult = result.multitagResult.get();

      //   // Calculate robot pose
      //   // Transform3d fieldToCamera = multitagResult.estimatedPose.best;
      //   // Transform3d fieldToRobot = fieldToCamera.plus(robotToCamera.inverse());
      //   // Pose3d robotPose = new Pose3d(fieldToRobot.getTranslation(),
      // fieldToRobot.getRotation());

      //   // Calculate average tag distance
      //   // double totalTagDistance = 0.0;
      //   // for (var target : result.targets) {
      //   //   totalTagDistance += target.bestCameraToTarget.getTranslation().getNorm();
      //   // }

      //   // Add tag IDs
      //   // tagIds.addAll(multitagResult.fiducialIDsUsed);

      //   // Add observation
      //   poseObservations.add(
      //       new PoseObservation(
      //           result.getTimestampSeconds(), // Timestamp
      //           robotPose, // 3D pose estimate
      //           multitagResult.estimatedPose.ambiguity, // Ambiguity
      //           multitagResult.fiducialIDsUsed.size(), // Tag count
      //           totalTagDistance / result.targets.size(), // Average tag distance
      //           PoseObservationType.PHOTONVISION)); // Observation type

      // } else if (!result.targets.isEmpty()) { // Single tag result
      //   var target = result.targets.get(0);

      //   // Calculate robot pose
      //   var tagPose = aprilTagLayout.getTagPose(target.fiducialId);
      //   if (tagPose.isPresent()) {
      //     Transform3d fieldToTarget =
      //         new Transform3d(tagPose.get().getTranslation(), tagPose.get().getRotation());
      //     Transform3d cameraToTarget = target.bestCameraToTarget;
      //     Transform3d fieldToCamera = fieldToTarget.plus(cameraToTarget.inverse());
      //     Transform3d fieldToRobot = fieldToCamera.plus(robotToCamera.inverse());
      //     Pose3d robotPose = new Pose3d(fieldToRobot.getTranslation(),
      // fieldToRobot.getRotation());

      //     // Add tag ID
      //     tagIds.add((short) target.fiducialId);

      //     // Add observation
      //     poseObservations.add(
      //         new PoseObservation(
      //             result.getTimestampSeconds(), // Timestamp
      //             robotPose, // 3D pose estimate
      //             target.poseAmbiguity, // Ambiguity
      //             1, // Tag count
      //             cameraToTarget.getTranslation().getNorm(), // Average tag distance
      //             PoseObservationType.PHOTONVISION)); // Observation type
      //   }
      // }

      // object detection:
      // if else bc camera 0 is dead and to separate the results by camera. TODO: fix this system to
      // not be separated by num pls
      // if (camera.getName().equals("Jessie1")) {
      //   int numResults = (int) numResultsSub1.get();
      //   TFLiteResult[] results1 = new TFLiteResult[numResults];
      //   inputs.numDetections_Jessie1 = numResults;
      //   // declaring lists here to safe memory
      //   double[] confs = confidencesSub1.get();
      //   double[] class_ids = class_idsSub1.get();
      //   double[] widths = widthsSub1.get(); // in image pixels
      //   double[] heights = heightsSub1.get(); // in image pixels
      //   double[] centerXs = centerXsSub1.get(); // in image pixel coords (top left = (0,0))
      //   double[] centerYs = centerYsSub1.get(); // in image pixel coords
      //   for (int i = 0; i < numResultsSub1.get() - 1; i++) {
      //     // calc distance from camera & robot center:
      //     double frameWidth = VisionConstants.cameraResolution[0]; // in pixels
      //     double frameHeight = VisionConstants.cameraResolution[1]; // in pixels
      //     double distFromCamera = 0.0;
      //     double fuelDiameter = VisionConstants.fuelDiameter; // works as height here
      //     // double avgLength = (widths[i] + heights[i]) / 2; // avg of length & width bc they
      //     // should be the same for spheres
      //     // dont know if correcting for that is good here, may implement l8r
      //     // assuming no camera warping, fingers crossed
      //     // distance formula: (using heights of everything, width could also be used)
      //     distFromCamera =
      //         (VisionConstants.camera1FocalLength * fuelDiameter * frameHeight)
      //             / (heights[i] * camera1SensorDiameter);

      //     // gets the distance between the camera and the center of the robot
      //     // (treating the center as 0,0 or the new translation3d)
      //     double toRobot =
      //         VisionConstants.robotToCamera1.getTranslation().getDistance(new Translation3d());
      //     double distFromRobot = distFromCamera + toRobot;
      //     results1[i] =
      //         new TFLiteResult(
      //             confs[i],
      //             class_ids[i],
      //             widths[i],
      //             heights[i],
      //             centerXs[i],
      //             centerYs[i],
      //             distFromRobot);
      //   }
      //   inputs.detectionResults_Jessie1 = results1;

      // } else if (camera.getName().equals("Jessie2")) {
      //   int numResults = (int) numResultsSub2.get();
      //   TFLiteResult[] results2 = new TFLiteResult[numResults];
      //   inputs.numDetections_Jessie2 = numResults;
      //   // declaring lists here to safe memory
      //   double[] confs = confidencesSub2.get();
      //   double[] class_ids = class_idsSub2.get();
      //   double[] widths = widthsSub2.get(); // in image pixels
      //   double[] heights = heightsSub2.get(); // in image pixels
      //   double[] centerXs = centerXsSub2.get(); // in image pixel coords (top left = (0,0))
      //   double[] centerYs = centerYsSub2.get(); // in image pixel coords
      //   for (int i = 0; i < numResultsSub2.get() - 1; i++) {
      //     // calc distance from camera & robot center:
      //     double frameWidth = VisionConstants.cameraResolution[0]; // in pixels
      //     double frameHeight = VisionConstants.cameraResolution[1]; // in pixels
      //     double distFromCamera = 0.0;
      //     double fuelDiameter = VisionConstants.fuelDiameter; // works as height here
      //     // double avgLength = (widths[i] + heights[i]) / 2; // avg of length & width bc they
      //     // should be the same for spheres
      //     // dont know if correcting for that is good here, may implement l8r
      //     // assuming no camera warping, fingers crossed
      //     // distance formula: (using heights of everything, width could also be used)
      //     distFromCamera =
      //         (VisionConstants.camera2FocalLength * fuelDiameter * frameHeight)
      //             / (heights[i] * camera2SensorDiameter);

      //     // gets the distance between the camera and the center of the robot
      //     // (treating the center as 0,0 or the new translation3d)
      //     double toRobot =
      //         VisionConstants.robotToCamera2.getTranslation().getDistance(new Translation3d());
      //     double distFromRobot = distFromCamera + toRobot;
      //     results2[i] =
      //         new TFLiteResult(
      //             confs[i],
      //             class_ids[i],
      //             widths[i],
      //             heights[i],
      //             centerXs[i],
      //             centerYs[i],
      //             distFromRobot);
      //   }

      //   inputs.detectionResults_Jessie2 = results2;
      // }

      //
    }

    // Save pose observations to inputs object
    // inputs.poseObservations = new PoseObservation[poseObservations.size()];
    // for (int i = 0; i < poseObservations.size(); i++) {
    //   inputs.poseObservations[i] = poseObservations.get(i);
    // }
    inputs.detectionResults = new TFLiteResult[results.size()];
    for (int i = 0; i < results.size(); i++) {
      inputs.detectionResults[i] = results.get(i);
    }
    // // Save tag IDs to inputs objects
    // inputs.tagIds = new int[tagIds.size()];
    // int i = 0;
    // for (int id : tagIds) {
    //   inputs.tagIds[i++] = id;
    // }
  }
}
