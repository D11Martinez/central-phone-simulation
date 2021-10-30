package central.telephone.simulation.entities;

import javax.persistence.*;

@Entity
@Table(name = "telephone_lines")
public class TelephoneLine  extends AuditModel {
  private static final long serialVersionUID = 1L;

  @Id
  private Long id;

  @Column(name = "enabled", nullable = false)
  private Boolean enabled = true;

  @ManyToOne(fetch = FetchType.EAGER)
  private UserEntity user = null;

  @ManyToOne(fetch = FetchType.EAGER)
  private CentralTelephone centralTelephone;

  public TelephoneLine(){}

  public TelephoneLine(Boolean enabled) {
    this.enabled = enabled;
  }

  public TelephoneLine(Long id, Boolean enabled, CentralTelephone centralTelephone) {
    this.id = id;
    this.enabled = enabled;
    this.centralTelephone = centralTelephone;
  }

  public TelephoneLine(Long id, Boolean enabled,UserEntity user, CentralTelephone centralTelephone) {
    this.id = id;
    this.enabled = enabled;
    this.user = user;
    this.centralTelephone = centralTelephone;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {this.id = id;}

  public Boolean getEnabled() {
    return enabled;
  }

  public void setEnabled(Boolean enabled) {
    this.enabled = enabled;
  }

  public UserEntity getUser() {
    return user;
  }

  public void setUser(UserEntity user) {
    this.user = user;
  }

  public CentralTelephone getCentralTelephone() {
    return centralTelephone;
  }

  public void setCentralTelephone(CentralTelephone centralTelephone) {
    this.centralTelephone = centralTelephone;
  }

  @Override
  public String toString() {
    return "TelephoneLine{" +
        "id=" + id +
        ", enabled=" + enabled +
        ", user=" + user +
        ", centralTelephone=" + centralTelephone.getName() +
        '}';
  }
}
