package central.telephone.simulation.entities;

import java.util.Set;

import javax.persistence.*;


@Entity
@Table(name = "roles")
public class Role extends AuditModel {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", unique = true, length = 45, nullable = false)
    private String name;

    @Column(name = "details", length = 100, nullable = true)
    private String details;

    @OneToMany(mappedBy = "role",fetch = FetchType.LAZY)
    private Set<UserEntity> users;

    public Role() {
        super();
    }

    public Role(String name, String details) {
        super();
        this.name = name;
        this.details = details;
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

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Set<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(Set<UserEntity> users) {
        this.users = users;
    }

    @PreRemove
    public void setNullUsersRole() {
        users.forEach(user -> user.setRole(null));
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", details='" + details + '\'' +
                '}';
    }
}