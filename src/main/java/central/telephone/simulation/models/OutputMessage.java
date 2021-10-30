package central.telephone.simulation.models;

import central.telephone.simulation.entities.CallLog;

public class OutputMessage {

  private Long callLogId;
  private Long from;
  private Long to;
  private String state;
  private String duration;

  public OutputMessage() {
  }

  public OutputMessage(InputMessage inputMessage, String duration){
    this.callLogId = inputMessage.getCallLogId();
    this.from = inputMessage.getFrom();
    this.to = inputMessage.getTo();
    this.state = inputMessage.getState();
    this.duration = duration;
  }

  public OutputMessage(CallLog callLog, String duration){
    this.callLogId = callLog.getId();
    this.from = callLog.getSenderLine().getId();
    this.to = callLog.getReceiverLine().getId();
    this.state = callLog.getState();
    this.duration = duration;
  }

  public OutputMessage(Long callLogId, Long from, Long to, String state, String duration) {
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
    return "OutputMessage{" +
        "callLogId=" + callLogId +
        ", from=" + from +
        ", to=" + to +
        ", state='" + state + '\'' +
        ", duration='" + duration + '\'' +
        '}';
  }
}
