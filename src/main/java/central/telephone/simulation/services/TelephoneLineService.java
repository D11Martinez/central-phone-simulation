package central.telephone.simulation.services;

import central.telephone.simulation.entities.TelephoneLine;
import central.telephone.simulation.entities.UserEntity;
import central.telephone.simulation.exceptions.InstanceAlreadyExistsException;
import central.telephone.simulation.repositories.TelephoneLineRepository;
import central.telephone.simulation.repositories.UserRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service("telephoneLineService")
public class TelephoneLineService {
  @Autowired
  @Qualifier("telephoneLineRepository")
  private TelephoneLineRepository telephoneLineRepository;

  @Autowired
  @Qualifier("userRepository")
  private UserRepository userRepository;

  public TelephoneLine createTelephoneLineIfNotExists(TelephoneLine telephoneLine) throws NotFoundException {
    if(telephoneLine.getId() == null) throw new NotFoundException("No se encontró el ID de la Linea");

    Optional<TelephoneLine> currentTelephoneLine = telephoneLineRepository.findById(telephoneLine.getId());

    if(currentTelephoneLine.isPresent()) throw new InstanceAlreadyExistsException("El Id de la linea ya está registrado.");

    return telephoneLineRepository.save(telephoneLine);
  }

  public List<TelephoneLine> findAgendaByUser(User userDetails) throws NotFoundException {
    Collection<GrantedAuthority> authorities = userDetails.getAuthorities();

    if(authorities.contains(new SimpleGrantedAuthority("ADMIN"))) {
      return telephoneLineRepository.findAll();
    }

    UserEntity userEntity = userRepository.findByUsername(userDetails.getUsername());

    if(userEntity == null ) throw new NotFoundException("El usuario no ha sido encontrado");

    TelephoneLine userLine = telephoneLineRepository.findByUser(userEntity);

    if(userLine == null) throw  new NotFoundException("El usuario no tiene una linea de teléfono asignada");

    return telephoneLineRepository.findByCentralTelephone(userLine.getCentralTelephone());
  }

  public TelephoneLine findTelephoneLineByUser(User userDetails) throws NotFoundException {
    UserEntity userEntity = userRepository.findByUsername(userDetails.getUsername());

    if(userEntity == null ) throw new NotFoundException("El usuario no ha sido encontrado");

    return telephoneLineRepository.findByUser(userEntity);
  }
}
