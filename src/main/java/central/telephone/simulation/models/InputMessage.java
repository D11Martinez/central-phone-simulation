package central.telephone.simulation.models;

public class InputMessage {
  private Long callLogId;
  private Long from;
  private Long to;
  private String state;
  private String duration;

  public InputMessage() {
  }

  public InputMessage(Long callLogId, Long from, Long to, String state, String duration) {
    this.callLogId = callLogId;
    this.from = from;
    this.to = to;
    this.state = state;
    this.duration = duration;
  }

  public Long getCallLogId() {
    return callLogId;
  }

  public void setCallLogId(Long callLogId) {
    this.callLogId = callLogId;
  }

  public Long getFrom() {
    return from;
  }

  public void setFrom(Long from) {
    this.from = from;
  }

  public Long getTo() {
    return to;
  }

  public void setTo(Long to) {
    this.to = to;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getDuration() {
    return duration;
  }

  public void setDuration(String duration) {
    this.duration = duration;
  }

  @Override
  public String toString() {
    return "InputMessage{" +
        "callLogId=" + callLogId +
        ", from=" + from +
        ", to=" + to +
        ", state='" + state +
        ", duration='" + duration +
        '}';
  }
}
