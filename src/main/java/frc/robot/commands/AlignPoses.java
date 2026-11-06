package frc.robot.commands;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.drive.Drive;

public class AlignPoses {
  public final SimpleDriveToPose drivetoPose1;
  public final SimpleDriveToPose drivetoPose2;
  public final SimpleDriveToPose drivetoPose3;
  public final SimpleDriveToPose drivetoPose4;
  public final SimpleDriveToPose drivetoPose5;
  public final SimpleDriveToPose drivetoPose6;
  public final SimpleDriveToPose drivetoPose7;

  public AlignPoses(Drive drive) {
    Translation2d alignPose2dtrans1 = new Translation2d(15, 6);
    Rotation2d alightPose2drotat1 = new Rotation2d(Units.degreesToRadians(0));

    Translation2d alignPose2dtrans2 = new Translation2d(14.1, 5.3);
    Rotation2d alightPose2drotat2 = new Rotation2d(Units.degreesToRadians(67));

    Translation2d alignPose2dtrans3 = new Translation2d(13.9, 6.8);
    Rotation2d alightPose2drotat3 = new Rotation2d(Units.degreesToRadians(180));

    Translation2d alignPose2dtrans4 = new Translation2d(15, 6);
    Rotation2d alightPose2drotat4 = new Rotation2d(Units.degreesToRadians(0));

    Translation2d alignPose2dtrans5 = new Translation2d(15, 6);
    Rotation2d alightPose2drotat5 = new Rotation2d(Units.degreesToRadians(0));

    Translation2d alignPose2dtrans6 = new Translation2d(15, 6);
    Rotation2d alightPose2drotat6 = new Rotation2d(Units.degreesToRadians(0));

    Translation2d alignPose2dtrans7 = new Translation2d(15, 6);
    Rotation2d alightPose2drotat7 = new Rotation2d(Units.degreesToRadians(0));

    Pose2d alignPose1 = new Pose2d(alignPose2dtrans1, alightPose2drotat1);
    drivetoPose1 = new SimpleDriveToPose(drive, alignPose1);

    Pose2d alignPose2 = new Pose2d(alignPose2dtrans2, alightPose2drotat2);
    drivetoPose2 = new SimpleDriveToPose(drive, alignPose2);

    Pose2d alignPose3 = new Pose2d(alignPose2dtrans3, alightPose2drotat3);
    drivetoPose3 = new SimpleDriveToPose(drive, alignPose3);

    Pose2d alignPose4 = new Pose2d(alignPose2dtrans4, alightPose2drotat4);
    drivetoPose4 = new SimpleDriveToPose(drive, alignPose4);

    Pose2d alignPose5 = new Pose2d(alignPose2dtrans5, alightPose2drotat5);
    drivetoPose5 = new SimpleDriveToPose(drive, alignPose5);

    Pose2d alignPose6 = new Pose2d(alignPose2dtrans6, alightPose2drotat6);
    drivetoPose6 = new SimpleDriveToPose(drive, alignPose6);

    Pose2d alignPose7 = new Pose2d(alignPose2dtrans7, alightPose2drotat7);
    drivetoPose7 = new SimpleDriveToPose(drive, alignPose7);
  }

  public Command Sequence123() {
    return drivetoPose1.andThen(drivetoPose2).andThen(drivetoPose3);
  }
}
