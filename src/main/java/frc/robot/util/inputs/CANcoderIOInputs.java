package frc.robot.util.inputs;

import com.ctre.phoenix6.hardware.CANcoder;
import frc.robot.util.inputs.InputsUtil.LoggedControlRequest;
import frc.robot.util.inputs.InputsUtil.LoggedSignalBoolean;
import frc.robot.util.inputs.InputsUtil.LoggedSignalEnum;
import frc.robot.util.inputs.InputsUtil.LoggedSignalInteger;
import frc.robot.util.inputs.InputsUtil.LoggedSignalMeasure;

public record CANcoderIOInputs(
    LoggedControlRequest appliedControl,
    int deviceID,
    String networkName,
    boolean hasResetOccurred,
    boolean isConnected,
    LoggedSignalMeasure absolutePosition,
    LoggedSignalMeasure position,
    LoggedSignalMeasure positionSinceBoot,
    LoggedSignalMeasure velocity,
    LoggedSignalMeasure unfilteredVelocity,
    LoggedSignalMeasure supplyVoltage,
    LoggedSignalEnum magnetHealth,
    CANcoderIOStatusInputs status,
    CANcoderIOFaultInputs faults,
    CANcoderIOStickyFaultInputs stickyFaults) {
  public CANcoderIOInputs(CANcoder cancoder) {
    this(
        new LoggedControlRequest(cancoder.getAppliedControl()),
        cancoder.getDeviceID(),
        cancoder.getNetwork().getName(),
        cancoder.hasResetOccurred(),
        cancoder.isConnected(),
        new LoggedSignalMeasure(cancoder.getAbsolutePosition()),
        new LoggedSignalMeasure(cancoder.getPosition()),
        new LoggedSignalMeasure(cancoder.getPositionSinceBoot()),
        new LoggedSignalMeasure(cancoder.getVelocity()),
        new LoggedSignalMeasure(cancoder.getUnfilteredVelocity()),
        new LoggedSignalMeasure(cancoder.getSupplyVoltage()),
        new LoggedSignalEnum(cancoder.getMagnetHealth()),
        new CANcoderIOStatusInputs(cancoder),
        new CANcoderIOFaultInputs(cancoder),
        new CANcoderIOStickyFaultInputs(cancoder));
  }

  public static record CANcoderIOStatusInputs(
      LoggedSignalBoolean isProLicensed,
      LoggedSignalInteger version,
      LoggedSignalInteger versionMajor,
      LoggedSignalInteger versionMinor,
      LoggedSignalInteger versionBugfix,
      LoggedSignalInteger versionBuild) {
    public CANcoderIOStatusInputs(CANcoder cancoder) {
      this(
          new LoggedSignalBoolean(cancoder.getIsProLicensed()),
          new LoggedSignalInteger(cancoder.getVersion()),
          new LoggedSignalInteger(cancoder.getVersionMajor()),
          new LoggedSignalInteger(cancoder.getVersionMinor()),
          new LoggedSignalInteger(cancoder.getVersionBugfix()),
          new LoggedSignalInteger(cancoder.getVersionBuild()));
    }
  }

  public static record CANcoderIOFaultInputs(
      LoggedSignalInteger faultField,
      LoggedSignalBoolean badMagnet,
      LoggedSignalBoolean bootDuringEnable,
      LoggedSignalBoolean hardware,
      LoggedSignalBoolean undervoltage,
      LoggedSignalBoolean unlicensedFeatureInUse) {
    public CANcoderIOFaultInputs(CANcoder cancoder) {
      this(
          new LoggedSignalInteger(cancoder.getFaultField()),
          new LoggedSignalBoolean(cancoder.getFault_BadMagnet()),
          new LoggedSignalBoolean(cancoder.getFault_BootDuringEnable()),
          new LoggedSignalBoolean(cancoder.getFault_Hardware()),
          new LoggedSignalBoolean(cancoder.getFault_Undervoltage()),
          new LoggedSignalBoolean(cancoder.getFault_UnlicensedFeatureInUse()));
    }
  }

  public static record CANcoderIOStickyFaultInputs(
      LoggedSignalInteger stickyFaultField,
      LoggedSignalBoolean badMagnet,
      LoggedSignalBoolean bootDuringEnable,
      LoggedSignalBoolean hardware,
      LoggedSignalBoolean undervoltage,
      LoggedSignalBoolean unlicensedFeatureInUse) {
    public CANcoderIOStickyFaultInputs(CANcoder cancoder) {
      this(
          new LoggedSignalInteger(cancoder.getStickyFaultField()),
          new LoggedSignalBoolean(cancoder.getStickyFault_BadMagnet()),
          new LoggedSignalBoolean(cancoder.getStickyFault_BootDuringEnable()),
          new LoggedSignalBoolean(cancoder.getStickyFault_Hardware()),
          new LoggedSignalBoolean(cancoder.getStickyFault_Undervoltage()),
          new LoggedSignalBoolean(cancoder.getStickyFault_UnlicensedFeatureInUse()));
    }
  }

  // cancoder.getAppliedControl();
  // cancoder.getDeviceID();
  // cancoder.getNetwork();
  // cancoder.getResetOccurredChecker();
  // cancoder.hasResetOccurred();
  // cancoder.getSimState();
  // cancoder.isConnected();

  // cancoder.getAbsolutePosition();
  // cancoder.getPosition();
  // cancoder.getPositionSinceBoot();
  // cancoder.getVelocity();
  // cancoder.getUnfilteredVelocity();
  // cancoder.getSupplyVoltage();
  // cancoder.getMagnetHealth();

  // cancoder.getIsProLicensed();
  // cancoder.getVersion();
  // cancoder.getVersionMajor();
  // cancoder.getVersionMinor();
  // cancoder.getVersionBugfix();
  // cancoder.getVersionBuild();

  // cancoder.getFaultField();
  // cancoder.getFault_BadMagnet();
  // cancoder.getFault_BootDuringEnable();
  // cancoder.getFault_Hardware();
  // cancoder.getFault_Undervoltage();
  // cancoder.getFault_UnlicensedFeatureInUse();

  // cancoder.getStickyFaultField();
  // cancoder.getStickyFault_BadMagnet();
  // cancoder.getStickyFault_BootDuringEnable();
  // cancoder.getStickyFault_Hardware();
  // cancoder.getStickyFault_Undervoltage();
  // cancoder.getStickyFault_UnlicensedFeatureInUse();

  // cancoder.getAppliedControl();
  // cancoder.getDeviceID();
  // cancoder.getNetwork();
  // cancoder.hasResetOccurred();
  // cancoder.isConnected();
}
