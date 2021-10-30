package central.telephone.simulation.services;

import central.telephone.simulation.entities.CallLog;
import central.telephone.simulation.entities.TelephoneLine;
import central.telephone.simulation.entities.UserEntity;
import central.telephone.simulation.models.InputMessage;
import central.telephone.simulation.models.OutputMessage;
import central.telephone.simulation.repositories.CallLogRepository;
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

@Service("callLogService")
public class CallLogService {
  @Autowired
  @Qualifier("callLogRepository")
  private CallLogRepository callLogRepository;

  @Autowired
  @Qualifier("telephoneLineRepository")
  private TelephoneLineRepository telephoneLineRepository;

  @Autowired
  @Qualifier("userRepository")
  private UserRepository userRepository;

  public CallLog findOne(Long callLogId){
    return callLogRepository.getById(callLogId);
  }

  public List<CallLog> findAll(){
    return callLogRepository.findAll();
  }

  public List<CallLog> findByRole(User userDetails) throws NotFoundException {
    Collection<GrantedAuthority> authorities = userDetails.getAuthorities();

    if(authorities.contains(new SimpleGrantedAuthority("ADMIN"))) {
      return callLogRepository.findAll();
    }

    UserEntity userEntity = userRepository.findByUsername(userDetails.getUsername());

    if(userEntity == null ) throw new NotFoundException("El usuario no ha sido encontrado");

    TelephoneLine userLine = telephoneLineRepository.findByUser(userEntity);

    if(userLine == null) throw  new NotFoundException("El usuario no tiene una linea de teléfono asignada");

    return callLogRepository.findBySenderLineOrReceiverLine(userLine,userLine);
  }

  public OutputMessage saveOrUpdate(InputMessage inputMessage){

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

        callLogRepository.save(callLog);

        return new OutputMessage(inputMessage, duration);
      }

    }
    inputMessage.setState("No definido");
    return new OutputMessage(inputMessage, "00:00");

  }
}
