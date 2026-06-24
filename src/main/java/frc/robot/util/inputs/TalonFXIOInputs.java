package frc.robot.util.inputs;

import com.ctre.phoenix6.hardware.TalonFX;
import frc.robot.util.inputs.InputsUtil.LoggedControlRequest;
import frc.robot.util.inputs.InputsUtil.LoggedSignalBoolean;
import frc.robot.util.inputs.InputsUtil.LoggedSignalDouble;
import frc.robot.util.inputs.InputsUtil.LoggedSignalEnum;
import frc.robot.util.inputs.InputsUtil.LoggedSignalInteger;
import frc.robot.util.inputs.InputsUtil.LoggedSignalMeasure;

public record TalonFXIOInputs(
    LoggedControlRequest appliedControlRequest,
    double setSpeed,
    double expiration,
    int deviceID,
    String description,
    String networkName,
    boolean hasResetOccurred,
    boolean isAlive,
    boolean isConnected,
    boolean isSafetyEnabled,
    LoggedSignalMeasure position,
    LoggedSignalMeasure velocity,
    LoggedSignalMeasure acceleration,
    LoggedSignalMeasure rotorPosition,
    LoggedSignalMeasure rotorVelocity,
    LoggedSignalMeasure motorVoltage,
    LoggedSignalMeasure statorCurrent,
    LoggedSignalMeasure torqueCurrent,
    LoggedSignalMeasure supplyVoltage,
    LoggedSignalMeasure supplyCurrent,
    LoggedSignalDouble dutyCycle,
    TalonFXIOStatusInputs status,
    TalonFXIOMotorTypeInputs motorType,
    TalonFXIOClosedLoopInputs closedLoop,
    TalonFXIODifferentialInputs differential,
    TalonFXIOFaultInputs faults,
    TalonFXIOStickyFaultInputs stickyFaults) {
  public TalonFXIOInputs(TalonFX talonFX) {
    this(
        new LoggedControlRequest(talonFX.getAppliedControl()),
        talonFX.get(),
        talonFX.getExpiration(),
        talonFX.getDeviceID(),
        talonFX.getDescription(),
        talonFX.getNetwork().getName(),
        talonFX.hasResetOccurred(),
        talonFX.isAlive(),
        talonFX.isConnected(),
        talonFX.isSafetyEnabled(),
        new LoggedSignalMeasure(talonFX.getPosition()),
        new LoggedSignalMeasure(talonFX.getVelocity()),
        new LoggedSignalMeasure(talonFX.getAcceleration()),
        new LoggedSignalMeasure(talonFX.getRotorPosition()),
        new LoggedSignalMeasure(talonFX.getRotorVelocity()),
        new LoggedSignalMeasure(talonFX.getMotorVoltage()),
        new LoggedSignalMeasure(talonFX.getStatorCurrent()),
        new LoggedSignalMeasure(talonFX.getTorqueCurrent()),
        new LoggedSignalMeasure(talonFX.getSupplyVoltage()),
        new LoggedSignalMeasure(talonFX.getSupplyCurrent()),
        new LoggedSignalDouble(talonFX.getDutyCycle()),
        new TalonFXIOStatusInputs(talonFX),
        new TalonFXIOMotorTypeInputs(talonFX),
        new TalonFXIOClosedLoopInputs(talonFX),
        new TalonFXIODifferentialInputs(talonFX),
        new TalonFXIOFaultInputs(talonFX),
        new TalonFXIOStickyFaultInputs(talonFX));
  }

  public static record TalonFXIOStatusInputs(
      LoggedSignalMeasure processorTemp,
      LoggedSignalMeasure deviceTemp,
      LoggedSignalMeasure ancillaryDeviceTemp,
      LoggedSignalEnum controlMode,
      LoggedSignalEnum differentialControlMode,
      LoggedSignalEnum motorOutputStatus,
      LoggedSignalEnum bridgeOutput,
      LoggedSignalEnum appliedRotorPolarity,
      LoggedSignalEnum deviceEnable,
      LoggedSignalEnum robotEnable,
      LoggedSignalEnum forwardLimit,
      LoggedSignalEnum reverseLimit,
      LoggedSignalBoolean motionMagicIsRunning,
      LoggedSignalBoolean motionMagicAtTarget,
      LoggedSignalBoolean isProLicensed,
      LoggedSignalInteger version,
      LoggedSignalInteger versionMajor,
      LoggedSignalInteger versionMinor,
      LoggedSignalInteger versionBugfix,
      LoggedSignalInteger versionBuild) {
    public TalonFXIOStatusInputs(TalonFX talonFX) {
      this(
          new LoggedSignalMeasure(talonFX.getProcessorTemp()),
          new LoggedSignalMeasure(talonFX.getDeviceTemp()),
          new LoggedSignalMeasure(talonFX.getAncillaryDeviceTemp()),
          new LoggedSignalEnum(talonFX.getControlMode()),
          new LoggedSignalEnum(talonFX.getDifferentialControlMode()),
          new LoggedSignalEnum(talonFX.getMotorOutputStatus()),
          new LoggedSignalEnum(talonFX.getBridgeOutput()),
          new LoggedSignalEnum(talonFX.getAppliedRotorPolarity()),
          new LoggedSignalEnum(talonFX.getDeviceEnable()),
          new LoggedSignalEnum(talonFX.getRobotEnable()),
          new LoggedSignalEnum(talonFX.getForwardLimit()),
          new LoggedSignalEnum(talonFX.getReverseLimit()),
          new LoggedSignalBoolean(talonFX.getMotionMagicIsRunning()),
          new LoggedSignalBoolean(talonFX.getMotionMagicAtTarget()),
          new LoggedSignalBoolean(talonFX.getIsProLicensed()),
          new LoggedSignalInteger(talonFX.getVersion()),
          new LoggedSignalInteger(talonFX.getVersionMajor()),
          new LoggedSignalInteger(talonFX.getVersionMinor()),
          new LoggedSignalInteger(talonFX.getVersionBugfix()),
          new LoggedSignalInteger(talonFX.getVersionBuild()));
    }
  }

  public static record TalonFXIOClosedLoopInputs(
      LoggedSignalInteger closedLoopSlot,
      LoggedSignalDouble closedLoopReference,
      LoggedSignalDouble closedLoopReferenceSlope,
      LoggedSignalDouble closedLoopError,
      LoggedSignalDouble closedLoopOutput,
      LoggedSignalDouble closedLoopProportionalOutput,
      LoggedSignalDouble closedLoopIntegratedOutput,
      LoggedSignalDouble closedLoopDerivativeOutput,
      LoggedSignalDouble closedLoopFeedForward) {
    public TalonFXIOClosedLoopInputs(TalonFX talonFX) {
      this(
          new LoggedSignalInteger(talonFX.getClosedLoopSlot()),
          new LoggedSignalDouble(talonFX.getClosedLoopReference()),
          new LoggedSignalDouble(talonFX.getClosedLoopReferenceSlope()),
          new LoggedSignalDouble(talonFX.getClosedLoopError()),
          new LoggedSignalDouble(talonFX.getClosedLoopOutput()),
          new LoggedSignalDouble(talonFX.getClosedLoopProportionalOutput()),
          new LoggedSignalDouble(talonFX.getClosedLoopIntegratedOutput()),
          new LoggedSignalDouble(talonFX.getClosedLoopDerivativeOutput()),
          new LoggedSignalDouble(talonFX.getClosedLoopFeedForward()));
    }
  }

  public static record TalonFXIODifferentialInputs(
      LoggedSignalMeasure differentialAveragePosition,
      LoggedSignalMeasure differentialAverageVelocity,
      LoggedSignalMeasure differentialDifferencePosition,
      LoggedSignalMeasure differentialDifferenceVelocity,
      LoggedSignalDouble differentialOutput,
      TalonFXIODifferentialClosedLoopInputs closedLoop) {
    public TalonFXIODifferentialInputs(TalonFX talonFX) {
      this(
          new LoggedSignalMeasure(talonFX.getDifferentialAveragePosition()),
          new LoggedSignalMeasure(talonFX.getDifferentialAverageVelocity()),
          new LoggedSignalMeasure(talonFX.getDifferentialDifferencePosition()),
          new LoggedSignalMeasure(talonFX.getDifferentialDifferenceVelocity()),
          new LoggedSignalDouble(talonFX.getDifferentialOutput()),
          new TalonFXIODifferentialClosedLoopInputs(talonFX));
    }
  }

  public static record TalonFXIODifferentialClosedLoopInputs(
      LoggedSignalInteger closedLoopSlot,
      LoggedSignalDouble closedLoopReference,
      LoggedSignalDouble closedLoopReferenceSlope,
      LoggedSignalDouble closedLoopError,
      LoggedSignalDouble closedLoopOutput,
      LoggedSignalDouble closedLoopProportionalOutput,
      LoggedSignalDouble closedLoopIntegratedOutput,
      LoggedSignalDouble closedLoopDerivativeOutput,
      LoggedSignalDouble closedLoopFeedForward) {
    public TalonFXIODifferentialClosedLoopInputs(TalonFX talonFX) {
      this(
          new LoggedSignalInteger(talonFX.getDifferentialClosedLoopSlot()),
          new LoggedSignalDouble(talonFX.getDifferentialClosedLoopReference()),
          new LoggedSignalDouble(talonFX.getDifferentialClosedLoopReferenceSlope()),
          new LoggedSignalDouble(talonFX.getDifferentialClosedLoopError()),
          new LoggedSignalDouble(talonFX.getDifferentialClosedLoopOutput()),
          new LoggedSignalDouble(talonFX.getDifferentialClosedLoopProportionalOutput()),
          new LoggedSignalDouble(talonFX.getDifferentialClosedLoopIntegratedOutput()),
          new LoggedSignalDouble(talonFX.getDifferentialClosedLoopDerivativeOutput()),
          new LoggedSignalDouble(talonFX.getDifferentialClosedLoopFeedForward()));
    }
  }

  public static record TalonFXIOMotorTypeInputs(
      LoggedSignalEnum connectedMotor,
      LoggedSignalMeasure motorKT,
      LoggedSignalMeasure motorKV,
      LoggedSignalMeasure motorStallCurrent) {
    public TalonFXIOMotorTypeInputs(TalonFX talonFX) {
      this(
          new LoggedSignalEnum(talonFX.getConnectedMotor()),
          new LoggedSignalMeasure(talonFX.getMotorKT()),
          new LoggedSignalMeasure(talonFX.getMotorKV()),
          new LoggedSignalMeasure(talonFX.getMotorStallCurrent()));
    }
  }

  public static record TalonFXIOFaultInputs(
      LoggedSignalInteger faultField,
      LoggedSignalBoolean bootDuringEnable,
      LoggedSignalBoolean bridgeBrownout,
      LoggedSignalBoolean deviceTemp,
      LoggedSignalBoolean forwardHardLimit,
      LoggedSignalBoolean forwardSoftLimit,
      LoggedSignalBoolean fusedSensorOutOfSync,
      LoggedSignalBoolean hardware,
      LoggedSignalBoolean missingDifferentialFX,
      LoggedSignalBoolean missingHardLimitRemote,
      LoggedSignalBoolean missingSoftLimitRemote,
      LoggedSignalBoolean overSupplyV,
      LoggedSignalBoolean procTemp,
      LoggedSignalBoolean remoteSensorDataInvalid,
      LoggedSignalBoolean remoteSensorPosOverflow,
      LoggedSignalBoolean remoteSensorReset,
      LoggedSignalBoolean reverseHardLimit,
      LoggedSignalBoolean reverseSoftLimit,
      LoggedSignalBoolean staticBrakeDisabled,
      LoggedSignalBoolean statorCurrLimit,
      LoggedSignalBoolean supplyCurrLimit,
      LoggedSignalBoolean undervoltage,
      LoggedSignalBoolean unlicensedFeatureInUse,
      LoggedSignalBoolean unstableSupplyV,
      LoggedSignalBoolean usingFusedCANcoderWhileUnlicensed) {
    public TalonFXIOFaultInputs(TalonFX talonFX) {
      this(
          new LoggedSignalInteger(talonFX.getFaultField()),
          new LoggedSignalBoolean(talonFX.getFault_BootDuringEnable()),
          new LoggedSignalBoolean(talonFX.getFault_BridgeBrownout()),
          new LoggedSignalBoolean(talonFX.getFault_DeviceTemp()),
          new LoggedSignalBoolean(talonFX.getFault_ForwardHardLimit()),
          new LoggedSignalBoolean(talonFX.getFault_ForwardSoftLimit()),
          new LoggedSignalBoolean(talonFX.getFault_FusedSensorOutOfSync()),
          new LoggedSignalBoolean(talonFX.getFault_Hardware()),
          new LoggedSignalBoolean(talonFX.getFault_MissingDifferentialFX()),
          new LoggedSignalBoolean(talonFX.getFault_MissingHardLimitRemote()),
          new LoggedSignalBoolean(talonFX.getFault_MissingSoftLimitRemote()),
          new LoggedSignalBoolean(talonFX.getFault_OverSupplyV()),
          new LoggedSignalBoolean(talonFX.getFault_ProcTemp()),
          new LoggedSignalBoolean(talonFX.getFault_RemoteSensorDataInvalid()),
          new LoggedSignalBoolean(talonFX.getFault_RemoteSensorPosOverflow()),
          new LoggedSignalBoolean(talonFX.getFault_RemoteSensorReset()),
          new LoggedSignalBoolean(talonFX.getFault_ReverseHardLimit()),
          new LoggedSignalBoolean(talonFX.getFault_ReverseSoftLimit()),
          new LoggedSignalBoolean(talonFX.getFault_StaticBrakeDisabled()),
          new LoggedSignalBoolean(talonFX.getFault_StatorCurrLimit()),
          new LoggedSignalBoolean(talonFX.getFault_SupplyCurrLimit()),
          new LoggedSignalBoolean(talonFX.getFault_Undervoltage()),
          new LoggedSignalBoolean(talonFX.getFault_UnlicensedFeatureInUse()),
          new LoggedSignalBoolean(talonFX.getFault_UnstableSupplyV()),
          new LoggedSignalBoolean(talonFX.getFault_UsingFusedCANcoderWhileUnlicensed()));
    }
  }

  public static record TalonFXIOStickyFaultInputs(
      LoggedSignalInteger stickyFaultField,
      LoggedSignalBoolean bootDuringEnable,
      LoggedSignalBoolean bridgeBrownout,
      LoggedSignalBoolean deviceTemp,
      LoggedSignalBoolean forwardHardLimit,
      LoggedSignalBoolean forwardSoftLimit,
      LoggedSignalBoolean fusedSensorOutOfSync,
      LoggedSignalBoolean hardware,
      LoggedSignalBoolean missingDifferentialFX,
      LoggedSignalBoolean missingHardLimitRemote,
      LoggedSignalBoolean missingSoftLimitRemote,
      LoggedSignalBoolean overSupplyV,
      LoggedSignalBoolean procTemp,
      LoggedSignalBoolean remoteSensorDataInvalid,
      LoggedSignalBoolean remoteSensorPosOverflow,
      LoggedSignalBoolean remoteSensorReset,
      LoggedSignalBoolean reverseHardLimit,
      LoggedSignalBoolean reverseSoftLimit,
      LoggedSignalBoolean staticBrakeDisabled,
      LoggedSignalBoolean statorCurrLimit,
      LoggedSignalBoolean supplyCurrLimit,
      LoggedSignalBoolean undervoltage,
      LoggedSignalBoolean unlicensedFeatureInUse,
      LoggedSignalBoolean unstableSupplyV,
      LoggedSignalBoolean usingFusedCANcoderWhileUnlicensed) {
    public TalonFXIOStickyFaultInputs(TalonFX talonFX) {
      this(
          new LoggedSignalInteger(talonFX.getStickyFaultField()),
          new LoggedSignalBoolean(talonFX.getStickyFault_BootDuringEnable()),
          new LoggedSignalBoolean(talonFX.getStickyFault_BridgeBrownout()),
          new LoggedSignalBoolean(talonFX.getStickyFault_DeviceTemp()),
          new LoggedSignalBoolean(talonFX.getStickyFault_ForwardHardLimit()),
          new LoggedSignalBoolean(talonFX.getStickyFault_ForwardSoftLimit()),
          new LoggedSignalBoolean(talonFX.getStickyFault_FusedSensorOutOfSync()),
          new LoggedSignalBoolean(talonFX.getStickyFault_Hardware()),
          new LoggedSignalBoolean(talonFX.getStickyFault_MissingDifferentialFX()),
          new LoggedSignalBoolean(talonFX.getStickyFault_MissingHardLimitRemote()),
          new LoggedSignalBoolean(talonFX.getStickyFault_MissingSoftLimitRemote()),
          new LoggedSignalBoolean(talonFX.getStickyFault_OverSupplyV()),
          new LoggedSignalBoolean(talonFX.getStickyFault_ProcTemp()),
          new LoggedSignalBoolean(talonFX.getStickyFault_RemoteSensorDataInvalid()),
          new LoggedSignalBoolean(talonFX.getStickyFault_RemoteSensorPosOverflow()),
          new LoggedSignalBoolean(talonFX.getStickyFault_RemoteSensorReset()),
          new LoggedSignalBoolean(talonFX.getStickyFault_ReverseHardLimit()),
          new LoggedSignalBoolean(talonFX.getStickyFault_ReverseSoftLimit()),
          new LoggedSignalBoolean(talonFX.getStickyFault_StaticBrakeDisabled()),
          new LoggedSignalBoolean(talonFX.getStickyFault_StatorCurrLimit()),
          new LoggedSignalBoolean(talonFX.getStickyFault_SupplyCurrLimit()),
          new LoggedSignalBoolean(talonFX.getStickyFault_Undervoltage()),
          new LoggedSignalBoolean(talonFX.getStickyFault_UnlicensedFeatureInUse()),
          new LoggedSignalBoolean(talonFX.getStickyFault_UnstableSupplyV()),
          new LoggedSignalBoolean(talonFX.getStickyFault_UsingFusedCANcoderWhileUnlicensed()));
    }
  }

  // talonFx.getAppliedControl();
  // talonFx.get();
  // talonFx.getDescription();
  // talonFx.getDeviceID();
  // talonFx.getExpiration();
  // talonFx.getNetwork();
  // talonFx.getSimState();
  // talonFx.hasResetOccurred();
  // talonFx.isAlive();
  // talonFx.isConnected();
  // talonFx.isSafetyEnabled();

  // talonFx.getPosition();
  // talonFx.getVelocity();
  // talonFx.getAcceleration();
  // talonFx.getRotorPosition();
  // talonFx.getRotorVelocity();
  // talonFx.getDutyCycle();
  // talonFx.getMotorVoltage();
  // talonFx.getStatorCurrent();
  // talonFx.getTorqueCurrent();
  // talonFx.getSupplyVoltage();
  // talonFx.getSupplyCurrent();

  // status
  // talonFx.getControlMode();
  // talonFx.getDifferentialControlMode();
  // talonFx.getMotorOutputStatus();
  // talonFx.getBridgeOutput();
  // talonFx.getMotionMagicIsRunning();
  // talonFx.getAppliedRotorPolarity();
  // talonFx.getDeviceEnable();
  // talonFx.getRobotEnable();
  // talonFx.getMotionMagicAtTarget();
  // talonFx.getForwardLimit();
  // talonFx.getReverseLimit();
  // talonFx.getProcessorTemp();
  // talonFx.getDeviceTemp();
  // talonFx.getAncillaryDeviceTemp();
  // talonFx.getIsProLicensed();
  // talonFx.getVersion();
  // talonFx.getVersionMajor();
  // talonFx.getVersionMinor();
  // talonFx.getVersionBugfix();
  // talonFx.getVersionBuild();

  // motorType
  // talonFx.getConnectedMotor();
  // talonFx.getMotorKT();
  // talonFx.getMotorKV();
  // talonFx.getMotorStallCurrent();

  // closedLoop
  // talonFx.getClosedLoopSlot();
  // talonFx.getClosedLoopReference();
  // talonFx.getClosedLoopReferenceSlope();
  // talonFx.getClosedLoopError();
  // talonFx.getClosedLoopOutput();
  // talonFx.getClosedLoopProportionalOutput();
  // talonFx.getClosedLoopIntegratedOutput();
  // talonFx.getClosedLoopDerivativeOutput();
  // talonFx.getClosedLoopFeedForward();

  // differential
  // talonFx.getDifferentialAveragePosition();
  // talonFx.getDifferentialAverageVelocity();
  // talonFx.getDifferentialDifferencePosition();
  // talonFx.getDifferentialDifferenceVelocity();
  // talonFx.getDifferentialOutput();
  //   differentialClosedLoop
  //   talonFx.getDifferentialClosedLoopSlot();
  //   talonFx.getDifferentialClosedLoopReference();
  //   talonFx.getDifferentialClosedLoopReferenceSlope();
  //   talonFx.getDifferentialClosedLoopError();
  //   talonFx.getDifferentialClosedLoopOutput();
  //   talonFx.getDifferentialClosedLoopProportionalOutput();
  //   talonFx.getDifferentialClosedLoopIntegratedOutput();
  //   talonFx.getDifferentialClosedLoopDerivativeOutput();
  //   talonFx.getDifferentialClosedLoopFeedForward();

  // faults
  // talonFx.getFaultField();
  // talonFx.getFault_BootDuringEnable();
  // talonFx.getFault_BridgeBrownout();
  // talonFx.getFault_DeviceTemp();
  // talonFx.getFault_ForwardHardLimit();
  // talonFx.getFault_ForwardSoftLimit();
  // talonFx.getFault_FusedSensorOutOfSync();
  // talonFx.getFault_Hardware();
  // talonFx.getFault_MissingDifferentialFX();
  // talonFx.getFault_MissingHardLimitRemote();
  // talonFx.getFault_MissingSoftLimitRemote();
  // talonFx.getFault_OverSupplyV();
  // talonFx.getFault_ProcTemp();
  // talonFx.getFault_RemoteSensorDataInvalid();
  // talonFx.getFault_RemoteSensorPosOverflow();
  // talonFx.getFault_RemoteSensorReset();
  // talonFx.getFault_ReverseHardLimit();
  // talonFx.getFault_ReverseSoftLimit();
  // talonFx.getFault_StaticBrakeDisabled();
  // talonFx.getFault_StatorCurrLimit();
  // talonFx.getFault_SupplyCurrLimit();
  // talonFx.getFault_Undervoltage();
  // talonFx.getFault_UnlicensedFeatureInUse();
  // talonFx.getFault_UnstableSupplyV();
  // talonFx.getFault_UsingFusedCANcoderWhileUnlicensed();

  // sticky faults
  // talonFx.getStickyFaultField();
  // talonFx.getStickyFault_BootDuringEnable();
  // talonFx.getStickyFault_BridgeBrownout();
  // talonFx.getStickyFault_DeviceTemp();
  // talonFx.getStickyFault_ForwardHardLimit();
  // talonFx.getStickyFault_ForwardSoftLimit();
  // talonFx.getStickyFault_FusedSensorOutOfSync();
  // talonFx.getStickyFault_Hardware();
  // talonFx.getStickyFault_MissingDifferentialFX();
  // talonFx.getStickyFault_MissingHardLimitRemote();
  // talonFx.getStickyFault_MissingSoftLimitRemote();
  // talonFx.getStickyFault_OverSupplyV();
  // talonFx.getStickyFault_ProcTemp();
  // talonFx.getStickyFault_RemoteSensorDataInvalid();
  // talonFx.getStickyFault_RemoteSensorPosOverflow();
  // talonFx.getStickyFault_RemoteSensorReset();
  // talonFx.getStickyFault_ReverseHardLimit();
  // talonFx.getStickyFault_ReverseSoftLimit();
  // talonFx.getStickyFault_StaticBrakeDisabled();
  // talonFx.getStickyFault_StatorCurrLimit();
  // talonFx.getStickyFault_SupplyCurrLimit();
  // talonFx.getStickyFault_Undervoltage();
  // talonFx.getStickyFault_UnlicensedFeatureInUse();
  // talonFx.getStickyFault_UnstableSupplyV();
  // talonFx.getStickyFault_UsingFusedCANcoderWhileUnlicensed();

}
