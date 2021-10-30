package central.telephone.simulation.repositories;

import central.telephone.simulation.entities.CallLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("callLogRepository")
public interface CallLogRepository extends JpaRepository<CallLog,Long> {
}
