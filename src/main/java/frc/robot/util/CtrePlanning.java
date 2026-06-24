package frc.robot.util;

import com.ctre.phoenix6.controls.CoastOut;
import com.ctre.phoenix6.controls.ColorFlowAnimation;
import com.ctre.phoenix6.controls.DifferentialDutyCycle;
import com.ctre.phoenix6.controls.DifferentialFollower;
import com.ctre.phoenix6.controls.DifferentialMotionMagicDutyCycle;
import com.ctre.phoenix6.controls.DifferentialMotionMagicExpoDutyCycle;
import com.ctre.phoenix6.controls.DifferentialMotionMagicExpoVoltage;
import com.ctre.phoenix6.controls.DifferentialMotionMagicVelocityDutyCycle;
import com.ctre.phoenix6.controls.DifferentialMotionMagicVelocityVoltage;
import com.ctre.phoenix6.controls.DifferentialMotionMagicVoltage;
import com.ctre.phoenix6.controls.DifferentialPositionDutyCycle;
import com.ctre.phoenix6.controls.DifferentialPositionVoltage;
import com.ctre.phoenix6.controls.DifferentialStrictFollower;
import com.ctre.phoenix6.controls.DifferentialVelocityDutyCycle;
import com.ctre.phoenix6.controls.DifferentialVelocityVoltage;
import com.ctre.phoenix6.controls.DifferentialVoltage;
import com.ctre.phoenix6.controls.DutyCycleOut;
import com.ctre.phoenix6.controls.DynamicMotionMagicDutyCycle;
import com.ctre.phoenix6.controls.DynamicMotionMagicExpoDutyCycle;
import com.ctre.phoenix6.controls.DynamicMotionMagicExpoTorqueCurrentFOC;
import com.ctre.phoenix6.controls.DynamicMotionMagicExpoVoltage;
import com.ctre.phoenix6.controls.DynamicMotionMagicTorqueCurrentFOC;
import com.ctre.phoenix6.controls.DynamicMotionMagicVoltage;
import com.ctre.phoenix6.controls.EmptyAnimation;
import com.ctre.phoenix6.controls.EmptyControl;
import com.ctre.phoenix6.controls.FireAnimation;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.controls.LarsonAnimation;
import com.ctre.phoenix6.controls.ModulateVBatOut;
import com.ctre.phoenix6.controls.MotionMagicDutyCycle;
import com.ctre.phoenix6.controls.MotionMagicExpoDutyCycle;
import com.ctre.phoenix6.controls.MotionMagicExpoTorqueCurrentFOC;
import com.ctre.phoenix6.controls.MotionMagicExpoVoltage;
import com.ctre.phoenix6.controls.MotionMagicTorqueCurrentFOC;
import com.ctre.phoenix6.controls.MotionMagicVelocityDutyCycle;
import com.ctre.phoenix6.controls.MotionMagicVelocityTorqueCurrentFOC;
import com.ctre.phoenix6.controls.MotionMagicVelocityVoltage;
import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.controls.MusicTone;
import com.ctre.phoenix6.controls.NeutralOut;
import com.ctre.phoenix6.controls.PositionDutyCycle;
import com.ctre.phoenix6.controls.PositionTorqueCurrentFOC;
import com.ctre.phoenix6.controls.PositionVoltage;
import com.ctre.phoenix6.controls.RainbowAnimation;
import com.ctre.phoenix6.controls.RgbFadeAnimation;
import com.ctre.phoenix6.controls.SingleFadeAnimation;
import com.ctre.phoenix6.controls.SolidColor;
import com.ctre.phoenix6.controls.StaticBrake;
import com.ctre.phoenix6.controls.StrictFollower;
import com.ctre.phoenix6.controls.StrobeAnimation;
import com.ctre.phoenix6.controls.TorqueCurrentFOC;
import com.ctre.phoenix6.controls.TwinkleAnimation;
import com.ctre.phoenix6.controls.TwinkleOffAnimation;
import com.ctre.phoenix6.controls.VelocityDutyCycle;
import com.ctre.phoenix6.controls.VelocityTorqueCurrentFOC;
import com.ctre.phoenix6.controls.VelocityVoltage;
import com.ctre.phoenix6.controls.VoltageOut;
import com.ctre.phoenix6.controls.compound.Diff_DutyCycleOut_Open;
import com.ctre.phoenix6.controls.compound.Diff_DutyCycleOut_Position;
import com.ctre.phoenix6.controls.compound.Diff_DutyCycleOut_Velocity;
import com.ctre.phoenix6.controls.compound.Diff_MotionMagicDutyCycle_Open;
import com.ctre.phoenix6.controls.compound.Diff_MotionMagicDutyCycle_Position;
import com.ctre.phoenix6.controls.compound.Diff_MotionMagicDutyCycle_Velocity;
import com.ctre.phoenix6.controls.compound.Diff_MotionMagicExpoDutyCycle_Open;
import com.ctre.phoenix6.controls.compound.Diff_MotionMagicExpoDutyCycle_Position;
import com.ctre.phoenix6.controls.compound.Diff_MotionMagicExpoDutyCycle_Velocity;
import com.ctre.phoenix6.controls.compound.Diff_MotionMagicExpoTorqueCurrentFOC_Open;
import com.ctre.phoenix6.controls.compound.Diff_MotionMagicExpoTorqueCurrentFOC_Position;
import com.ctre.phoenix6.controls.compound.Diff_MotionMagicExpoTorqueCurrentFOC_Velocity;
import com.ctre.phoenix6.controls.compound.Diff_MotionMagicExpoVoltage_Open;
import com.ctre.phoenix6.controls.compound.Diff_MotionMagicExpoVoltage_Position;
import com.ctre.phoenix6.controls.compound.Diff_MotionMagicExpoVoltage_Velocity;
import com.ctre.phoenix6.controls.compound.Diff_MotionMagicTorqueCurrentFOC_Open;
import com.ctre.phoenix6.controls.compound.Diff_MotionMagicTorqueCurrentFOC_Position;
import com.ctre.phoenix6.controls.compound.Diff_MotionMagicTorqueCurrentFOC_Velocity;
import com.ctre.phoenix6.controls.compound.Diff_MotionMagicVelocityDutyCycle_Open;
import com.ctre.phoenix6.controls.compound.Diff_MotionMagicVelocityDutyCycle_Position;
import com.ctre.phoenix6.controls.compound.Diff_MotionMagicVelocityDutyCycle_Velocity;
import com.ctre.phoenix6.controls.compound.Diff_MotionMagicVelocityTorqueCurrentFOC_Open;
import com.ctre.phoenix6.controls.compound.Diff_MotionMagicVelocityTorqueCurrentFOC_Position;
import com.ctre.phoenix6.controls.compound.Diff_MotionMagicVelocityTorqueCurrentFOC_Velocity;
import com.ctre.phoenix6.controls.compound.Diff_MotionMagicVelocityVoltage_Open;
import com.ctre.phoenix6.controls.compound.Diff_MotionMagicVelocityVoltage_Position;
import com.ctre.phoenix6.controls.compound.Diff_MotionMagicVelocityVoltage_Velocity;
import com.ctre.phoenix6.controls.compound.Diff_MotionMagicVoltage_Open;
import com.ctre.phoenix6.controls.compound.Diff_MotionMagicVoltage_Position;
import com.ctre.phoenix6.controls.compound.Diff_MotionMagicVoltage_Velocity;
import com.ctre.phoenix6.controls.compound.Diff_PositionDutyCycle_Open;
import com.ctre.phoenix6.controls.compound.Diff_PositionDutyCycle_Position;
import com.ctre.phoenix6.controls.compound.Diff_PositionDutyCycle_Velocity;
import com.ctre.phoenix6.controls.compound.Diff_PositionTorqueCurrentFOC_Open;
import com.ctre.phoenix6.controls.compound.Diff_PositionTorqueCurrentFOC_Position;
import com.ctre.phoenix6.controls.compound.Diff_PositionTorqueCurrentFOC_Velocity;
import com.ctre.phoenix6.controls.compound.Diff_PositionVoltage_Open;
import com.ctre.phoenix6.controls.compound.Diff_PositionVoltage_Position;
import com.ctre.phoenix6.controls.compound.Diff_PositionVoltage_Velocity;
import com.ctre.phoenix6.controls.compound.Diff_TorqueCurrentFOC_Open;
import com.ctre.phoenix6.controls.compound.Diff_TorqueCurrentFOC_Position;
import com.ctre.phoenix6.controls.compound.Diff_TorqueCurrentFOC_Velocity;
import com.ctre.phoenix6.controls.compound.Diff_VelocityDutyCycle_Open;
import com.ctre.phoenix6.controls.compound.Diff_VelocityDutyCycle_Position;
import com.ctre.phoenix6.controls.compound.Diff_VelocityDutyCycle_Velocity;
import com.ctre.phoenix6.controls.compound.Diff_VelocityTorqueCurrentFOC_Open;
import com.ctre.phoenix6.controls.compound.Diff_VelocityTorqueCurrentFOC_Position;
import com.ctre.phoenix6.controls.compound.Diff_VelocityTorqueCurrentFOC_Velocity;
import com.ctre.phoenix6.controls.compound.Diff_VelocityVoltage_Open;
import com.ctre.phoenix6.controls.compound.Diff_VelocityVoltage_Position;
import com.ctre.phoenix6.controls.compound.Diff_VelocityVoltage_Velocity;
import com.ctre.phoenix6.controls.compound.Diff_VoltageOut_Open;
import com.ctre.phoenix6.controls.compound.Diff_VoltageOut_Position;
import com.ctre.phoenix6.controls.compound.Diff_VoltageOut_Velocity;
import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.hardware.CANdi;
import com.ctre.phoenix6.hardware.CANdle;
import com.ctre.phoenix6.hardware.CANrange;
import com.ctre.phoenix6.hardware.Pigeon2;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.hardware.TalonFXS;
import com.ctre.phoenix6.mechanisms.DifferentialMechanism;

