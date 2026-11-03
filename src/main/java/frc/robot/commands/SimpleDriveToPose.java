package frc.robot.commands;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.drive.Drive;
import java.util.function.Supplier;

public class SimpleDriveToPose extends Command {
  private final Drive drive;
  private final Supplier<Pose2d> targetPoseSupplier;
  // Translation controller drives distance error toward 0
  private final PIDController translationController = new PIDController(2.5, 0.0, 0.0);
  // Profiled PID for heading rotation
  private final ProfiledPIDController headingController =
      new ProfiledPIDController(
          3.5,
          0.0,
          0.0,
          new TrapezoidProfile.Constraints(
              Math.PI * 2, Math.PI * 4) // Max velocity (rad/s), max acceleration (rad/s^2)
          );

  public SimpleDriveToPose(Drive drive, Supplier<Pose2d> targetPoseSupplier) {
    this.drive = drive;
    this.targetPoseSupplier = targetPoseSupplier;
    // Continuous input wrapping prevents the robot from taking the long way around (e.g. 350 deg)
    headingController.enableContinuousInput(-Math.PI, Math.PI);
    translationController.setTolerance(0.02); // 2 cm tolerance
    headingController.setTolerance(Math.toRadians(2.0)); // 2 deg tolerance
    addRequirements(drive);
  }
  /** Overload for a static target pose */
  public SimpleDriveToPose(Drive drive, Pose2d targetPose) {
    this(drive, () -> targetPose);
  }

  @Override
  public void initialize() {
    translationController.reset();
    headingController.reset(drive.getPose().getRotation().getRadians());
  }

  @Override
  public void execute() {
    Pose2d currentPose = drive.getPose();
    Pose2d targetPose = targetPoseSupplier.get();
    // 1. Calculate translation error vector and straight-line distance
    Translation2d translationError =
        targetPose.getTranslation().minus(currentPose.getTranslation());
    double distanceError = translationError.getNorm();
    // 2. Calculate linear velocity magnitude directed toward the target, clamped to physical limits
    // Distance error is positive, so calculate speed needed to close distance:
    double linearVelocityScalar = Math.abs(translationController.calculate(distanceError, 0.0));
    linearVelocityScalar =
        MathUtil.clamp(linearVelocityScalar, 0.0, drive.getMaxLinearSpeedMetersPerSec() / 2);
    // 3. Convert scalar velocity to field-relative X and Y components
    double xVelocity = 0.0;
    double yVelocity = 0.0;
    if (distanceError > 0.01) { // Deadband to prevent jitter when close
      xVelocity = (translationError.getX() / distanceError) * linearVelocityScalar;
      yVelocity = (translationError.getY() / distanceError) * linearVelocityScalar;
    }
    // 4. Calculate angular velocity
    double angularVelocity =
        headingController.calculate(
            currentPose.getRotation().getRadians(), targetPose.getRotation().getRadians());
    // 5. Convert field-relative speeds to robot-relative speeds and send to drivetrain
    ChassisSpeeds speeds =
        ChassisSpeeds.fromFieldRelativeSpeeds(
            xVelocity, yVelocity, angularVelocity, currentPose.getRotation());
    drive.runVelocity(speeds);
  }

  @Override
  public void end(boolean interrupted) {
    drive.stop();
  }

  @Override
  public boolean isFinished() {
    return translationController.atSetpoint() && headingController.atSetpoint();
  }
}
