package frc.robot.subsystems.elevatorSuperstructure.arm;

import com.ctre.phoenix6.sim.TalonFXSimState;
import edu.wpi.first.wpilibj.simulation.DCMotorSim;
import lombok.Getter;
import org.littletonrobotics.junction.AutoLog;

public interface ArmIO {
  public static interface ArmInputs {
    public boolean isConnected();

    public double getPositionRotations();

    public double getVelocityRotationsPerSec();

    public double getAppliedVolts();

    public double getCurrentAmps();

    public double[] getOdometryTimestamps();

    public double[] getOdometryPositionsRotations();
  }

  @Getter
  @AutoLog
  public static class ArmMutInputs implements ArmInputs {
    public boolean connected = false;
    public double positionRotations = 0.0;
    public double velocityRotationsPerSec = 0.0;
    public double appliedVolts = 0.0;
    public double currentAmps = 0.0;

    public double[] odometryTimestamps = new double[] {};
    public double[] odometryPositionsRotations = new double[] {};
  }

  public default void updateInputs(ArmMutInputs inputs) {}

  public default void setPosition(double rotations) {}

  public default void setVelocity(double velocityRadPerSec) {}

  public default TalonFXSimState getTalonSimState() {
    return null;
  }

  public default DCMotorSim getTalonSimModel() {
    return null;
  }

  public default void updateConfig() {}
}
