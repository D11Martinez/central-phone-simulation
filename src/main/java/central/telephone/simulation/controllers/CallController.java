package central.telephone.simulation.controllers;

import central.telephone.simulation.entities.CallLog;
import central.telephone.simulation.entities.TelephoneLine;
import central.telephone.simulation.models.InputMessage;
import central.telephone.simulation.models.OutputMessage;
import central.telephone.simulation.services.CallLogService;
import central.telephone.simulation.services.TelephoneLineService;
import javassist.NotFoundException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/calls")
public class CallController {
  public static final String AGENDA_LIST = "calls/agenda";
  public static final String CALL_LOG_LIST = "calls/list";
  public static final String CALL_LOG_DELETE = "calls/delete";

  private static final Log LOG = LogFactory.getLog(CallController.class);

  @Autowired
  @Qualifier("callLogService")
  private CallLogService callLogService;

  @Autowired
  @Qualifier("telephoneLineService")
  private TelephoneLineService telephoneLineService;

  @MessageMapping("call-service")
  @SendTo("/topic/call-log")
  public OutputMessage send(InputMessage inputMessage) throws Exception {
    OutputMessage outputMessage = callLogService.saveOrUpdate(inputMessage);

    LOG.info("METHOD: messageMapping() inputMessage =" + inputMessage);
    LOG.info("METHOD: messageMapping() outputMessage =" + outputMessage);

    return outputMessage;
  }

  @GetMapping("/list")
  @PreAuthorize("isAuthenticated() and (hasAnyAuthority('ADMIN','CLIENT'))")
  public String getListCallLog(Model model) {
    User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    List<CallLog> callLogsList = callLogService.findAll();

    model.addAttribute("user", user);
    model.addAttribute("callLogsList",callLogsList);

    return CALL_LOG_LIST;
  }

  @GetMapping("/agenda")
  @PreAuthorize("isAuthenticated() and (hasAnyAuthority('ADMIN','CLIENT'))")
  public String getFormCallLog(Model model) throws NotFoundException {
    User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    List<TelephoneLine> telephoneLinesList = telephoneLineService.findAgendaByUser(user);
    TelephoneLine myTelephoneLine = telephoneLineService.findTelephoneLineByUser(user);

    model.addAttribute("user", user);
    model.addAttribute("newCallLog", new CallLog());
    model.addAttribute("myTelephoneLine",myTelephoneLine);
    model.addAttribute("telephoneLinesList",telephoneLinesList);

    return AGENDA_LIST;
  }

  @PostMapping("/agenda")
  @PreAuthorize("isAuthenticated() and (hasAnyAuthority('ADMIN'))")
  public String postFormCallLog(Model model, @ModelAttribute(name="callLog") CallLog callLog) {
    User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    model.addAttribute("user", user);

    return AGENDA_LIST;
  }

  @GetMapping("/edit/{id}")
  @PreAuthorize("isAuthenticated() and (hasAnyAuthority('ADMIN'))")
  public String getEditCallLog(@PathVariable Long id, Model model) {
    User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    CallLog callLog = callLogService.findOne(id);

    model.addAttribute("user", user);
    model.addAttribute("callLog", callLog);

    return AGENDA_LIST;
  }

  @PostMapping("/edit/{id}")
  @PreAuthorize("isAuthenticated() and (hasAnyAuthority('ADMIN'))")
  public String postEditCallLog(Model model, @PathVariable Long id, @ModelAttribute(name="callLog") CallLog callLog){
    return  "redirect:/chat/list/";
  }

  @GetMapping("/delete/{id}")
  @PreAuthorize("isAuthenticated() and (hasAnyAuthority('ADMIN'))")
  public String getDeleteCallLog(@PathVariable Long id, Model model) {
    User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    CallLog callLog = callLogService.findOne(id);

    model.addAttribute("user", user);
    model.addAttribute("callLog", callLog);

    return CALL_LOG_DELETE;
  }

  @PostMapping("/delete/{id}")
  @PreAuthorize("isAuthenticated() and (hasAnyAuthority('ADMIN'))")
  public String postDeleteCallLog(Model model, @PathVariable Long id, @ModelAttribute(name="callLog") CallLog callLog){
    return  "redirect:/calls/list";
  }

}
