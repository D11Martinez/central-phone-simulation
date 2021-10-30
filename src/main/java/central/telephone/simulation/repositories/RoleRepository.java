package central.telephone.simulation.repositories;

import central.telephone.simulation.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository("roleRepository")
public interface RoleRepository extends JpaRepository<Role, Long> {
  Role findByName(String name);

  @Query(value = "select count(r)>0 from public.roles as r where r.name = ?1", nativeQuery = true)
  public boolean exists(String name);
}
