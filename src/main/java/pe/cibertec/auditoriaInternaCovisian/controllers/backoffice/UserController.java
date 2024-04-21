package pe.cibertec.auditoriaInternaCovisian.controllers.backoffice;

import lombok.AllArgsConstructor;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pe.cibertec.auditoriaInternaCovisian.models.bd.User;
import pe.cibertec.auditoriaInternaCovisian.models.dto.UserDto;
import pe.cibertec.auditoriaInternaCovisian.models.dto.UserEmpleado;
import pe.cibertec.auditoriaInternaCovisian.models.dto.UserLider;
import pe.cibertec.auditoriaInternaCovisian.services.IUserService;

import java.security.Principal;
import java.util.List;


@AllArgsConstructor
@Controller
@RequestMapping("/user")
public class UserController {

     UserDetailsService userDetailsService;
     private IUserService iUserService;

    @GetMapping("/registroAuditor")
    public String registroAuditorInicio(Model model) {
        model.addAttribute("userAndAuditorDto", new UserDto());
        return "backoffice/user/frmregistroauditor";
    }
    @PostMapping("/registroAuditor")
    public String registroAuditorPost(@ModelAttribute("userAndAuditorDto") UserDto userAndAuditorDto, Model model) {
        iUserService.saveUserAndAuditor(userAndAuditorDto);
        model.addAttribute("message", "Registro Correcto");
        return "backoffice/user/frmregistroauditor";
    }

    @GetMapping("/registroEmpleado")
    public String registroEmpleadoInicio(Model model){
        model.addAttribute("userAndEmpleadoDto", new UserEmpleado());
        return "backoffice/user/frmregistroempleado";
    }

    @PostMapping("/registroEmpleado")
    public  String registroEmpleadoPost(@ModelAttribute("userAndEmpleadoDto") UserEmpleado userAndEmpleadoDto, Model model){
        iUserService.saveUserAndEmpleado(userAndEmpleadoDto);
        model.addAttribute("message", "Registro Correcto");
        return "backoffice/user/frmregistroempleado";
    }


    @GetMapping("/registroLider")
    public String registroLiderInicio(Model model){
        model.addAttribute("userAndLiderDto", new UserLider());
        return "backoffice/user/frmregistrolider";
    }

    @PostMapping("/registroLider")
    public  String registroLiderPost(@ModelAttribute("userAndLiderDto") UserLider userAndLiderDto, Model model){
        iUserService.saveUserAndLider(userAndLiderDto);
        model.addAttribute("message", "Registro Correcto");
        return "backoffice/user/frmregistrolider";
    }

    @GetMapping("/detail")
    public String verUsuario(Model model, Principal principal){
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("user", userDetails);
        return "backoffice/user/frmusuario";
    }
    @PostMapping("/cambiarContrasenia")
    @ResponseBody
    public String cambiarContraseña(@RequestParam("username") String username, @RequestParam("password") String password){
        iUserService.cambiarContraseña(username,password);
        return null;
    }
    @GetMapping("/prueba")
    @ResponseBody
    UserDetails nose(Model model, Principal principal){
        return userDetailsService.loadUserByUsername(principal.getName());
    }

    //METODOS PARA EXPOTAR USUARIOS EN FORMATO PDF - XLS (POR MOTIVOS DE NEGOCIO NO SE USAN - PERO GUARDO PARA REUTILIZARLO)
    /*
    @GetMapping("/export-pdf")
    public ResponseEntity<byte[]> exportPdf() throws JRException, FileNotFoundException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("petsReport", "usuariosReporte.pdf");
        return ResponseEntity.ok().headers(headers).body(iUserService.exportPdf());
    }

    @GetMapping("/export-xls")
    public ResponseEntity<byte[]> exportXls() throws JRException, FileNotFoundException {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet; charset=UTF-8");
        var contentDisposition = ContentDisposition.builder("attachment").filename("usuariosReporte" + ".xls").build();
        headers.setContentDisposition(contentDisposition);
        return ResponseEntity.ok().headers(headers).body(iUserService.exportXls());
    }*/

}
