package pe.cibertec.auditoriaInternaCovisian.controllers.backoffice;


import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pe.cibertec.auditoriaInternaCovisian.models.bd.Empleado;
import pe.cibertec.auditoriaInternaCovisian.models.bd.Evaluacion;
import pe.cibertec.auditoriaInternaCovisian.models.bd.Llamada;
import pe.cibertec.auditoriaInternaCovisian.services.IEmpleadoService;
import pe.cibertec.auditoriaInternaCovisian.services.IEvaluacionService;
import pe.cibertec.auditoriaInternaCovisian.services.ILlamadasService;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/evaluacion")
public class EvaluacionController {

    UserDetailsService userDetailsService;
    private ILlamadasService iLlamadasService;
    private IEvaluacionService iEvaluacionService;
    private IEmpleadoService iEmpleadoService;


    @GetMapping("/crear/{numeroOrden}")
    public String llamada(@PathVariable int numeroOrden, Model model, Principal principal){
        /*Estas dos primeras lineas. Tienen los datos de tu usuarios y de tu empleado/auditor */
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("user", userDetails);

        Llamada llamada = iLlamadasService.llamadaPorOrden(numeroOrden);
        model.addAttribute("llamada", llamada);

        Evaluacion evaluacion = new Evaluacion();
        model.addAttribute("evaluacion",evaluacion);

        LocalDateTime fh = LocalDateTime.now();
        model.addAttribute("fechaconhora",fh);

        return "backoffice/evaluacion/frmguardarevaluacion";
    }

    @PostMapping("/guardarEvaluacion")
    public String guardarEvaluacion(@ModelAttribute("evaluacion") Evaluacion evaluacion, @RequestParam("numeroOrden") int numeroOrden, Model model, Principal principal){
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("user", userDetails);
        iEvaluacionService.save(evaluacion);
        Llamada llamada = iLlamadasService.llamadaPorOrden(numeroOrden);
        llamada.setEstadoAuditoria(true);
        iLlamadasService.save(llamada);
        return "backoffice/llamada/frmlistallamadas";
    }

    @GetMapping("/listar")
    public String listarEvaluaciones(Model model, Principal principal) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("user", userDetails);
        String username = principal.getName();
        Empleado empleado = iEmpleadoService.findByUsername(username);
        List<Evaluacion> evaluaciones = iEvaluacionService.obtenerEvaluacionesPorEmpleado(empleado);
        model.addAttribute("evaluaciones", evaluaciones);
        return "backoffice/evaluacion/frmlistaevaluaciones";
    }

    @PostMapping("/firmar")
    public String firmarEvaluacion(@RequestParam("numeroOrden") int numeroOrden) {
        Evaluacion evaluacion = iEvaluacionService.obtenerEvaluacionPorNumeroOrden(numeroOrden);
        evaluacion.setEstadoFirma(true);
        iEvaluacionService.save(evaluacion);
        return "redirect:/evaluacion/listar";
    }

}
