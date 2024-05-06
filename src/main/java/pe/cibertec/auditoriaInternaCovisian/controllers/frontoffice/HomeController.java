package pe.cibertec.auditoriaInternaCovisian.controllers.frontoffice;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pe.cibertec.auditoriaInternaCovisian.services.CustomUserDatail;

@AllArgsConstructor
@Controller
@RequestMapping("/home")
public class HomeController {

    UserDetailsService userDetailsService;

    @GetMapping("/login")
    public String login() {
        return "frontoffice/login";
    }

    @GetMapping("/inicio-page")
    public String user(HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserDetails userDetailss = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        CustomUserDatail customUserDatail = (CustomUserDatail) userDetailss;
        session.setAttribute("user", customUserDatail);
        return "frontoffice/inicio";
    }

    @GetMapping("/admin-inicio-page")
    public String admin(HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserDetails userDetailss = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        CustomUserDatail customUserDatail = (CustomUserDatail) userDetailss;
        session.setAttribute("user", customUserDatail);
        return "backoffice/admin/inicio";
    }

    @GetMapping("/solicitud-cambiar-password")
    public String solicitudCambiarPassword() {
        return "frontoffice/frmcambiarpassword";
    }
}
