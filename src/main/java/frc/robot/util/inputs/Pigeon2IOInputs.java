package frc.robot.util.inputs;

import com.ctre.phoenix6.hardware.Pigeon2;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Rotation3d;
import frc.robot.util.inputs.InputsUtil.LoggedControlRequest;
import frc.robot.util.inputs.InputsUtil.LoggedSignalBoolean;
import frc.robot.util.inputs.InputsUtil.LoggedSignalDouble;
import frc.robot.util.inputs.InputsUtil.LoggedSignalDouble3d;
import frc.robot.util.inputs.InputsUtil.LoggedSignalInteger;
import frc.robot.util.inputs.InputsUtil.LoggedSignalMeasure;
import frc.robot.util.inputs.InputsUtil.LoggedSignalMeasure3d;
import frc.robot.util.inputs.InputsUtil.LoggedSignalQuaternion;

public record Pigeon2IOInputs(
    LoggedControlRequest AppliedControlRequest,
    int DeviceID,
    String NetworkName,
    Rotation2d rotation2d,
    Rotation3d rotation3d,
    boolean hasResetOccurred,
    boolean isConnected,
    LoggedSignalMeasure Roll,
    LoggedSignalMeasure Pitch,
    LoggedSignalMeasure Yaw,
    LoggedSignalMeasure SupplyVoltage,
    LoggedSignalMeasure UpTime,
    LoggedSignalMeasure Temperature,
    LoggedSignalBoolean TemperatureCompensationDisabled,
    LoggedSignalBoolean NoMotionEnabled,
    LoggedSignalDouble NoMotionCount,
    LoggedSignalQuaternion quaternion,
    LoggedSignalMeasure3d accumGyro,
    LoggedSignalMeasure3d angVelDevice,
    LoggedSignalMeasure3d angVelWorld,
    LoggedSignalMeasure3d acceleration,
    LoggedSignalDouble3d gravityVector,
    LoggedSignalDouble3d magneticField,
    LoggedSignalDouble3d rawMagneticField,
    Pigeon2IOStatusInputs status,
    Pigeon2IOFaultInputs faults,
    Pigeon2IOStickyFaultInputs stickyFaults) {

  public Pigeon2IOInputs(Pigeon2 pigeon2) {
    this(
        new LoggedControlRequest(pigeon2.getAppliedControl()),
        pigeon2.getDeviceID(),
        pigeon2.getNetwork().getName(),
        pigeon2.getRotation2d(),
        pigeon2.getRotation3d(),
        pigeon2.hasResetOccurred(),
        pigeon2.isConnected(),
        new LoggedSignalMeasure(pigeon2.getRoll()),
        new LoggedSignalMeasure(pigeon2.getPitch()),
        new LoggedSignalMeasure(pigeon2.getYaw()),
        new LoggedSignalMeasure(pigeon2.getSupplyVoltage()),
        new LoggedSignalMeasure(pigeon2.getUpTime()),
        new LoggedSignalMeasure(pigeon2.getTemperature()),
        new LoggedSignalBoolean(pigeon2.getTemperatureCompensationDisabled()),
        new LoggedSignalBoolean(pigeon2.getNoMotionEnabled()),
        new LoggedSignalDouble(pigeon2.getNoMotionCount()),
        new LoggedSignalQuaternion(
            new LoggedSignalDouble(pigeon2.getQuatW()),
            new LoggedSignalDouble(pigeon2.getQuatX()),
            new LoggedSignalDouble(pigeon2.getQuatY()),
            new LoggedSignalDouble(pigeon2.getQuatZ())),
        new LoggedSignalMeasure3d(
            new LoggedSignalMeasure(pigeon2.getAccumGyroX()),
            new LoggedSignalMeasure(pigeon2.getAccumGyroY()),
            new LoggedSignalMeasure(pigeon2.getAccumGyroZ())),
        new LoggedSignalMeasure3d(
            new LoggedSignalMeasure(pigeon2.getAngularVelocityXDevice()),
            new LoggedSignalMeasure(pigeon2.getAngularVelocityYDevice()),
            new LoggedSignalMeasure(pigeon2.getAngularVelocityZDevice())),
        new LoggedSignalMeasure3d(
            new LoggedSignalMeasure(pigeon2.getAngularVelocityXWorld()),
            new LoggedSignalMeasure(pigeon2.getAngularVelocityYWorld()),
            new LoggedSignalMeasure(pigeon2.getAngularVelocityZWorld())),
        new LoggedSignalMeasure3d(
            new LoggedSignalMeasure(pigeon2.getAccelerationX()),
            new LoggedSignalMeasure(pigeon2.getAccelerationY()),
            new LoggedSignalMeasure(pigeon2.getAccelerationZ())),
        new LoggedSignalDouble3d(
            new LoggedSignalDouble(pigeon2.getGravityVectorX()),
            new LoggedSignalDouble(pigeon2.getGravityVectorY()),
            new LoggedSignalDouble(pigeon2.getGravityVectorZ())),
        new LoggedSignalDouble3d(
            new LoggedSignalDouble(pigeon2.getMagneticFieldX()),
            new LoggedSignalDouble(pigeon2.getMagneticFieldY()),
            new LoggedSignalDouble(pigeon2.getMagneticFieldZ())),
        new LoggedSignalDouble3d(
            new LoggedSignalDouble(pigeon2.getRawMagneticFieldX()),
            new LoggedSignalDouble(pigeon2.getRawMagneticFieldY()),
            new LoggedSignalDouble(pigeon2.getRawMagneticFieldZ())),
        new Pigeon2IOStatusInputs(pigeon2),
        new Pigeon2IOFaultInputs(pigeon2),
        new Pigeon2IOStickyFaultInputs(pigeon2));
  }

  public static record Pigeon2IOStatusInputs(
      LoggedSignalBoolean isProLicensed,
      LoggedSignalInteger version,
      LoggedSignalInteger versionMajor,
      LoggedSignalInteger versionMinor,
      LoggedSignalInteger versionBugfix,
      LoggedSignalInteger versionBuild) {
    public Pigeon2IOStatusInputs(Pigeon2 pigeon2) {
      this(
          new LoggedSignalBoolean(pigeon2.getIsProLicensed()),
          new LoggedSignalInteger(pigeon2.getVersion()),
          new LoggedSignalInteger(pigeon2.getVersionMajor()),
          new LoggedSignalInteger(pigeon2.getVersionMinor()),
          new LoggedSignalInteger(pigeon2.getVersionBugfix()),
          new LoggedSignalInteger(pigeon2.getVersionBuild()));
    }
  }

  public static record Pigeon2IOFaultInputs(
      LoggedSignalInteger faultField,
      LoggedSignalBoolean BootDuringEnable,
      LoggedSignalBoolean BootIntoMotion,
      LoggedSignalBoolean BootupAccelerometer,
      LoggedSignalBoolean BootupGyroscope,
      LoggedSignalBoolean BootupMagnetometer,
      LoggedSignalBoolean DataAcquiredLate,
      LoggedSignalBoolean Hardware,
      LoggedSignalBoolean LoopTimeSlow,
      LoggedSignalBoolean SaturatedAccelerometer,
      LoggedSignalBoolean SaturatedGyroscope,
      LoggedSignalBoolean SaturatedMagnetometer,
      LoggedSignalBoolean Undervoltage,
      LoggedSignalBoolean UnlicensedFeatureInUse) {
    public Pigeon2IOFaultInputs(Pigeon2 pigeon2) {
      this(
          new LoggedSignalInteger(pigeon2.getFaultField()),
          new LoggedSignalBoolean(pigeon2.getFault_BootDuringEnable()),
          new LoggedSignalBoolean(pigeon2.getFault_BootIntoMotion()),
          new LoggedSignalBoolean(pigeon2.getFault_BootupAccelerometer()),
          new LoggedSignalBoolean(pigeon2.getFault_BootupGyroscope()),
          new LoggedSignalBoolean(pigeon2.getFault_BootupMagnetometer()),
          new LoggedSignalBoolean(pigeon2.getFault_DataAcquiredLate()),
          new LoggedSignalBoolean(pigeon2.getFault_Hardware()),
          new LoggedSignalBoolean(pigeon2.getFault_LoopTimeSlow()),
          new LoggedSignalBoolean(pigeon2.getFault_SaturatedAccelerometer()),
          new LoggedSignalBoolean(pigeon2.getFault_SaturatedGyroscope()),
          new LoggedSignalBoolean(pigeon2.getFault_SaturatedMagnetometer()),
          new LoggedSignalBoolean(pigeon2.getFault_Undervoltage()),
          new LoggedSignalBoolean(pigeon2.getFault_UnlicensedFeatureInUse()));
    }
  }

  public static record Pigeon2IOStickyFaultInputs(
      LoggedSignalInteger stickyFaultField,
      LoggedSignalBoolean BootDuringEnable,
      LoggedSignalBoolean BootIntoMotion,
      LoggedSignalBoolean BootupAccelerometer,
      LoggedSignalBoolean BootupGyroscope,
      LoggedSignalBoolean BootupMagnetometer,
      LoggedSignalBoolean DataAcquiredLate,
      LoggedSignalBoolean Hardware,
      LoggedSignalBoolean LoopTimeSlow,
      LoggedSignalBoolean SaturatedAccelerometer,
      LoggedSignalBoolean SaturatedGyroscope,
      LoggedSignalBoolean SaturatedMagnetometer,
      LoggedSignalBoolean Undervoltage,
      LoggedSignalBoolean UnlicensedFeatureInUse) {
    public Pigeon2IOStickyFaultInputs(Pigeon2 pigeon2) {
      this(
          new LoggedSignalInteger(pigeon2.getStickyFaultField()),
          new LoggedSignalBoolean(pigeon2.getStickyFault_BootDuringEnable()),
          new LoggedSignalBoolean(pigeon2.getStickyFault_BootIntoMotion()),
          new LoggedSignalBoolean(pigeon2.getStickyFault_BootupAccelerometer()),
          new LoggedSignalBoolean(pigeon2.getStickyFault_BootupGyroscope()),
          new LoggedSignalBoolean(pigeon2.getStickyFault_BootupMagnetometer()),
          new LoggedSignalBoolean(pigeon2.getStickyFault_DataAcquiredLate()),
          new LoggedSignalBoolean(pigeon2.getStickyFault_Hardware()),
          new LoggedSignalBoolean(pigeon2.getStickyFault_LoopTimeSlow()),
          new LoggedSignalBoolean(pigeon2.getStickyFault_SaturatedAccelerometer()),
          new LoggedSignalBoolean(pigeon2.getStickyFault_SaturatedGyroscope()),
          new LoggedSignalBoolean(pigeon2.getStickyFault_SaturatedMagnetometer()),
          new LoggedSignalBoolean(pigeon2.getStickyFault_Undervoltage()),
          new LoggedSignalBoolean(pigeon2.getStickyFault_UnlicensedFeatureInUse()));
    }
  }

  // pigeon2.getAppliedControl();
  // pigeon2.getDeviceID();
  // pigeon2.getNetwork();
  // pigeon2.getRotation2d();
  // pigeon2.getRotation3d();
  // pigeon2.getSimState();
  // pigeon2.hasResetOccurred();
  // pigeon2.getResetOccurredChecker();
  // pigeon2.isConnected();

  // pigeon2.getRoll();
  // pigeon2.getPitch();
  // pigeon2.getYaw();
  // pigeon2.getSupplyVoltage();
  // pigeon2.getTemperature();
  // pigeon2.getTemperatureCompensationDisabled();
  // pigeon2.getNoMotionEnabled();
  // pigeon2.getNoMotionCount();
  // pigeon2.getUpTime();

  // pigeon2.getQuatW();
  // pigeon2.getQuatX();
  // pigeon2.getQuatY();
  // pigeon2.getQuatZ();

  // pigeon2.getAccumGyroX();
  // pigeon2.getAccumGyroY();
  // pigeon2.getAccumGyroZ();

  // pigeon2.getAngularVelocityXDevice();
  // pigeon2.getAngularVelocityYDevice();
  // pigeon2.getAngularVelocityZDevice();
  // pigeon2.getAngularVelocityYWorld();
  // pigeon2.getAngularVelocityXWorld();
  // pigeon2.getAngularVelocityZWorld();

  // pigeon2.getAccelerationX();
  // pigeon2.getAccelerationY();
  // pigeon2.getAccelerationZ();

  // pigeon2.getGravityVectorX();
  // pigeon2.getGravityVectorY();
  // pigeon2.getGravityVectorZ();

  // pigeon2.getMagneticFieldX();
  // pigeon2.getMagneticFieldY();
  // pigeon2.getMagneticFieldZ();
  // pigeon2.getRawMagneticFieldX();
  // pigeon2.getRawMagneticFieldY();
  // pigeon2.getRawMagneticFieldZ();

  // pigeon2.getIsProLicensed();
  // pigeon2.getVersion();
  // pigeon2.getVersionMajor();
  // pigeon2.getVersionMinor();
  // pigeon2.getVersionBugfix();
  // pigeon2.getVersionBuild();

  // pigeon2.getFaultField();
  // pigeon2.getFault_BootDuringEnable();
  // pigeon2.getFault_BootIntoMotion();
  // pigeon2.getFault_BootupAccelerometer();
  // pigeon2.getFault_BootupGyroscope();
  // pigeon2.getFault_BootupMagnetometer();
  // pigeon2.getFault_DataAcquiredLate();
  // pigeon2.getFault_Hardware();
  // pigeon2.getFault_LoopTimeSlow();
  // pigeon2.getFault_SaturatedAccelerometer();
  // pigeon2.getFault_SaturatedGyroscope();
  // pigeon2.getFault_SaturatedMagnetometer();
  // pigeon2.getFault_Undervoltage();
  // pigeon2.getFault_UnlicensedFeatureInUse();

  // pigeon2.getStickyFaultField();
  // pigeon2.getStickyFault_BootDuringEnable();
  // pigeon2.getStickyFault_BootIntoMotion();
  // pigeon2.getStickyFault_BootupAccelerometer();
  // pigeon2.getStickyFault_BootupGyroscope();
  // pigeon2.getStickyFault_BootupMagnetometer();
  // pigeon2.getStickyFault_DataAcquiredLate();
  // pigeon2.getStickyFault_Hardware();
  // pigeon2.getStickyFault_LoopTimeSlow();
  // pigeon2.getStickyFault_SaturatedAccelerometer();
  // pigeon2.getStickyFault_SaturatedGyroscope();
  // pigeon2.getStickyFault_SaturatedMagnetometer();
  // pigeon2.getStickyFault_Undervoltage();
  // pigeon2.getStickyFault_UnlicensedFeatureInUse();

}
