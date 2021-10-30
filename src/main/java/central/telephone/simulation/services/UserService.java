package central.telephone.simulation.services;

import central.telephone.simulation.entities.Role;
import central.telephone.simulation.entities.UserEntity;
import central.telephone.simulation.exceptions.NotFoundException;
import central.telephone.simulation.exceptions.InstanceAlreadyExistsException;
import central.telephone.simulation.repositories.RoleRepository;
import central.telephone.simulation.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserService {
  @Autowired
  @Qualifier("userRepository")
  private UserRepository userRepository;

  @Autowired
  @Qualifier("roleRepository")
  private RoleRepository roleRepository;

  @Autowired
  private BCryptPasswordEncoder encoder;

  public UserEntity createUser(String username, String password, String roleName){
    boolean usernameExists = userRepository.exists(username);

    if(usernameExists) throw new InstanceAlreadyExistsException("Username already exists.");

    String encodedPassword = encoder.encode(password);
    Role role = roleRepository.findByName(roleName);

    if(role == null) throw new NotFoundException("The Role does not exists");

    UserEntity newUser = new UserEntity(username,encodedPassword,role);

    return userRepository.save(newUser);
  }

  public UserEntity createUserIfNotExists(String username, String password, String roleName){
    UserEntity currentUser = userRepository.findByUsername(username);

    if(currentUser == null ){
      String encodedPassword = encoder.encode(password);
      Role role = roleRepository.findByName(roleName);

      if(role == null) throw new NotFoundException("The Role does not exists");

      UserEntity newUser = new UserEntity(username,encodedPassword,role);

      return userRepository.save(newUser);
    }

    return currentUser;
  }

  public UserEntity findByUsername(String username){
    return userRepository.findByUsername(username);
  }

}
