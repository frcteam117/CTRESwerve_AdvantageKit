package frc.robot.subsystems.elevatorSuperstructure.wrist;

import com.ctre.phoenix6.sim.TalonFXSimState;
import edu.wpi.first.wpilibj.simulation.DCMotorSim;
import frc.robot.subsystems.elevatorSuperstructure.wrist.WristIO.WristIOInputs;

import org.littletonrobotics.junction.AutoLog;

public interface WristIO {
    @AutoLog
    public static class WristIOInputs {
        public boolean connected = false;
        public double positionRotations = 0.0;
        public double appliedVolts = 0.0;
        public double currentAmps = 0.0;
        public double[] odometryTimestamps = new double[] {};
        public double[] odometryPositionsRotations = new double[] {};
    }

    public default void updateInputs(WristIOInputs inputs) {}

    public default void setPosition(double rotations) {}

    public default void setVelocity(double velocityRadPerSec) {}

    public default TalonFXSimState getTalonSimState() {
        return null;
    }

    public default DCMotorSim getTalonSimModel() {
        return null;
    }
}
