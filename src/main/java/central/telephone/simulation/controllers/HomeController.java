package central.telephone.simulation.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class HomeController {

  @GetMapping("/")
  public String inicio() {
    return "/login";
  }

  @GetMapping("/home")
  public String home(Model model){
    try {
      User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
      model.addAttribute("user",user);
    }catch(Exception e) {
      System.out.print(e.getMessage());
    }
    return "plantilla/home";
  }
}