public class CtrePlanning {

  // TalonFX

  MusicTone /*                                         */ bf; // Music
  NeutralOut /*                                        */ bc; // Neutral Output
  CoastOut /*                                          */ bd;
  StaticBrake /*                                       */ be;
  Follower /*                                          */ ay; // Followers
  StrictFollower /*                                    */ az;
  DifferentialFollower /*                              */ ba;
  DifferentialStrictFollower /*                        */ bb;
  DutyCycleOut /*                                      */ aa; // DutyCycle
  PositionDutyCycle /*                                 */ ad;
  VelocityDutyCycle /*                                 */ ag;
  MotionMagicDutyCycle /*                              */ aj;
  MotionMagicExpoDutyCycle /*                          */ bj;
  MotionMagicVelocityDutyCycle /*                      */ bg;
  DynamicMotionMagicDutyCycle /*                       */ bm;
  DynamicMotionMagicExpoDutyCycle /*                   */ bp;
  Diff_DutyCycleOut_Open /*                            */
      ce; // DutyCycle with DutyCycleOut differential
  Diff_PositionDutyCycle_Open /*                       */ cf;
  Diff_VelocityDutyCycle_Open /*                       */ cg;
  Diff_MotionMagicDutyCycle_Open /*                    */ ch;
  Diff_MotionMagicExpoDutyCycle_Open /*                */ ci;
  Diff_MotionMagicVelocityDutyCycle_Open /*            */ cj;
  Diff_DutyCycleOut_Position /*                        */
      bs; // DutyCycle with PositionDutyCycle differential
  Diff_PositionDutyCycle_Position /*                   */ bt;
  Diff_VelocityDutyCycle_Position /*                   */ bu;
  Diff_MotionMagicDutyCycle_Position /*                */ bv;
  Diff_MotionMagicExpoDutyCycle_Position /*            */ bw;
  Diff_MotionMagicVelocityDutyCycle_Position /*        */ bx;
  Diff_DutyCycleOut_Velocity /*                        */
      by; // DutyCycle with VelocityDutyCycle differential
  Diff_PositionDutyCycle_Velocity /*                   */ bz;
  Diff_VelocityDutyCycle_Velocity /*                   */ ca;
  Diff_MotionMagicDutyCycle_Velocity /*                */ cb;
  Diff_MotionMagicExpoDutyCycle_Velocity /*            */ cc;
  Diff_MotionMagicVelocityDutyCycle_Velocity /*        */ cd;
  VoltageOut /*                                        */ ac; // Voltage
  PositionVoltage /*                                   */ ae;
  VelocityVoltage /*                                   */ ah;
  MotionMagicVoltage /*                                */ ak;
  MotionMagicExpoVoltage /*                            */ bk;
  MotionMagicVelocityVoltage /*                        */ bi;
  DynamicMotionMagicVoltage /*                         */ bn;
  DynamicMotionMagicExpoVoltage /*                     */ bq;
  Diff_VoltageOut_Open /*                              */
      cw; // Voltage with VoltageOut differential
  Diff_PositionVoltage_Open /*                         */ cx;
  Diff_VelocityVoltage_Open /*                         */ cy;
  Diff_MotionMagicVoltage_Open /*                      */ cz;
  Diff_MotionMagicExpoVoltage_Open /*                  */ da;
  Diff_MotionMagicVelocityVoltage_Open /*              */ db;
  Diff_VoltageOut_Position /*                          */
      ck; // Voltage with PositionVoltage differential
  Diff_PositionVoltage_Position /*                     */ cl;
  Diff_VelocityVoltage_Position /*                     */ cm;
  Diff_MotionMagicVoltage_Position /*                  */ cn;
  Diff_MotionMagicExpoVoltage_Position /*              */ co;
  Diff_MotionMagicVelocityVoltage_Position /*          */ cp;
  Diff_VoltageOut_Velocity /*                          */
      cq; // Voltage with VelocityVoltage differential
  Diff_PositionVoltage_Velocity /*                     */ cr;
  Diff_VelocityVoltage_Velocity /*                     */ cs;
  Diff_MotionMagicVoltage_Velocity /*                  */ ct;
  Diff_MotionMagicExpoVoltage_Velocity /*              */ cu;
  Diff_MotionMagicVelocityVoltage_Velocity /*          */ cv;
  TorqueCurrentFOC /*                                  */ ab; // TorqueCurrentFOC
  PositionTorqueCurrentFOC /*                          */ af;
  VelocityTorqueCurrentFOC /*                          */ ai;
  MotionMagicTorqueCurrentFOC /*                       */ al;
  MotionMagicExpoTorqueCurrentFOC /*                   */ bl;
  MotionMagicVelocityTorqueCurrentFOC /*               */ bh;
  DynamicMotionMagicTorqueCurrentFOC /*                */ bo;
  DynamicMotionMagicExpoTorqueCurrentFOC /*            */
      br; // TorqueCurrentFOC with TorqueCurrentFOC differential
  Diff_TorqueCurrentFOC_Open /*                        */ dp;
  Diff_PositionTorqueCurrentFOC_Open /*                */ dq;
  Diff_VelocityTorqueCurrentFOC_Open /*                */ dr;
  Diff_MotionMagicTorqueCurrentFOC_Open /*             */ ds;
  Diff_MotionMagicExpoTorqueCurrentFOC_Open /*         */ dt;
  Diff_MotionMagicVelocityTorqueCurrentFOC_Open /*     */ du;
  Diff_TorqueCurrentFOC_Position /*                    */
      dc; // TorqueCurrentFOC with PositionTorqueCurrentFOC differential
  Diff_PositionTorqueCurrentFOC_Position /*            */ dd;
  Diff_VelocityTorqueCurrentFOC_Position /*            */ de;
  Diff_MotionMagicTorqueCurrentFOC_Position /*         */ df;
  Diff_MotionMagicExpoTorqueCurrentFOC_Position /*     */ dg;
  Diff_MotionMagicVelocityTorqueCurrentFOC_Position /* */ dh;
  Diff_TorqueCurrentFOC_Velocity /*                    */
      di; // TorqueCurrentFOC with VelocityTorqueCurrentFOC differential
  Diff_PositionTorqueCurrentFOC_Velocity /*            */ dj;
  Diff_VelocityTorqueCurrentFOC_Velocity /*            */ dk;
  Diff_MotionMagicTorqueCurrentFOC_Velocity /*         */ dl;
  Diff_MotionMagicExpoTorqueCurrentFOC_Velocity /*     */ dm;
  Diff_MotionMagicVelocityTorqueCurrentFOC_Velocity /* */ dn;
  DifferentialDutyCycle /*                             */
      am; // duplicates with limited functionality
  DifferentialVoltage /*                               */ an;
  DifferentialPositionDutyCycle /*                     */ ao;
  DifferentialPositionVoltage /*                       */ ap;
  DifferentialVelocityDutyCycle /*                     */ aq;
  DifferentialVelocityVoltage /*                       */ ar;
  DifferentialMotionMagicDutyCycle /*                  */ as;
  DifferentialMotionMagicVoltage /*                    */ at;
  DifferentialMotionMagicVelocityDutyCycle /*          */ aw;
  DifferentialMotionMagicVelocityVoltage /*            */ ax;
  DifferentialMotionMagicExpoDutyCycle /*              */ au;
  DifferentialMotionMagicExpoVoltage /*                */ av;

