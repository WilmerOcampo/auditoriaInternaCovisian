package pe.cibertec.auditoriaInternaCovisian.controllers.backoffice;

import lombok.AllArgsConstructor;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pe.cibertec.auditoriaInternaCovisian.models.bd.*;
import pe.cibertec.auditoriaInternaCovisian.models.dto.UserDto;
import pe.cibertec.auditoriaInternaCovisian.models.dto.UserEmpleado;
import pe.cibertec.auditoriaInternaCovisian.models.dto.UserLider;
import pe.cibertec.auditoriaInternaCovisian.repositories.AuditorRepository;
import pe.cibertec.auditoriaInternaCovisian.repositories.EmpleadoRepository;
import pe.cibertec.auditoriaInternaCovisian.repositories.LiderRepository;
import pe.cibertec.auditoriaInternaCovisian.services.IAuditorService;
import pe.cibertec.auditoriaInternaCovisian.services.IEmpleadoService;
import pe.cibertec.auditoriaInternaCovisian.services.ILiderService;
import pe.cibertec.auditoriaInternaCovisian.services.IUserService;

import java.security.Principal;
import java.util.List;


@AllArgsConstructor
@Controller
@RequestMapping("/user")
public class UserController {

     UserDetailsService userDetailsService;
     private IUserService iUserService;
     private ILiderService iLiderService;
     private IEmpleadoService iEmpleadoService;
     private IAuditorService iAuditorService;
     private  AuditorRepository auditorRepository;
     private  EmpleadoRepository empleadoRepository;
     private  LiderRepository liderRepository;

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
        //Ver si aca si usar UserDetails
        return "backoffice/user/frmusuario";
    }
    @PostMapping("/actualizar")
    @ResponseBody
    public String actualizarUsuario(@RequestParam("dni") int dni, @RequestParam("nombre") String nombre, @RequestParam("apellido") String apellido){
        Empleado empleado= iEmpleadoService.findByDni(dni);
        Auditor auditor = iAuditorService.findByDni(dni);
        Lider lider = iLiderService.findByDni(dni);

        if(empleado != null){
            empleado.setNombreEmpleado(nombre);
            empleado.setApellidoEmpleado(apellido);
            empleadoRepository.save(empleado);
        }else if(auditor != null){
            auditor.setNombreAuditor(nombre);
            auditor.setApellidoAuditor(apellido);
            auditorRepository.save(auditor);
        }else if (lider != null){
            lider.setNombreLider(nombre);
            lider.setApellidoLider(apellido);
            liderRepository.save(lider);
        }
        return null;
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
