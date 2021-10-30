package central.telephone.simulation.repositories;

import central.telephone.simulation.entities.CentralTelephone;
import central.telephone.simulation.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("centralTelephoneRepository")
public interface CentralTelephoneRepository extends JpaRepository<CentralTelephone, Long> {
  CentralTelephone findByName(String name);
}