  public void talonFX(final TalonFX talonFX) {
    // 108 StatusSignal getters
    // 5 pos + vel
    // 6 volt + amp
    // 6 control mode
    // 8 talon changing data
    // 4 basic motor data
    // 6 talon basic data
    // 9 closed loop
    // 5 diff mech
    // 9 diff closed loop
    // 25 faults
    // 25 sticky faults
    //
    // 12 misc non-StatusSignal getters

    talonFX.getPosition();
    talonFX.getVelocity();
    talonFX.getAcceleration();
    talonFX.getRotorPosition();
    talonFX.getRotorVelocity();

    talonFX.getDutyCycle();
    talonFX.getMotorVoltage();
    talonFX.getStatorCurrent();
    talonFX.getTorqueCurrent();
    talonFX.getSupplyVoltage();
    talonFX.getSupplyCurrent();

    talonFX.getControlMode();
    talonFX.getDifferentialControlMode();
    talonFX.getMotorOutputStatus();
    talonFX.getBridgeOutput();
    talonFX.getMotionMagicIsRunning();
    talonFX.getAppliedRotorPolarity();

    talonFX.getDeviceEnable();
    talonFX.getRobotEnable();
    talonFX.getMotionMagicAtTarget();
    talonFX.getForwardLimit();
    talonFX.getReverseLimit();
    talonFX.getProcessorTemp();
    talonFX.getDeviceTemp();
    talonFX.getAncillaryDeviceTemp();

    talonFX.getConnectedMotor();
    talonFX.getMotorKT();
    talonFX.getMotorKV();
    talonFX.getMotorStallCurrent();

    talonFX.getIsProLicensed();
    talonFX.getVersion();
    talonFX.getVersionMajor();
    talonFX.getVersionMinor();
    talonFX.getVersionBugfix();
    talonFX.getVersionBuild();

    talonFX.getClosedLoopSlot();
    talonFX.getClosedLoopReference();
    talonFX.getClosedLoopReferenceSlope();
    talonFX.getClosedLoopError();
    talonFX.getClosedLoopOutput();
    talonFX.getClosedLoopProportionalOutput();
    talonFX.getClosedLoopIntegratedOutput();
    talonFX.getClosedLoopDerivativeOutput();
    talonFX.getClosedLoopFeedForward();

    talonFX.getDifferentialAveragePosition();
    talonFX.getDifferentialAverageVelocity();
    talonFX.getDifferentialDifferencePosition();
    talonFX.getDifferentialDifferenceVelocity();
    talonFX.getDifferentialOutput();

    talonFX.getDifferentialClosedLoopSlot();
    talonFX.getDifferentialClosedLoopReference();
    talonFX.getDifferentialClosedLoopReferenceSlope();
    talonFX.getDifferentialClosedLoopError();
    talonFX.getDifferentialClosedLoopOutput();
    talonFX.getDifferentialClosedLoopProportionalOutput();
    talonFX.getDifferentialClosedLoopIntegratedOutput();
    talonFX.getDifferentialClosedLoopDerivativeOutput();
    talonFX.getDifferentialClosedLoopFeedForward();

    talonFX.getFaultField();
    talonFX.getFault_BootDuringEnable();
    talonFX.getFault_BridgeBrownout();
    talonFX.getFault_DeviceTemp();
    talonFX.getFault_ForwardHardLimit();
    talonFX.getFault_ForwardSoftLimit();
    talonFX.getFault_FusedSensorOutOfSync();
    talonFX.getFault_Hardware();
    talonFX.getFault_MissingDifferentialFX();
    talonFX.getFault_MissingHardLimitRemote();
    talonFX.getFault_MissingSoftLimitRemote();
    talonFX.getFault_OverSupplyV();
    talonFX.getFault_ProcTemp();
    talonFX.getFault_RemoteSensorDataInvalid();
    talonFX.getFault_RemoteSensorPosOverflow();
    talonFX.getFault_RemoteSensorReset();
    talonFX.getFault_ReverseHardLimit();
    talonFX.getFault_ReverseSoftLimit();
    talonFX.getFault_StaticBrakeDisabled();
    talonFX.getFault_StatorCurrLimit();
    talonFX.getFault_SupplyCurrLimit();
    talonFX.getFault_Undervoltage();
    talonFX.getFault_UnlicensedFeatureInUse();
    talonFX.getFault_UnstableSupplyV();
    talonFX.getFault_UsingFusedCANcoderWhileUnlicensed();

    talonFX.getStickyFaultField();
    talonFX.getStickyFault_BootDuringEnable();
    talonFX.getStickyFault_BridgeBrownout();
    talonFX.getStickyFault_DeviceTemp();
    talonFX.getStickyFault_ForwardHardLimit();
    talonFX.getStickyFault_ForwardSoftLimit();
    talonFX.getStickyFault_FusedSensorOutOfSync();
    talonFX.getStickyFault_Hardware();
    talonFX.getStickyFault_MissingDifferentialFX();
    talonFX.getStickyFault_MissingHardLimitRemote();
    talonFX.getStickyFault_MissingSoftLimitRemote();
    talonFX.getStickyFault_OverSupplyV();
    talonFX.getStickyFault_ProcTemp();
    talonFX.getStickyFault_RemoteSensorDataInvalid();
    talonFX.getStickyFault_RemoteSensorPosOverflow();
    talonFX.getStickyFault_RemoteSensorReset();
    talonFX.getStickyFault_ReverseHardLimit();
    talonFX.getStickyFault_ReverseSoftLimit();
    talonFX.getStickyFault_StaticBrakeDisabled();
    talonFX.getStickyFault_StatorCurrLimit();
    talonFX.getStickyFault_SupplyCurrLimit();
    talonFX.getStickyFault_Undervoltage();
    talonFX.getStickyFault_UnlicensedFeatureInUse();
    talonFX.getStickyFault_UnstableSupplyV();
    talonFX.getStickyFault_UsingFusedCANcoderWhileUnlicensed();

    talonFX.getAppliedControl();
    talonFX.get();
    talonFX.getDescription();
    talonFX.getDeviceID();
    talonFX.getExpiration();
    talonFX.getNetwork();
    talonFX.getSimState();
    talonFX.hasResetOccurred();
    talonFX.getResetOccurredChecker();
    talonFX.isAlive();
    talonFX.isConnected();
    talonFX.isSafetyEnabled();
  }

