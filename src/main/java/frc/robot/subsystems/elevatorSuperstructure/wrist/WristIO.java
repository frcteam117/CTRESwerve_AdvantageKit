package frc.robot.subsystems.elevatorSuperstructure.wrist;

import com.ctre.phoenix6.sim.TalonFXSimState;
import edu.wpi.first.wpilibj.simulation.DCMotorSim;
import lombok.Getter;
import org.littletonrobotics.junction.AutoLog;

public interface WristIO {
  public static interface WristInputs {
    public boolean isConnected();

    public double getPositionRotations();

    public double getNextPositionRotations();

    public double getVelocityRotationsPerSec();

    public double getAppliedVolts();

    public double getCurrentAmps();

    public double[] getOdometryTimestamps();

    public double[] getOdometryPositionsRotations();
  }

  @Getter
  @AutoLog
  public static class WristMutInputs implements WristInputs {
    public boolean connected = false;
    public double positionRotations = 0.0;
    public double nextPositionRotations = 0.0;
    public double velocityRotationsPerSec = 0.0;
    public double appliedVolts = 0.0;
    public double currentAmps = 0.0;
    public double[] odometryTimestamps = new double[] {};
    public double[] odometryPositionsRotations = new double[] {};
  }

  public default void updateInputs(WristMutInputs inputs) {}

  public default void setPosition(double rotations) {}

  public default void setVelocity(double velocityRadPerSec) {}

  public default TalonFXSimState getTalonSimState() {
    return null;
  }

  public default DCMotorSim getTalonSimModel() {
    return null;
  }
}
