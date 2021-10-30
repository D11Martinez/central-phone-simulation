package central.telephone.simulation.repositories;

import central.telephone.simulation.entities.CallLog;
import central.telephone.simulation.entities.TelephoneLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("callLogRepository")
public interface CallLogRepository extends JpaRepository<CallLog,Long> {
  List<CallLog> findBySenderLineOrReceiverLine(TelephoneLine senderLine, TelephoneLine receiverLine);
}
