package central.telephone.simulation.repositories;

import central.telephone.simulation.entities.CentralTelephone;
import central.telephone.simulation.entities.TelephoneLine;
import central.telephone.simulation.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("telephoneLineRepository")
public interface TelephoneLineRepository extends JpaRepository<TelephoneLine,Long> {
  TelephoneLine findByUser(UserEntity user);

  List<TelephoneLine> findByCentralTelephone(CentralTelephone centralTelephone);
}
