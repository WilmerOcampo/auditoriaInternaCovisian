package pe.cibertec.auditoriaInternaCovisian.controllers.backoffice;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pe.cibertec.auditoriaInternaCovisian.models.bd.Evaluacion;
import pe.cibertec.auditoriaInternaCovisian.models.bd.Llamada;
import pe.cibertec.auditoriaInternaCovisian.services.IAuditorService;
import pe.cibertec.auditoriaInternaCovisian.services.IEvaluacionService;
import pe.cibertec.auditoriaInternaCovisian.services.ILlamadasService;

import java.security.Principal;
import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/llamada")
public class LlamadaController {


    UserDetailsService userDetailsService;
    private ILlamadasService iLlamadasService;
    private IEvaluacionService iEvaluacionService;
    private IAuditorService iAuditorService;

    @GetMapping("/listar")
    public String auditarGet(Model model, Principal principal){
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("user", userDetails);
        return "backoffice/llamada/frmlistallamadas";
    }
    @PostMapping("/listar")
    public String auditarPost(Model model, Principal principal, @RequestParam(name = "dniEmpleado", required = false) Integer dniEmpleado){
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("user", userDetails);
        if (dniEmpleado != null) {
            List<Llamada> llamadas = iLlamadasService.listarLlamadasPorDni(dniEmpleado);
            model.addAttribute("llamadas", llamadas);
        }
        return "backoffice/llamada/frmlistallamadas";
    }
    @GetMapping("/detail/{id}")
    public String detailLibro(@PathVariable Integer id, Model model, Principal principal) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("user", userDetails);

        Llamada llamada = iLlamadasService.llamadaPorOrden(id);
        model.addAttribute("llamada", llamada);

        Evaluacion evaluacion = iEvaluacionService.evalaucionPorOrden(llamada.getNumeroOrden());
        model.addAttribute("evaluacion",evaluacion);
        return "backoffice/llamada/llamada";
    }

    @PostMapping("/edit")
    public String editEvaluacion(@RequestParam("numeroOrden") Integer numeroOrden,
                                 @RequestParam("observacionesEvaluacion") String observaciones,
                                 @RequestParam("nota") int nota) {
        Evaluacion evaluacionPorOrden = iEvaluacionService.evalaucionPorOrden(numeroOrden);
        evaluacionPorOrden.setObservacionesEvaluacion(observaciones);
        evaluacionPorOrden.setNota(nota);
        iEvaluacionService.save(evaluacionPorOrden);

        return "redirect:/llamada/detail/" + numeroOrden;
    }



}
