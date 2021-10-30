package central.telephone.simulation.controllers;

import central.telephone.simulation.entities.CentralTelephone;
import central.telephone.simulation.entities.TelephoneLine;
import central.telephone.simulation.entities.UserEntity;
import central.telephone.simulation.services.CentralTelephoneService;
import central.telephone.simulation.services.RoleService;
import central.telephone.simulation.services.TelephoneLineService;
import central.telephone.simulation.services.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
  private static final Log LOG = LogFactory.getLog(LoginController.class);

  @Autowired
  @Qualifier("userService")
  private UserService userService;

  @Autowired
  @Qualifier("roleService")
  private RoleService roleService;


  @Autowired
  @Qualifier("centralTelephoneService")
  private CentralTelephoneService centralTelephoneService;

  @Autowired
  @Qualifier("telephoneLineService")
  private TelephoneLineService telephoneLineService;


  @GetMapping("/login")
  public String showLoginForm( Model model,
                               @RequestParam(name = "error", required = false) String error,
                               @RequestParam(name = "logout", required = false) String logout){

    LOG.info("METHOD: showLoginForm() --PARAMS: error ="+error+", logout: "+logout);
    model.addAttribute("error", error);
    model.addAttribute("logout", logout);

//    roleService.createRoleIfNotExists("ADMIN","Central Telephone Administrator");
//    roleService.createRoleIfNotExists("CLIENT","Central Telephone Client");
//    UserEntity admin = userService.createUserIfNotExists("admin","1234","ADMIN");
//    UserEntity client01 = userService.createUserIfNotExists("client01","1234","CLIENT");
//    UserEntity client02 = userService.createUserIfNotExists("client02","1234","CLIENT");
//    UserEntity client03 = userService.createUserIfNotExists("client03","1234","CLIENT");
//
//    CentralTelephone centralTelephone = centralTelephoneService.createCentralTelephoneIfNotExists("Central Telephones 1",30);
//    TelephoneLine telephoneLine01 = new TelephoneLine(7001L, true,client01, centralTelephone);
//    TelephoneLine telephoneLine02 = new TelephoneLine(7002L, true,client02, centralTelephone);
//    TelephoneLine telephoneLine03 = new TelephoneLine(7003L, true,client03, centralTelephone);
//    TelephoneLine telephoneLine04 = new TelephoneLine(7004L, true,centralTelephone);
//    TelephoneLine telephoneLine05 = new TelephoneLine(7005L, true,centralTelephone);
//    TelephoneLine telephoneLine06 = new TelephoneLine(7006L, true,centralTelephone);
//
//
//    try {
//      telephoneLineService.createTelephoneLineIfNotExists(telephoneLine01);
//      telephoneLineService.createTelephoneLineIfNotExists(telephoneLine02);
//      telephoneLineService.createTelephoneLineIfNotExists(telephoneLine03);
//      telephoneLineService.createTelephoneLineIfNotExists(telephoneLine04);
//      telephoneLineService.createTelephoneLineIfNotExists(telephoneLine05);
//      telephoneLineService.createTelephoneLineIfNotExists(telephoneLine06);
//    }catch (Exception exception){
//      exception.printStackTrace();
//    }

    return "login";
  }

  @RequestMapping("/403")
  public String accessDenied( Model model,
                              @RequestParam(name = "error", required = false) String error,
                              @RequestParam(name = "logout", required = false) String logout){

    User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    model.addAttribute("username",user.getUsername());

    LOG.info("METHOD: accessDenied() --PARAMS: error ="+error+", logout: "+logout);
    model.addAttribute("error", error);
    model.addAttribute("logout", logout);
    LOG.info("Running to 403 view");
    return "error/403";
  }
}
