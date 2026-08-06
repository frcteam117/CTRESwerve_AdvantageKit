package frc.robot.subsystems.elevatorSuperstructure.arm;

import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.controls.VelocityVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.TunerConstants;

public class ArmIOTalonFX implements ArmIO {
  private final TalonFX talon;

  public ArmIOTalonFX() {
    talon = new TalonFX(ArmConstants.CANID, TunerConstants.kCANBus);
    talon.getConfigurator().apply(ArmConstants.talonFXConfigs);
  }

  @Override
  public void updateInputs(ArmMutInputs inputs) {
    inputs.connected = true;
    inputs.positionRotations = talon.getPosition().getValueAsDouble();
    inputs.appliedVolts = talon.getVelocity().getValueAsDouble();
    inputs.currentAmps = Math.abs(talon.getSupplyCurrent().getValueAsDouble());
    inputs.odometryTimestamps = new double[] {Timer.getFPGATimestamp()};
    inputs.odometryPositionsRotations = new double[] {inputs.positionRotations};
  }

  @Override
  public void setVelocity(double velocityRotationsPerSec) {
    VelocityVoltage velocityRequest = new VelocityVoltage(velocityRotationsPerSec);

    talon.setControl(velocityRequest);
  }

  @Override
  public void setPosition(double rotations) {
    final MotionMagicVoltage m_request = new MotionMagicVoltage(0);

    talon.setControl(m_request.withPosition(rotations));
  }

  @Override
  public void updateConfig() {
    talon.getConfigurator().apply(ArmConstants.talonFXConfigs);
  }
}
