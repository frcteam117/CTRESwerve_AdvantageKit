package frc.robot.subsystems.elevatorSuperstructure.superstructure;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.subsystems.elevatorSuperstructure.arm.ArmConstants;
import frc.robot.subsystems.elevatorSuperstructure.arm.ArmIO;
import frc.robot.subsystems.elevatorSuperstructure.arm.ArmSubsystem;
import frc.robot.subsystems.elevatorSuperstructure.elevator.*;
import frc.robot.subsystems.elevatorSuperstructure.superstructure.SuperstructureIO.SuperstructureInputs;
import frc.robot.subsystems.elevatorSuperstructure.wrist.WristConstants;
import frc.robot.subsystems.elevatorSuperstructure.wrist.WristIO;
import frc.robot.subsystems.elevatorSuperstructure.wrist.WristSubsystem;

public class SuperstructureSubsystem extends SubsystemBase {

  private final ElevatorSubsystem elevator;
  private final ArmSubsystem arm;
  private final WristSubsystem wrist;

  private final SuperstructureInputs inputs;

  public SuperstructureSubsystem(ElevatorIO elevatorIO, ArmIO armIO, WristIO wristIO) {
    // System.out.println("HIJK " + ElevatorConstants.talonFXConfigs.Slot0.kS);
    elevator = new ElevatorSubsystem(elevatorIO);
    arm = new ArmSubsystem(armIO);
    wrist = new WristSubsystem(wristIO);

    inputs = new SuperstructureInputs(elevator.getInputs(), arm.getInputs(), wrist.getInputs());
  }

  @Override
  public void periodic() {

    elevator.periodic();

    arm.periodic();

    wrist.periodic();
  }

  public SuperstructureInputs getInputs() {
    return inputs;
  }

  // TODO: add in specific position location commands & consider implementing some attributes of
  // statebased for it

  // default command: set soup to go to their nextPositions
  public Command RunSoupPositions(SuperstructureSubsystem soup) {
    return soup.run(
        () -> {
          elevator.getIO().setPosition(elevator.getInputs().getNextPositionRotations());
          arm.getIO().setPosition(arm.getInputs().getNextPositionRotations());
          wrist.getIO().setPosition(wrist.getInputs().getNextPositionRotations());
        });
  }

  // ===========
  // these wont be used unless for some reason only 1 of the mechanisms needs to be running at a
  // time
  public Command RunElevatorPosition(SuperstructureSubsystem soup) {
    return soup.run(
        () -> {
          elevator.getIO().setPosition(elevator.getInputs().getNextPositionRotations());
        });
  }

  public void RequestElevatorPosition(double requestedRots) {
    elevator.setNextPosition(
        SuperstructureUtil.calcSafeElevatorPosition(
            requestedRots,
            wrist.getInputs().getPositionRotations(),
            arm.getInputs().getPositionRotations()));
  }
// TODO: it kinda works? debug: elevator wont go past 5 rots & the wrist pos min is not correct
  public Command RequestRaiseElevator(SuperstructureSubsystem soup) {
    double safePos =
        SuperstructureUtil.calcSafeElevatorPosition(
            elevator.getInputs().getNextPositionRotations() + ElevatorConstants.rotRate,
            arm.getInputs().getPositionRotations(),
            wrist.getInputs().getPositionRotations());
    double safeArmPos =
        SuperstructureUtil.calcSafeArmPosition(
            arm.getInputs().getPositionRotations(),
            safePos,
            wrist.getInputs().getPositionRotations());
    double safeWristPos =
        SuperstructureUtil.calcSafeArmPosition(
            wrist.getInputs().getPositionRotations(),
            safePos,
            arm.getInputs().getPositionRotations());
    return soup.runOnce(() -> elevator.setNextPosition(safePos))
        .andThen(
            soup.runOnce(() -> ElevatorRequestNewMechanismPositions(safeArmPos, safeWristPos)));
  }

  public Command RequestLowerElevator(SuperstructureSubsystem soup) {
    double safePos =
        SuperstructureUtil.calcSafeElevatorPosition(
            elevator.getInputs().getNextPositionRotations() - ElevatorConstants.rotRate,
            arm.getInputs().getPositionRotations(),
            wrist.getInputs().getPositionRotations());
    double safeArmPos =
        SuperstructureUtil.calcSafeArmPosition(
            arm.getInputs().getPositionRotations(),
            safePos,
            wrist.getInputs().getPositionRotations());
    double safeWristPos =
        SuperstructureUtil.calcSafeArmPosition(
            wrist.getInputs().getPositionRotations(),
            safePos,
            arm.getInputs().getPositionRotations());
    return soup.runOnce(() -> elevator.setNextPosition(safePos))
        .andThen(
            soup.runOnce(() -> ElevatorRequestNewMechanismPositions(safeArmPos, safeWristPos)));
  }