  // TalonFXS

  NeutralOut /*                                 */ dv; // Neutral Output
  CoastOut /*                                   */ dw;
  StaticBrake /*                                */ dx;
  Follower /*                                   */ dy; // Followers
  StrictFollower /*                             */ dz;
  DifferentialFollower /*                       */ ea;
  DifferentialStrictFollower /*                 */ eb;
  DutyCycleOut /*                               */ ec; // DutyCycle
  PositionDutyCycle /*                          */ ed;
  VelocityDutyCycle /*                          */ ee;
  MotionMagicDutyCycle /*                       */ ef;
  MotionMagicExpoDutyCycle /*                   */ eg;
  MotionMagicVelocityDutyCycle /*               */ eh;
  DynamicMotionMagicDutyCycle /*                */ ei;
  DynamicMotionMagicExpoDutyCycle /*            */ ej;
  Diff_DutyCycleOut_Open /*                     */ ek; // DutyCycle with DutyCycleOut differential
  Diff_PositionDutyCycle_Open /*                */ el;
  Diff_VelocityDutyCycle_Open /*                */ em;
  Diff_MotionMagicDutyCycle_Open /*             */ en;
  Diff_MotionMagicExpoDutyCycle_Open /*         */ eo;
  Diff_MotionMagicVelocityDutyCycle_Open /*     */ ep;
  Diff_DutyCycleOut_Position /*                 */
      eq; // DutyCycle with PositionDutyCycle differential
  Diff_PositionDutyCycle_Position /*            */ er;
  Diff_VelocityDutyCycle_Position /*            */ es;
  Diff_MotionMagicDutyCycle_Position /*         */ et;
  Diff_MotionMagicExpoDutyCycle_Position /*     */ eu;
  Diff_MotionMagicVelocityDutyCycle_Position /* */ ev;
  Diff_DutyCycleOut_Velocity /*                 */
      ew; // DutyCycle with VelocityDutyCycle differential
  Diff_PositionDutyCycle_Velocity /*            */ ex;
  Diff_VelocityDutyCycle_Velocity /*            */ ey;
  Diff_MotionMagicDutyCycle_Velocity /*         */ ez;
  Diff_MotionMagicExpoDutyCycle_Velocity /*     */ fa;
  Diff_MotionMagicVelocityDutyCycle_Velocity /* */ fb;
  VoltageOut /*                                 */ fc; // Voltage
  PositionVoltage /*                            */ fd;
  VelocityVoltage /*                            */ fe;
  MotionMagicVoltage /*                         */ ff;
  MotionMagicExpoVoltage /*                     */ fg;
  MotionMagicVelocityVoltage /*                 */ fh;
  DynamicMotionMagicVoltage /*                  */ fi;
  DynamicMotionMagicExpoVoltage /*              */ fj;
  Diff_VoltageOut_Open /*                       */ fk; // Voltage with VoltageOut differential
  Diff_PositionVoltage_Open /*                  */ fl;
  Diff_VelocityVoltage_Open /*                  */ fm;
  Diff_MotionMagicVoltage_Open /*               */ fn;
  Diff_MotionMagicExpoVoltage_Open /*           */ fo;
  Diff_MotionMagicVelocityVoltage_Open /*       */ fp;
  Diff_VoltageOut_Position /*                   */ fq; // Voltage with PositionVoltage differential
  Diff_PositionVoltage_Position /*              */ fr;
  Diff_VelocityVoltage_Position /*              */ fs;
  Diff_MotionMagicVoltage_Position /*           */ ft;
  Diff_MotionMagicExpoVoltage_Position /*       */ fu;
  Diff_MotionMagicVelocityVoltage_Position /*   */ fv;
  Diff_VoltageOut_Velocity /*                   */ fw; // Voltage with VelocityVoltage differential
  Diff_PositionVoltage_Velocity /*              */ fx;
  Diff_VelocityVoltage_Velocity /*              */ fy;
  Diff_MotionMagicVoltage_Velocity /*           */ fz;
  Diff_MotionMagicExpoVoltage_Velocity /*       */ ga;
  Diff_MotionMagicVelocityVoltage_Velocity /*   */ gb;
  DifferentialDutyCycle /*                      */ gc; // duplicates with limited functionality
  DifferentialVoltage /*                        */ gd;
  DifferentialPositionDutyCycle /*              */ ge;
  DifferentialPositionVoltage /*                */ gf;
  DifferentialVelocityDutyCycle /*              */ gg;
  DifferentialVelocityVoltage /*                */ gh;
  DifferentialMotionMagicDutyCycle /*           */ gi;
  DifferentialMotionMagicVoltage /*             */ gj;
  DifferentialMotionMagicVelocityDutyCycle /*   */ gk;
  DifferentialMotionMagicVelocityVoltage /*     */ gl;
  DifferentialMotionMagicExpoDutyCycle /*       */ gm;
  DifferentialMotionMagicExpoVoltage /*         */ gn;

