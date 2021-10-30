package central.telephone.simulation.services;

import central.telephone.simulation.entities.Role;
import central.telephone.simulation.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("roleService")
public class RoleService {
  @Autowired
  @Qualifier("roleRepository")
  private RoleRepository roleRepository;

  public Role createRoleIfNotExists(String name, String description){
    Role currentRole = roleRepository.findByName(name);

    if(currentRole == null) {
      Role newRole = new Role(name,description);

      return roleRepository.save(newRole);
    }

    return currentRole;
  }
}
