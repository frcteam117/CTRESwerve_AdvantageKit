package frc.robot.util.inputs;

import com.ctre.phoenix6.StatusCode;
import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.Timestamp;
import com.ctre.phoenix6.Timestamp.TimestampSource;
import com.ctre.phoenix6.controls.ControlRequest;
import edu.wpi.first.units.Measure;

public class InputsUtil {

  public record LoggedSignalDouble3d(
      LoggedSignalDouble x, LoggedSignalDouble y, LoggedSignalDouble z) {}

  public record LoggedSignalMeasure3d(
      LoggedSignalMeasure x, LoggedSignalMeasure y, LoggedSignalMeasure z) {}

  public record LoggedSignalQuaternion(
      LoggedSignalDouble w, LoggedSignalDouble x, LoggedSignalDouble y, LoggedSignalDouble z) {}

  public static record LoggedControlRequest(String name, String... controlInfo) {
    public LoggedControlRequest(ControlRequest controlRequest) {
      this(
          controlRequest.getName(),
          controlRequest.getControlInfo().entrySet().stream()
              .map(entry -> entry.getKey() + ":" + entry.getValue())
              .toArray(String[]::new));
    }
  }

  public static record LoggedSignalMeasure(
      String name,
      double value,
      String unit,
      StatusCode status,
      LoggedTimestamp deviceTimestamp,
      LoggedTimestamp canivoreTimestamp,
      LoggedTimestamp systemTimestamp) {
    public LoggedSignalMeasure(StatusSignal<? extends Measure<?>> signal) {
      this(
          signal.getName(),
          signal.getValueAsDouble(),
          signal.getUnits(),
          signal.getStatus(),
          new LoggedTimestamp(signal.getAllTimestamps().getDeviceTimestamp()),
          new LoggedTimestamp(signal.getAllTimestamps().getCANivoreTimestamp()),
          new LoggedTimestamp(signal.getAllTimestamps().getSystemTimestamp()));
    }
  }

  public static record LoggedSignalDouble(
      String name,
      double value,
      String unit,
      StatusCode status,
      LoggedTimestamp deviceTimestamp,
      LoggedTimestamp canivoreTimestamp,
      LoggedTimestamp systemTimestamp) {
    public LoggedSignalDouble(StatusSignal<? extends Double> signal) {
      this(
          signal.getName(),
          signal.getValueAsDouble(),
          signal.getUnits(),
          signal.getStatus(),
          new LoggedTimestamp(signal.getAllTimestamps().getDeviceTimestamp()),
          new LoggedTimestamp(signal.getAllTimestamps().getCANivoreTimestamp()),
          new LoggedTimestamp(signal.getAllTimestamps().getSystemTimestamp()));
    }
  }

  public static record LoggedSignalInteger(
      String name,
      int value,
      StatusCode status,
      LoggedTimestamp deviceTimestamp,
      LoggedTimestamp canivoreTimestamp,
      LoggedTimestamp systemTimestamp) {
    public LoggedSignalInteger(StatusSignal<? extends Integer> signal) {
      this(
          signal.getName(),
          signal.getValue(),
          signal.getStatus(),
          new LoggedTimestamp(signal.getAllTimestamps().getDeviceTimestamp()),
          new LoggedTimestamp(signal.getAllTimestamps().getCANivoreTimestamp()),
          new LoggedTimestamp(signal.getAllTimestamps().getSystemTimestamp()));
    }
  }

  public static record LoggedSignalBoolean(
      String name,
      boolean value,
      StatusCode status,
      LoggedTimestamp deviceTimestamp,
      LoggedTimestamp canivoreTimestamp,
      LoggedTimestamp systemTimestamp) {
    public LoggedSignalBoolean(StatusSignal<? extends Boolean> signal) {
      this(
          signal.getName(),
          signal.getValue(),
          signal.getStatus(),
          new LoggedTimestamp(signal.getAllTimestamps().getDeviceTimestamp()),
          new LoggedTimestamp(signal.getAllTimestamps().getCANivoreTimestamp()),
          new LoggedTimestamp(signal.getAllTimestamps().getSystemTimestamp()));
    }
  }

  public static record LoggedSignalEnum(
      String name,
      Enum<?> value,
      StatusCode status,
      LoggedTimestamp deviceTimestamp,
      LoggedTimestamp canivoreTimestamp,
      LoggedTimestamp systemTimestamp) {
    public LoggedSignalEnum(StatusSignal<? extends Enum<?>> signal) {
      this(
          signal.getName(),
          signal.getValue(),
          signal.getStatus(),
          new LoggedTimestamp(signal.getAllTimestamps().getDeviceTimestamp()),
          new LoggedTimestamp(signal.getAllTimestamps().getCANivoreTimestamp()),
          new LoggedTimestamp(signal.getAllTimestamps().getSystemTimestamp()));
    }
  }

  public static record LoggedTimestamp(
      TimestampSource source, double timestamp, double latency, boolean isValid) {
    public LoggedTimestamp(Timestamp timestamp) {
      this(timestamp.getSource(), timestamp.getTime(), timestamp.getLatency(), timestamp.isValid());
    }
  }
}
