package frc.robot.subsystems.elevatorSuperstructure.wrist;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.controls.VelocityVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.MotorAlignmentValue;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.TunerConstants;

public class WristIOTalonFX implements WristIO{
    private final TalonFX talon;

  public WristIOTalonFX() {
    talon = new TalonFX(WristConstants.CANID, TunerConstants.kCANBus);
    var talonFXConfigs = new TalonFXConfiguration();

    var slot0Configs = talonFXConfigs.Slot0;
    //FIX VALUES
    slot0Configs.kS = 0.25; // Add 0.25 V output to overcome static friction
    slot0Configs.kV = 0.12; // A velocity target of 1 rps results in 0.12 V output
    slot0Configs.kA = 0.01; // An acceleration of 1 rps/s requires 0.01 V output
    slot0Configs.kP = 4.8; // A position error of 2.5 rotations results in 12 V output
    slot0Configs.kI = 0; // no output for integrated error
    slot0Configs.kD = 0.1; // A velocity error of 1 rps results in 0.1 V output

    // set Motion Magic settings CHANGE LATER
    var motionMagicConfigs = talonFXConfigs.MotionMagic;
    motionMagicConfigs.MotionMagicCruiseVelocity = 80; // Target cruise velocity of 80 rps
    motionMagicConfigs.MotionMagicAcceleration =
        160; // Target acceleration of 160 rps/s (0.5 seconds)
    motionMagicConfigs.MotionMagicJerk = 1600; // Target jerk of 1600 rps/s/s (0.1 seconds)
  }
  @Override
  public void updateInputs(WristIOInputs inputs) {
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
}
