package frc.robot.subsystems.elevatorSuperstructure.superstructure;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
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
  // default command: set soup to go to their nextPositions
  public Command RunSoupPositions(SuperstructureSubsystem soup) {
    // return RunElevatorPosition(soup)
    //       .alongWith(RunArmPosition(soup))
    //       .alongWith(RunWristPosition(soup));
    return RunWristPosition(soup);
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
            elevator.getInputs().getPositionRotations(),
            arm.getInputs().getPositionRotations()));
  }

  // will this interruption mess up the elevator & arm movement?
  public Command RequestRaiseWrist(SuperstructureSubsystem soup) {
    return soup.runOnce(
        () ->
            wrist.setNextPosition(
                SuperstructureUtil.calcSafeWristPosition(
                    wrist.getInputs().getPositionRotations() + WristConstants.rotRate,
                    elevator.getInputs().getPositionRotations(),
                    arm.getInputs().getPositionRotations())));
  }

  public Command RequestLowerWrist(SuperstructureSubsystem soup) {
    return soup.runOnce(
        () ->
            wrist.setNextPosition(
                SuperstructureUtil.calcSafeWristPosition(
                    wrist.getInputs().getPositionRotations() - WristConstants.rotRate,
                    elevator.getInputs().getPositionRotations(),
                    arm.getInputs().getPositionRotations())));
  }

  // public Command WristUp(SuperstructureSubsystem soup) {
  //   return soup.run(
  //       () -> {
  //         wrist.getIO().setPosition(WristConstants.topRotations);
  //       });
  // }

  // public Command WristDown(SuperstructureSubsystem soup) {
  //   return soup.run(
  //       () -> {
  //         wrist.getIO().setPosition(WristConstants.bottomRotations);
  //       });
  // }

  // public Command WristMid(SuperstructureSubsystem soup) {
  //   return soup.run(
  //       () -> {
  //         wrist.getIO().setPosition(WristConstants.midRotations);
  //       });
  // }

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
