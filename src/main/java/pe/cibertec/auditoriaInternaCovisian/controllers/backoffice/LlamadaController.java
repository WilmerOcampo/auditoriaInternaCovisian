package pe.cibertec.auditoriaInternaCovisian.controllers.backoffice;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pe.cibertec.auditoriaInternaCovisian.models.bd.*;
import pe.cibertec.auditoriaInternaCovisian.services.*;

import java.security.Principal;
import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/llamada")
public class LlamadaController {


    UserDetailsService userDetailsService;
    private ILlamadasService iLlamadasService;
    private IEvaluacionService iEvaluacionService;

    @GetMapping("/listar")
    public String auditarGet(Model model, Principal principal) {
        return "backoffice/llamada/frmlistallamadas";
    }

    @PostMapping("/listar")
    public String auditarPost(Model model, @RequestParam(name = "dniEmpleado", required = false) Integer dniEmpleado) {
        if (dniEmpleado != null) {
            List<Llamada> llamadas = iLlamadasService.listarLlamadasPorDni(dniEmpleado);
            model.addAttribute("llamadas", llamadas);
        }
        return "backoffice/llamada/frmlistallamadas";
    }

    @GetMapping("/detail/{id}")
    public String detailLibro(@PathVariable Integer id, Model model) {
        Llamada llamada = iLlamadasService.llamadaPorOrden(id);
        model.addAttribute("llamada", llamada);
        Evaluacion evaluacion = iEvaluacionService.evalaucionPorOrden(llamada.getNumeroOrden());
        model.addAttribute("evaluacion", evaluacion);
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
