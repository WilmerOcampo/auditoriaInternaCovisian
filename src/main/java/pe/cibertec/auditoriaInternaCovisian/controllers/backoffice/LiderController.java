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
    private ILlamadasService iLlamadasService;

    /*LIMPIAR CODIGO AQUI*/
    @GetMapping("/inicio-page")
    public String inicioLider(Model model, Principal principal) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());

        /*DATOS PARA CHART.JS (INTENTARE INYECTARLO PARA AJAX Y REDUCIR CODIGO JAVA)*/
        if (userDetails instanceof CustomUserDatail) {
            CustomUserDatail customUserDetail = (CustomUserDatail) userDetails;
            String area = customUserDetail.getArea();
            int cantidadVista = iEvaluacionService.cantEvaluacionesVistasPorLider(area);
            int cantidadNoVista = iEvaluacionService.cantEvaluacionesNoVistasPorLider(area);
            model.addAttribute("cantidadVista", cantidadVista);
            model.addAttribute("cantidadNoVista", cantidadNoVista);
        } else {

        }
        Double notaATC = iEvaluacionService.promedioNotasPorArea("Atencion al Cliente");
        Double notaHogar = iEvaluacionService.promedioNotasPorArea("Hogar");
        Double notaVentas = iEvaluacionService.promedioNotasPorArea("Ventas");
        Double notaCS = iEvaluacionService.promedioNotasPorArea("Cross-selling");

        // Manejar el caso en el que el valor es null
        double notaATCValor = notaATC != null ? notaATC : 0.0;
        double notaHogarValor = notaHogar != null ? notaHogar : 0.0;
        double notaVentasValor = notaVentas != null ? notaVentas : 0.0;
        double notaCSValor = notaCS != null ? notaCS : 0.0;

        // Agregar los valores al modelo
        model.addAttribute("notaATC", notaATCValor);
        model.addAttribute("notaHogar", notaHogarValor);
        model.addAttribute("notaVentas", notaVentasValor);
        model.addAttribute("notaCS", notaCSValor);


        /*==============================================================================*/

        model.addAttribute("user", userDetails);
        return "backoffice/lider/inicio";
    }

    @GetMapping("/evaluaciones/{area}")
    public String listarEvaluacionesPorAreaLider(@PathVariable String area, Model model, Principal principal) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("user", userDetails);

        List<Evaluacion> listaEvaluaciones=iEvaluacionService.evaluacionesPorArea(area);
        model.addAttribute("evaluaciones", listaEvaluaciones);

        return "backoffice/lider/frmlistaevaluaciones";
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
            datos.put("nota", evaluacion.getNota());
            datos.put("observacionesEvaluacion", evaluacion.getObservacionesEvaluacion());
        }
        Empleado empleado = iEmpleadoService.empleadoPorIdEvaluacion(id);
        if (empleado != null) {
            datos.put("nombreEmpleado", empleado.getNombreEmpleado());
            datos.put("apellidoEmpleado", empleado.getApellidoEmpleado());
            datos.put("dniEmpleado", empleado.getDniEmpleado());
            datos.put("nombreCompleto", empleado.getNombreEmpleado() + ' ' + empleado.getApellidoEmpleado());
        }
        Llamada llamada = iLlamadasService.llamadaPorIdEvaluacion(id);
        if (llamada != null) {
            datos.put("tipoLlamada", llamada.getTipo());
        }
        return datos;
    }

}