  public void talonFXS(TalonFXS talonFXS) {
    // 130 StatusSignal getters
    // 9 pos + vel
    // 6 volt + amp
    // 6 control mode
    // 12 talon changing data
    // 4 basic motor data
    // 6 talon basic data
    // 9 closed loop
    // 5 diff mech
    // 9 diff closed loop
    // 32 faults
    // 32 sticky faults
    //
    // 12 misc non-StatusSignal getters

    talonFXS.getPosition();
    talonFXS.getVelocity();
    talonFXS.getAcceleration();
    talonFXS.getRotorPosition();
    talonFXS.getRotorVelocity();
    talonFXS.getRawPulseWidthPosition();
    talonFXS.getRawPulseWidthVelocity();
    talonFXS.getRawQuadraturePosition();
    talonFXS.getRawQuadratureVelocity();

    talonFXS.getDutyCycle();
    talonFXS.getMotorVoltage();
    talonFXS.getStatorCurrent();
    talonFXS.getTorqueCurrent();
    talonFXS.getSupplyVoltage();
    talonFXS.getSupplyCurrent();

    talonFXS.getControlMode();
    talonFXS.getDifferentialControlMode();
    talonFXS.getMotorOutputStatus();
    talonFXS.getBridgeOutput();
    talonFXS.getMotionMagicIsRunning();
    talonFXS.getAppliedRotorPolarity();

    talonFXS.getDeviceEnable();
    talonFXS.getRobotEnable();
    talonFXS.getMotionMagicAtTarget();
    talonFXS.getForwardLimit();
    talonFXS.getReverseLimit();
    talonFXS.getProcessorTemp();
    talonFXS.getDeviceTemp();
    talonFXS.getAncillaryDeviceTemp();
    talonFXS.getExternalMotorTemp();
    talonFXS.getExternalMotorTempStatus();
    talonFXS.getAnalogVoltage();
    talonFXS.getFiveVRailVoltage();

    talonFXS.getConnectedMotor();
    talonFXS.getMotorKT();
    talonFXS.getMotorKV();
    talonFXS.getMotorStallCurrent();

    talonFXS.getIsProLicensed();
    talonFXS.getVersion();
    talonFXS.getVersionMajor();
    talonFXS.getVersionMinor();
    talonFXS.getVersionBugfix();
    talonFXS.getVersionBuild();

    talonFXS.getClosedLoopSlot();
    talonFXS.getClosedLoopReference();
    talonFXS.getClosedLoopReferenceSlope();
    talonFXS.getClosedLoopError();
    talonFXS.getClosedLoopOutput();
    talonFXS.getClosedLoopProportionalOutput();
    talonFXS.getClosedLoopIntegratedOutput();
    talonFXS.getClosedLoopDerivativeOutput();
    talonFXS.getClosedLoopFeedForward();

    talonFXS.getDifferentialAveragePosition();
    talonFXS.getDifferentialAverageVelocity();
    talonFXS.getDifferentialDifferencePosition();
    talonFXS.getDifferentialDifferenceVelocity();
    talonFXS.getDifferentialOutput();

    talonFXS.getDifferentialClosedLoopSlot();
    talonFXS.getDifferentialClosedLoopReference();
    talonFXS.getDifferentialClosedLoopReferenceSlope();
    talonFXS.getDifferentialClosedLoopError();
    talonFXS.getDifferentialClosedLoopOutput();
    talonFXS.getDifferentialClosedLoopProportionalOutput();
    talonFXS.getDifferentialClosedLoopIntegratedOutput();
    talonFXS.getDifferentialClosedLoopDerivativeOutput();
    talonFXS.getDifferentialClosedLoopFeedForward();

    talonFXS.getFaultField();
    talonFXS.getFault_5V();
    talonFXS.getFault_BootDuringEnable();
    talonFXS.getFault_BridgeBrownout();
    talonFXS.getFault_BridgeShort();
    talonFXS.getFault_DeviceTemp();
    talonFXS.getFault_DriveDisabledHallSensor();
    talonFXS.getFault_ForwardHardLimit();
    talonFXS.getFault_ForwardSoftLimit();
    talonFXS.getFault_FusedSensorOutOfSync();
    talonFXS.getFault_Hardware();
    talonFXS.getFault_HallSensorMissing();
    talonFXS.getFault_MissingDifferentialFX();
    talonFXS.getFault_MissingHardLimitRemote();
    talonFXS.getFault_MissingSoftLimitRemote();
    talonFXS.getFault_MotorArrangementNotSelected();
    talonFXS.getFault_MotorTempSensorMissing();
    talonFXS.getFault_MotorTempSensorTooHot();
    talonFXS.getFault_OverSupplyV();
    talonFXS.getFault_ProcTemp();
    talonFXS.getFault_RemoteSensorDataInvalid();
    talonFXS.getFault_RemoteSensorPosOverflow();
    talonFXS.getFault_RemoteSensorReset();
    talonFXS.getFault_ReverseHardLimit();
    talonFXS.getFault_ReverseSoftLimit();
    talonFXS.getFault_StaticBrakeDisabled();
    talonFXS.getFault_StatorCurrLimit();
    talonFXS.getFault_SupplyCurrLimit();
    talonFXS.getFault_Undervoltage();
    talonFXS.getFault_UnlicensedFeatureInUse();
    talonFXS.getFault_UnstableSupplyV();
    talonFXS.getFault_UsingFusedCANcoderWhileUnlicensed();

    talonFXS.getStickyFaultField();
    talonFXS.getStickyFault_5V();
    talonFXS.getStickyFault_BootDuringEnable();
    talonFXS.getStickyFault_BridgeBrownout();
    talonFXS.getStickyFault_BridgeShort();
    talonFXS.getStickyFault_DeviceTemp();
    talonFXS.getStickyFault_DriveDisabledHallSensor();
    talonFXS.getStickyFault_ForwardHardLimit();
    talonFXS.getStickyFault_ForwardSoftLimit();
    talonFXS.getStickyFault_FusedSensorOutOfSync();
    talonFXS.getStickyFault_Hardware();
    talonFXS.getStickyFault_HallSensorMissing();
    talonFXS.getStickyFault_MissingDifferentialFX();
    talonFXS.getStickyFault_MissingHardLimitRemote();
    talonFXS.getStickyFault_MissingSoftLimitRemote();
    talonFXS.getStickyFault_MotorArrangementNotSelected();
    talonFXS.getStickyFault_MotorTempSensorMissing();
    talonFXS.getStickyFault_MotorTempSensorTooHot();
    talonFXS.getStickyFault_OverSupplyV();
    talonFXS.getStickyFault_ProcTemp();
    talonFXS.getStickyFault_RemoteSensorDataInvalid();
    talonFXS.getStickyFault_RemoteSensorPosOverflow();
    talonFXS.getStickyFault_RemoteSensorReset();
    talonFXS.getStickyFault_ReverseHardLimit();
    talonFXS.getStickyFault_ReverseSoftLimit();
    talonFXS.getStickyFault_StaticBrakeDisabled();
    talonFXS.getStickyFault_StatorCurrLimit();
    talonFXS.getStickyFault_SupplyCurrLimit();
    talonFXS.getStickyFault_Undervoltage();
    talonFXS.getStickyFault_UnlicensedFeatureInUse();
    talonFXS.getStickyFault_UnstableSupplyV();
    talonFXS.getStickyFault_UsingFusedCANcoderWhileUnlicensed();

    talonFXS.getAppliedControl();
    talonFXS.get();
    talonFXS.getDescription();
    talonFXS.getDeviceID();
    talonFXS.getExpiration();
    talonFXS.getNetwork();
    talonFXS.getSimState();
    talonFXS.hasResetOccurred();
    talonFXS.getResetOccurredChecker();
    talonFXS.isAlive();
    talonFXS.isConnected();
    talonFXS.isSafetyEnabled();
  }

