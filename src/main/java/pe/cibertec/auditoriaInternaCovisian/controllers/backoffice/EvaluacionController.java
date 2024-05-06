package pe.cibertec.auditoriaInternaCovisian.controllers.backoffice;


import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pe.cibertec.auditoriaInternaCovisian.models.bd.*;
import pe.cibertec.auditoriaInternaCovisian.services.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        iEvaluacionService.save(evaluacion);
        Llamada llamada = iLlamadasService.llamadaPorOrden(numeroOrden);
        llamada.setEstadoAuditoria(true);
        iLlamadasService.save(llamada);
        return "backoffice/llamada/frmlistallamadas";
    }

    @GetMapping("/listar")
    public String listarEvaluaciones(Model model, Principal principal) {
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
    @GetMapping("/{id}/detalles")
    @ResponseBody
    public Map<String, Object> getEvaluacionDetailsPage(@PathVariable("id") Long idEvaluacion, @RequestParam("numeroOrden") int numeroOrden) {
        Map<String, Object> datos = new HashMap<>();
        Evaluacion evaluacion = iEvaluacionService.findEvaluacionAndLlamadaByIdAndNumeroOrden(idEvaluacion, numeroOrden);
        if (evaluacion != null) {
            //Datos de la Llamada
            datos.put("numeroOrden", evaluacion.getLlamada().getNumeroOrden());
            datos.put("area", evaluacion.getLlamada().getArea());
            datos.put("tipo", evaluacion.getLlamada().getTipo());
            datos.put("subarea", evaluacion.getLlamada().getSubarea());
        }
        return datos;
    }

}
