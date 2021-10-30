package central.telephone.simulation.services;

import central.telephone.simulation.entities.CentralTelephone;
import central.telephone.simulation.repositories.CentralTelephoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("centralTelephoneService")
public class CentralTelephoneService {
  @Autowired
  @Qualifier("centralTelephoneRepository")
  private CentralTelephoneRepository centralTelephoneRepository;

  public CentralTelephone createCentralTelephoneIfNotExists(String name, Integer waitTimeSeconds){
    CentralTelephone currentCentralTelephone = centralTelephoneRepository.findByName(name);

    if(currentCentralTelephone != null) return currentCentralTelephone;

    CentralTelephone centralTelephone = new CentralTelephone(name,waitTimeSeconds);
    return centralTelephoneRepository.save(centralTelephone);
  }

  public CentralTelephone findById(Long id) {
    Optional<CentralTelephone> centralTelephone = centralTelephoneRepository.findById(id);

    return centralTelephone.isPresent()? centralTelephone.get():null;
  }
}