  // CANcoder

  public void cancoder(CANcoder cancoder) {
    // 25 StatusSignal getters
    // 5 pos + vel
    // 2 changing data
    // 6 basic data
    // 6 faults
    // 6 sticky faults
    //
    // 7 misc non-StatusSignal getters

    cancoder.getAbsolutePosition();
    cancoder.getPosition();
    cancoder.getPositionSinceBoot();
    cancoder.getVelocity();
    cancoder.getUnfilteredVelocity();

    cancoder.getSupplyVoltage();
    cancoder.getMagnetHealth();

    cancoder.getIsProLicensed();
    cancoder.getVersion();
    cancoder.getVersionMajor();
    cancoder.getVersionMinor();
    cancoder.getVersionBugfix();
    cancoder.getVersionBuild();

    cancoder.getFaultField();
    cancoder.getFault_BadMagnet();
    cancoder.getFault_BootDuringEnable();
    cancoder.getFault_Hardware();
    cancoder.getFault_Undervoltage();
    cancoder.getFault_UnlicensedFeatureInUse();

    cancoder.getStickyFaultField();
    cancoder.getStickyFault_BadMagnet();
    cancoder.getStickyFault_BootDuringEnable();
    cancoder.getStickyFault_Hardware();
    cancoder.getStickyFault_Undervoltage();
    cancoder.getStickyFault_UnlicensedFeatureInUse();

    cancoder.getAppliedControl();
    cancoder.getDeviceID();
    cancoder.getNetwork();
    cancoder.getResetOccurredChecker();
    cancoder.hasResetOccurred();
    cancoder.getSimState();
    cancoder.isConnected();
  }

