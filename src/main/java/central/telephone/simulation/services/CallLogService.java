package central.telephone.simulation.services;

import central.telephone.simulation.entities.CallLog;
import central.telephone.simulation.entities.TelephoneLine;
import central.telephone.simulation.models.InputMessage;
import central.telephone.simulation.models.OutputMessage;
import central.telephone.simulation.repositories.CallLogRepository;
import central.telephone.simulation.repositories.TelephoneLineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("callLogService")
public class CallLogService {
  @Autowired
  @Qualifier("callLogRepository")
  private CallLogRepository callLogRepository;

  @Autowired
  @Qualifier("telephoneLineRepository")
  private TelephoneLineRepository telephoneLineRepository;

  public CallLog findOne(Long callLogId){
    return callLogRepository.getById(callLogId);
  }

  public List<CallLog> findAll(){
    return callLogRepository.findAll();
  }

  public OutputMessage saveOrUpdate(InputMessage inputMessage){

    System.out.println("Dentro de save or Update");
    if(inputMessage.getCallLogId() == null){
      Optional<TelephoneLine> senderLine = telephoneLineRepository.findById(inputMessage.getFrom());
      Optional<TelephoneLine> receiverLine = telephoneLineRepository.findById(inputMessage.getTo());
      String state = inputMessage.getState();
      String duration = inputMessage.getDuration();

      if(senderLine.isPresent() && receiverLine.isPresent()){
        CallLog callLog = new CallLog(senderLine.get(), receiverLine.get(), state);
        CallLog savedCallLog = callLogRepository.save(callLog);

        return new OutputMessage(savedCallLog, duration);
      }

    }else{

      Optional<CallLog> optionalCallLog = callLogRepository.findById(inputMessage.getCallLogId());

      if(optionalCallLog.isPresent()){
        CallLog callLog =  optionalCallLog.get();
        String state = inputMessage.getState();
        String duration = inputMessage.getDuration();

        switch (state) {
          case "Recibida":
          case "Solicitada":
          case "Cancelada":
          case "Rechazada":
          case "Inalcanzable":
            callLog.setState(state);
            break;
          case "Finalizada":
            callLog.setState(state);
            callLog.setDuration(duration);
            break;
          default:
            inputMessage.setState("No definido");
            return new OutputMessage(inputMessage, duration);
        }

        CallLog updatedCallLog = callLogRepository.save(callLog);

        return new OutputMessage(updatedCallLog, duration);
      }

    }
    inputMessage.setState("No definido");
    return new OutputMessage(inputMessage, "00:00");

  }
}
