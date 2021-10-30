package central.telephone.simulation.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;


@Entity
@Table(name = "users")
public class UserEntity extends AuditModel{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 60, unique=true)
    private String username;

    @Column(nullable = false, length = 80)
    private String password;

    @Column(nullable = true)
    private boolean enabled;

    @Column(name="last_access")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastAccess;

    @ManyToOne(fetch = FetchType.EAGER)
    private Role role;

    public UserEntity() {
        super();
    }

    public UserEntity(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.enabled = true;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Date getLastAccess() {
        return lastAccess;
    }

    public void setLastAccess(Date lastAccess) {
        this.lastAccess = lastAccess;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                ", lastAccess=" + lastAccess +
                ", role=" + role +
                '}';
    }
}