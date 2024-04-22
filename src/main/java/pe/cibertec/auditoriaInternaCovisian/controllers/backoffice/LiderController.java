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
import pe.cibertec.auditoriaInternaCovisian.services.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Controller
@RequestMapping("/lider")
public class LiderController {
    UserDetailsService userDetailsService;
    private IEvaluacionService iEvaluacionService;
    private IEmpleadoService iEmpleadoService;

    @GetMapping("/inicio-page")
    public String inicioLider(Model model, Principal principal) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        if (userDetails instanceof CustomUserDatail) {
            CustomUserDatail customUserDetail = (CustomUserDatail) userDetails;
            String area = customUserDetail.getArea();
            model.addAttribute("evaluaciones", iEvaluacionService.ultimas5Evaluaciones(area));
            model.addAttribute("cantidadVista", iEvaluacionService.cantEvaluacionesVistasPorLider(area));
            model.addAttribute("cantidadNoVista", iEvaluacionService.cantEvaluacionesNoVistasPorLider(area));
        }
        Map<String, Double> promediosPorArea = iEvaluacionService.obtenerPromediosNotasPorAreas();
        model.addAttribute("promediosPorArea", promediosPorArea);
        model.addAttribute("user", userDetails);
        return "backoffice/lider/inicio";
    }

    @GetMapping("/evaluaciones/{area}")
    public String listarEvaluacionesPorAreaLider(@PathVariable String area, Model model, Principal principal) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("user", userDetails);
        model.addAttribute("evaluaciones", iEvaluacionService.evaluacionesPorArea(area));

        return "backoffice/lider/frmlistaevaluaciones";
    }
    @GetMapping("/empleados/{area}")
    public String listarEmpleadosPorAreaLider(@PathVariable String area, Model model, Principal principal) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("user", userDetails);
        model.addAttribute("empleados", iEmpleadoService.findByArea(area));

        return "backoffice/lider/frmlistaempleados";
    }

    /* GET y POST en formatos JSON para manipular en mi AJAX (Vista lider) */
    @PostMapping("/actualizarCampo")
    @ResponseBody
    public String actualizarCampoEstadoLider(@RequestParam("id") int id){
        Evaluacion evaluacion = iEvaluacionService.evaluacionPorId(id);
        evaluacion.setEstadoLider(true);
        iEvaluacionService.save(evaluacion);
        return null;
    }
    @GetMapping("/obtenerEvaluacion")
    @ResponseBody
    public Map<String,Object> obtenerDatosEvaluacion(@RequestParam("id") int id){
        Map<String, Object> datos = new HashMap<>();
        Evaluacion evaluacion = iEvaluacionService.evaluacionPorId(id);
        if (evaluacion != null) {
            //Datos Evaluacion
            datos.put("nota", evaluacion.getNota());
            datos.put("observacionesEvaluacion", evaluacion.getObservacionesEvaluacion());
            //Datos Auditor
            datos.put("apellidoAuditor", evaluacion.getAuditor().getApellidoAuditor());
            datos.put("nombreAuditor", evaluacion.getAuditor().getNombreAuditor());
            datos.put("dniAuditor", evaluacion.getAuditor().getDniAuditor());
            //Datos Empleado
            datos.put("nombreEmpleado",evaluacion.getEmpleado().getApellidoEmpleado());
            datos.put("apellidoEmpleado",evaluacion.getEmpleado().getNombreEmpleado());
            datos.put("dniEmpleado",evaluacion.getEmpleado().getDniEmpleado());
            datos.put("nombreCompleto",evaluacion.getEmpleado().getApellidoEmpleado() + " " +evaluacion.getEmpleado().getNombreEmpleado());
            //Datos Llamada
            datos.put("tipoLlamada",evaluacion.getLlamada().getTipo());
        }
        return datos;
    }

}