  // CANdi

  public void candi(CANdi candi) {
    // 33 StatusSignal getters
    // 2 current + voltage
    // 6 PWM
    // 6 signal input
    // 1 state
    // 6 basic info
    // 6 faults
    // 6 sticky faults
    //
    // 7 misc non-StatusSignal getters

    candi.getOutputCurrent();
    candi.getSupplyVoltage();

    candi.getPWM1Position();
    candi.getPWM1RiseToRise();
    candi.getPWM1Velocity();
    candi.getPWM2Position();
    candi.getPWM2RiseToRise();
    candi.getPWM2Velocity();

    candi.getS1Closed();
    candi.getS1State();
    candi.getS2Closed();
    candi.getS2State();
    candi.getQuadraturePosition();
    candi.getQuadratureVelocity();

    candi.getOvercurrent();

    candi.getIsProLicensed();
    candi.getVersion();
    candi.getVersionMajor();
    candi.getVersionMinor();
    candi.getVersionBugfix();
    candi.getVersionBuild();

    candi.getFaultField();
    candi.getFault_5V();
    candi.getFault_BootDuringEnable();
    candi.getFault_Hardware();
    candi.getFault_Undervoltage();
    candi.getFault_UnlicensedFeatureInUse();

    candi.getStickyFaultField();
    candi.getStickyFault_5V();
    candi.getStickyFault_BootDuringEnable();
    candi.getStickyFault_Hardware();
    candi.getStickyFault_Undervoltage();
    candi.getStickyFault_UnlicensedFeatureInUse();

    candi.getAppliedControl();
    candi.getDeviceID();
    candi.getNetwork();
    candi.getResetOccurredChecker();
    candi.getSimState();
    candi.hasResetOccurred();
    candi.isConnected();
  }

  // CANdle

  ModulateVBatOut /*     */ go;
  SolidColor /*          */ gp;
  EmptyAnimation /*      */ gq;
  ColorFlowAnimation /*  */ gr;
  FireAnimation /*       */ gs;
  LarsonAnimation /*     */ gt;
  RainbowAnimation /*    */ gu;
  RgbFadeAnimation /*    */ gv;
  SingleFadeAnimation /* */ gw;
  StrobeAnimation /*     */ gx;
  TwinkleAnimation /*    */ gy;
  TwinkleOffAnimation /* */ gz;
  EmptyControl /*        */ ha;

  public void candle(CANdle candle) {
    // 34 StatusSignal getters
    // 5 current status
    // 7 basic info
    // 11 faults
    // 11 sticky faults
    //
    // 7 misc non-StatusSignal getters

    candle.getFiveVRailVoltage();
    candle.getSupplyVoltage();
    candle.getOutputCurrent();
    candle.getDeviceTemp();
    candle.getVBatModulation();

    candle.getMaxSimultaneousAnimationCount();
    candle.getIsProLicensed();
    candle.getVersion();
    candle.getVersionMajor();
    candle.getVersionMinor();
    candle.getVersionBugfix();
    candle.getVersionBuild();

    candle.getFaultField();
    candle.getFault_5VTooHigh();
    candle.getFault_5VTooLow();
    candle.getFault_BootDuringEnable();
    candle.getFault_Hardware();
    candle.getFault_Overvoltage();
    candle.getFault_ShortCircuit();
    candle.getFault_SoftwareFuse();
    candle.getFault_Thermal();
    candle.getFault_Undervoltage();
    candle.getFault_UnlicensedFeatureInUse();

    candle.getStickyFaultField();
    candle.getStickyFault_5VTooHigh();
    candle.getStickyFault_5VTooLow();
    candle.getStickyFault_BootDuringEnable();
    candle.getStickyFault_Hardware();
    candle.getStickyFault_Overvoltage();
    candle.getStickyFault_ShortCircuit();
    candle.getStickyFault_SoftwareFuse();
    candle.getStickyFault_Thermal();
    candle.getStickyFault_Undervoltage();
    candle.getStickyFault_UnlicensedFeatureInUse();

    candle.getAppliedControl();
    candle.getDeviceID();
    candle.getNetwork();
    candle.getResetOccurredChecker();
    candle.getSimState();
    candle.hasResetOccurred();
    candle.isConnected();
  }

  // CANrange

