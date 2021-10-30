package central.telephone.simulation.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "centrals_telephones")
public class CentralTelephone  extends AuditModel {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name", length = 150, nullable = false)
  private String name;

  @Column(name = "wait_time", length = 100, nullable = true)
  private Integer waitTimeSeconds;

  @OneToMany(mappedBy = "centralTelephone",fetch = FetchType.LAZY)
  private Set<TelephoneLine> telephoneLines;

  public CentralTelephone() {}

  public CentralTelephone(String name, Integer waitTimeSeconds) {
    this.name = name;
    this.waitTimeSeconds = waitTimeSeconds;
  }

  public CentralTelephone(String name, Integer waitTimeSeconds, Set<TelephoneLine> telephoneLines) {
    this.name = name;
    this.waitTimeSeconds = waitTimeSeconds;
    this.telephoneLines = telephoneLines;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getWaitTimeSeconds() {
    return waitTimeSeconds;
  }

  public void setWaitTimeSeconds(Integer waitTimeSeconds) {
    this.waitTimeSeconds = waitTimeSeconds;
  }

  public Set<TelephoneLine> getTelephoneLines() {
    return telephoneLines;
  }

  public void setTelephoneLines(Set<TelephoneLine> telephoneLines) {
    this.telephoneLines = telephoneLines;
  }

  @Override
  public String toString() {
    return "CentralTelephone{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", waitTimeSeconds=" + waitTimeSeconds +
        ", telephoneLines=" + telephoneLines +
        '}';
  }
}