  public void ElevatorRequestNewMechanismPositions(
      double requestedArmPositions, double requestedWristPositions) {
    RequestArmPosition(requestedArmPositions);
    RequestWristPosition(requestedWristPositions);
  }

  // public Command ElevatorTop(SuperstructureSubsystem soup) {
  //   return soup.runOnce(
  //       () -> {
  //         elevator.getIO().setPosition(ElevatorConstants.topRotations);
  //       });
  // }

  // public Command ElevatorMid(SuperstructureSubsystem soup) {
  //   return soup.runOnce(
  //       () -> {
  //         elevator.getIO().setPosition(ElevatorConstants.midRotations);
  //       });
  // }

  // public Command ElevatorBottom(SuperstructureSubsystem soup) {
  //   return soup.runOnce(
  //       () -> {
  //         elevator.getIO().setPosition(ElevatorConstants.bottomRotations);
  //       });
  // }

  /// ===========
  public Command RunWristPosition(SuperstructureSubsystem soup) {
    return soup.run(
        () -> {
          wrist.getIO().setPosition(wrist.getInputs().getNextPositionRotations());
        });
  }

  public void RequestWristPosition(double requestedRots) {
    wrist.setNextPosition(
        SuperstructureUtil.calcSafeWristPosition(
            requestedRots,
            elevator.getInputs().getNextPositionRotations(),
            arm.getInputs().getPositionRotations()));
  }

  // will this interruption mess up the elevator & arm movement?
  public Command RequestRaiseWrist(SuperstructureSubsystem soup) {
    return soup.runOnce(
        () ->
            wrist.setNextPosition(
                SuperstructureUtil.calcSafeWristPosition(
                    wrist.getInputs().getNextPositionRotations() + WristConstants.rotRate,
                    elevator.getInputs().getPositionRotations(),
                    arm.getInputs().getPositionRotations())));
  }

  public Command RequestLowerWrist(SuperstructureSubsystem soup) {
    return soup.runOnce(
        () ->
            wrist.setNextPosition(
                SuperstructureUtil.calcSafeWristPosition(
                    wrist.getInputs().getNextPositionRotations() - WristConstants.rotRate,
                    elevator.getInputs().getPositionRotations(),
                    arm.getInputs().getPositionRotations())));
  }

  // :D

  // ===========
  public Command RunArmPosition(SuperstructureSubsystem soup) {
    return soup.run(
        () -> {
          arm.getIO().setPosition(arm.getInputs().getNextPositionRotations());
        });
  }

  public void RequestArmPosition(double requestedRots) {
    arm.setNextPosition(
        SuperstructureUtil.calcSafeArmPosition(
            requestedRots,
            elevator.getInputs().getPositionRotations(),
            wrist.getInputs().getPositionRotations()));
  }

  public Command RequestRaiseArm(SuperstructureSubsystem soup) {
    return soup.runOnce(
        () ->
            arm.setNextPosition(
                SuperstructureUtil.calcSafeArmPosition(
                    arm.getInputs().getNextPositionRotations() + ArmConstants.rotRate,
                    elevator.getInputs().getPositionRotations(),
                    wrist.getInputs().getPositionRotations())));
  }

  public Command RequestLowerArm(SuperstructureSubsystem soup) {
    return soup.runOnce(
        () ->
            arm.setNextPosition(
                SuperstructureUtil.calcSafeArmPosition(
                    arm.getInputs().getNextPositionRotations() - ArmConstants.rotRate,
                    elevator.getInputs().getPositionRotations(),
                    wrist.getInputs().getPositionRotations())));
  }

  // public Command ArmUp(SuperstructureSubsystem soup) {
  //   return soup.run(
  //       () -> {
  //         arm.getIO().setPosition(ArmConstants.topRotations);
  //       });
  // }

  // public Command ArmMid(SuperstructureSubsystem soup) {
  //   return soup.run(
  //       () -> {
  //         arm.getIO().setPosition(ArmConstants.midRotations);
  //       });
  // }

  // public Command ArmDown(SuperstructureSubsystem soup) {
  //   return soup.run(
  //       () -> {
  //         arm.getIO().setPosition(ArmConstants.bottomRotations);
  //       });
  // }
}