  public void canrange(CANrange canrange) {
    // 28 StatusSignal getters
    // 5 measurement
    // 7 current status
    // 6 basic info
    // 5 faults
    // 5 sticky faults
    //
    // 7 misc non-StatusSignal getters

    canrange.getDistance();
    canrange.getDistanceStdDev();
    canrange.getMeasurementTime();
    canrange.getSignalStrength();
    canrange.getIsDetected();

    canrange.getSupplyVoltage();
    canrange.getAmbientSignal();
    canrange.getMeasurementHealth();
    canrange.getRealFOVCenterX();
    canrange.getRealFOVCenterY();
    canrange.getRealFOVRangeX();
    canrange.getRealFOVRangeY();

    canrange.getIsProLicensed();
    canrange.getVersion();
    canrange.getVersionMajor();
    canrange.getVersionMinor();
    canrange.getVersionBugfix();
    canrange.getVersionBuild();

    canrange.getFaultField();
    canrange.getFault_BootDuringEnable();
    canrange.getFault_Hardware();
    canrange.getFault_Undervoltage();
    canrange.getFault_UnlicensedFeatureInUse();

    canrange.getStickyFaultField();
    canrange.getStickyFault_BootDuringEnable();
    canrange.getStickyFault_Hardware();
    canrange.getStickyFault_Undervoltage();
    canrange.getStickyFault_UnlicensedFeatureInUse();

    canrange.getAppliedControl();
    canrange.getDeviceID();
    canrange.getNetwork();
    canrange.getResetOccurredChecker();
    canrange.getSimState();
    canrange.hasResetOccurred();
    canrange.isConnected();
  }

  // Pigeon2

  public void pigeon2(Pigeon2 pigeon2) {
    // 68 StatusSignal getters
    // 10 orientation
    // 6 angular vel
    // 3 acc
    // 3 gravity
    // 6 mag field
    // 6 changing data
    // 6 basic data
    // 14 faults
    // 14 sticky faults
    //
    // 9 misc non-StatusSignal getters

    pigeon2.getRoll();
    pigeon2.getPitch();
    pigeon2.getYaw();
    pigeon2.getQuatW();
    pigeon2.getQuatX();
    pigeon2.getQuatY();
    pigeon2.getQuatZ();
    pigeon2.getAccumGyroX();
    pigeon2.getAccumGyroY();
    pigeon2.getAccumGyroZ();

    pigeon2.getAngularVelocityXDevice();
    pigeon2.getAngularVelocityYDevice();
    pigeon2.getAngularVelocityZDevice();
    pigeon2.getAngularVelocityYWorld();
    pigeon2.getAngularVelocityXWorld();
    pigeon2.getAngularVelocityZWorld();

    pigeon2.getAccelerationX();
    pigeon2.getAccelerationY();
    pigeon2.getAccelerationZ();

    pigeon2.getGravityVectorX();
    pigeon2.getGravityVectorY();
    pigeon2.getGravityVectorZ();

    pigeon2.getMagneticFieldX();
    pigeon2.getMagneticFieldY();
    pigeon2.getMagneticFieldZ();
    pigeon2.getRawMagneticFieldX();
    pigeon2.getRawMagneticFieldY();
    pigeon2.getRawMagneticFieldZ();

    pigeon2.getSupplyVoltage();
    pigeon2.getTemperature();
    pigeon2.getTemperatureCompensationDisabled();
    pigeon2.getNoMotionEnabled();
    pigeon2.getNoMotionCount();
    pigeon2.getUpTime();

    pigeon2.getIsProLicensed();
    pigeon2.getVersion();
    pigeon2.getVersionMajor();
    pigeon2.getVersionMinor();
    pigeon2.getVersionBugfix();
    pigeon2.getVersionBuild();

    pigeon2.getFaultField();
    pigeon2.getFault_BootDuringEnable();
    pigeon2.getFault_BootIntoMotion();
    pigeon2.getFault_BootupAccelerometer();
    pigeon2.getFault_BootupGyroscope();
    pigeon2.getFault_BootupMagnetometer();
    pigeon2.getFault_DataAcquiredLate();
    pigeon2.getFault_Hardware();
    pigeon2.getFault_LoopTimeSlow();
    pigeon2.getFault_SaturatedAccelerometer();
    pigeon2.getFault_SaturatedGyroscope();
    pigeon2.getFault_SaturatedMagnetometer();
    pigeon2.getFault_Undervoltage();
    pigeon2.getFault_UnlicensedFeatureInUse();

    pigeon2.getStickyFaultField();
    pigeon2.getStickyFault_BootDuringEnable();
    pigeon2.getStickyFault_BootIntoMotion();
    pigeon2.getStickyFault_BootupAccelerometer();
    pigeon2.getStickyFault_BootupGyroscope();
    pigeon2.getStickyFault_BootupMagnetometer();
    pigeon2.getStickyFault_DataAcquiredLate();
    pigeon2.getStickyFault_Hardware();
    pigeon2.getStickyFault_LoopTimeSlow();
    pigeon2.getStickyFault_SaturatedAccelerometer();
    pigeon2.getStickyFault_SaturatedGyroscope();
    pigeon2.getStickyFault_SaturatedMagnetometer();
    pigeon2.getStickyFault_Undervoltage();
    pigeon2.getStickyFault_UnlicensedFeatureInUse();

    pigeon2.getAppliedControl();
    pigeon2.getDeviceID();
    pigeon2.getNetwork();
    pigeon2.getRotation2d();
    pigeon2.getRotation3d();
    pigeon2.getSimState();
    pigeon2.hasResetOccurred();
    pigeon2.getResetOccurredChecker();
    pigeon2.isConnected();
  }

  // DifferentialMechanism

  public void differentialMechanism(DifferentialMechanism<?> diffMech) {
    // 10 StatusSignal getters
    // 4 pos + vel
    // 6 closed loop
    //
    // 7 misc non-StatusSignal getters

    diffMech.getAveragePosition();
    diffMech.getAverageVelocity();
    diffMech.getDifferentialPosition();
    diffMech.getDifferentialVelocity();

    diffMech.getAverageClosedLoopReference();
    diffMech.getAverageClosedLoopReferenceSlope();
    diffMech.getAverageClosedLoopError();
    diffMech.getDifferentialClosedLoopReference();
    diffMech.getDifferentialClosedLoopReferenceSlope();
    diffMech.getDifferentialClosedLoopError();

    diffMech.getDisabledReason();
    diffMech.getFollower();
    diffMech.getLeader();
    diffMech.getMechanismState();
    diffMech.getRequiresUserReason();
    diffMech.isDisabled();
    diffMech.requiresUserAction();
  }
}
