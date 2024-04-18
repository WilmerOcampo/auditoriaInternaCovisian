package pe.cibertec.auditoriaInternaCovisian.controllers.frontoffice;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pe.cibertec.auditoriaInternaCovisian.services.IUserService;

import java.security.Principal;

@AllArgsConstructor
@Controller
@RequestMapping("/home")
public class HomeController {

    UserDetailsService userDetailsService;
    private IUserService iUserService;
    @GetMapping("/login")
    public String login() {
        return "frontoffice/login";
    }

    @GetMapping("/inicio-page")
    public String user(Model model, Principal principal) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("user", userDetails);
        return "frontoffice/inicio";
    }
}
