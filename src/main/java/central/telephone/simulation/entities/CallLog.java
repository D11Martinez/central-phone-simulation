package central.telephone.simulation.entities;

import javax.persistence.*;

@Entity
@Table(name = "calls_logs")
public class CallLog extends AuditModel {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.EAGER)
  private TelephoneLine senderLine;

  @ManyToOne(fetch = FetchType.EAGER)
  private TelephoneLine receiverLine;

  @Column(name = "state", length = 20, nullable = false)
  private String state = "Solicitada";

  @Column(name = "duration", nullable = true)
  private String duration = "00:00";

  public CallLog(){}

  public CallLog(TelephoneLine senderLine, TelephoneLine receiverLine, String state) {
    this.senderLine = senderLine;
    this.receiverLine = receiverLine;
    this.state = state;
  }

  public Long getId() {
    return id;
  }

  public TelephoneLine getSenderLine() {
    return senderLine;
  }

  public void setSenderLine(TelephoneLine senderLine) {
    this.senderLine = senderLine;
  }

  public TelephoneLine getReceiverLine() {
    return receiverLine;
  }

  public void setReceiverLine(TelephoneLine receiverLine) {
    this.receiverLine = receiverLine;
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
    return "CallLog{" +
        "id=" + id +
        ", senderLine=" + senderLine +
        ", receiverLine=" + receiverLine +
        ", state='" + state + '\'' +
        ", duration=" + duration +
        '}';
  }
}
