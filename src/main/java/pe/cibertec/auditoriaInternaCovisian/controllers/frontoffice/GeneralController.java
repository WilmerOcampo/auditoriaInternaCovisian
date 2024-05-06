package pe.cibertec.auditoriaInternaCovisian.controllers.frontoffice;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;

@Controller
public class GeneralController {
    UserDetailsService userDetailsService;
    /*@GetMapping("/error")
    public String error(Model model, Principal principal) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("user", userDetails);
        return "error";
    }*/
}
