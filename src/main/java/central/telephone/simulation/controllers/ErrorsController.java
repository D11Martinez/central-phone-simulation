package central.telephone.simulation.controllers;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorsController {
  public static final String ISE_500 = "error/500";
  public static final String ISE_403 = "error/403";

  @ExceptionHandler(AccessDeniedException.class)
  public String showAccessDeniedError( Model model){
    try{
      User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
      model.addAttribute("user",user);
    }catch(Exception e) {
      e.printStackTrace();
    }
    return ISE_403;
  }

  //@ExceptionHandler(Exception.class)
  public String showInternalServerError(Model model){
    try{
      User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
      model.addAttribute("user",user);
    }catch(Exception e) {
      e.printStackTrace();
    }

    return ISE_500;
  }
}
