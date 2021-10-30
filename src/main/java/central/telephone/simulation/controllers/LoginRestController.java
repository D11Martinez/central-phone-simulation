package central.telephone.simulation.controllers;

import central.telephone.simulation.entities.UserEntity;
import central.telephone.simulation.services.UserService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginRestController {

  @Autowired
  @Qualifier("userService")
  private UserService userService;

  @RequestMapping("/login/username")
  @JsonView()
  public  String checkEmail(@RequestParam("username") String username) {

    String json = "{\"result\": ";
    UserEntity usr = userService.findByUsername(username);

    try {
      if(usr != null) {
        if(usr.isEnabled()) {
          json = json+"\"1\"";
        }else {
          json = json+"\"2\"";
        }
      }

      json = json + "}";
    }
    catch(Exception e) {
      e.printStackTrace();
      json = "{\"result\":\"0\"}";
    }
    return json;
  }

}