package pe.cibertec.auditoriaInternaCovisian.controllers.backoffice;

import jakarta.servlet.http.*;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pe.cibertec.auditoriaInternaCovisian.models.bd.*;
import pe.cibertec.auditoriaInternaCovisian.services.*;

import java.time.LocalDateTime;
import java.util.*;

@AllArgsConstructor
@Controller
@RequestMapping("/lider")
public class LiderController {
    UserDetailsService userDetailsService;
    private IEvaluacionService iEvaluacionService;
    private IEmpleadoService iEmpleadoService;
    private final IFeedbackService iFeedbackService;
    private final ILiderService iLiderService;
    private final ISalaService iSalaService;
    private final ICapacitacionService iCapacitacionService;

    @GetMapping("/inicio-page")
    public String inicioLider(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        UserDetails userDetailss = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        CustomUserDatail customUserDatail = (CustomUserDatail) userDetailss;
        session.setAttribute("user", customUserDatail);

        model.addAttribute("evaluaciones", iEvaluacionService.ultimas5Evaluaciones(customUserDatail.getArea()));
        model.addAttribute("cantidadVista", iEvaluacionService.cantEvaluacionesVistasPorLider(customUserDatail.getArea()));
        model.addAttribute("cantidadNoVista", iEvaluacionService.cantEvaluacionesNoVistasPorLider(customUserDatail.getArea()));

        model.addAttribute("cantempleados", iEmpleadoService.EmpleadosArea(customUserDatail.getArea()));
        model.addAttribute("evaluacionesAuditadas", iEvaluacionService.evaluacionesAuditadasArea(customUserDatail.getArea()));
        model.addAttribute("evaluacionesaprobadas", iEvaluacionService.evaluacionesAprobadasArea(customUserDatail.getArea()));

        Map<String, Double> promediosPorArea = iEvaluacionService.obtenerPromediosNotasPorAreas();
        model.addAttribute("promediosPorArea", promediosPorArea);

        return "backoffice/lider/inicio";
    }

    @GetMapping("/evaluaciones/{area}")
    public String listarEvaluacionesPorAreaLider(@PathVariable String area, Model model) {
        model.addAttribute("evaluaciones", iEvaluacionService.evaluacionesPorArea(area));
        return "backoffice/lider/frmlistaevaluaciones";
    }

    @GetMapping("/empleados/{area}")
    public String listarEmpleadosPorAreaLider(@PathVariable String area, Model model) {
        model.addAttribute("empleados", iEmpleadoService.findByArea(area));
        return "backoffice/lider/frmlistaempleados";
    }

    /* GET y POST en formatos JSON para manipular en mi AJAX (Vista lider) */
    @PostMapping("/actualizarCampo")
    @ResponseBody
    public String actualizarCampoEstadoLider(@RequestParam("id") int id) {
        Evaluacion evaluacion = iEvaluacionService.evaluacionPorId(id);
        evaluacion.setEstadoLider(true);
        iEvaluacionService.save(evaluacion);
        return null;
    }

    @GetMapping("/obtenerEvaluacion")
    @ResponseBody
    public Map<String, Object> obtenerDatosEvaluacion(@RequestParam("id") int id) {
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
            datos.put("nombreEmpleado", evaluacion.getEmpleado().getApellidoEmpleado());
            datos.put("apellidoEmpleado", evaluacion.getEmpleado().getNombreEmpleado());
            datos.put("dniEmpleado", evaluacion.getEmpleado().getDniEmpleado());
            datos.put("nombreCompleto", evaluacion.getEmpleado().getApellidoEmpleado() + " " + evaluacion.getEmpleado().getNombreEmpleado());
            //Datos Llamada
            datos.put("tipoLlamada", evaluacion.getLlamada().getTipo());
        }
        return datos;
    }

    /**
    * FEEDBACK Y MEMORANDUM
    * **/
    @GetMapping("/evaluacion/list")
    @ResponseBody
    public Optional<List<Object[]>> feedbacksList() {
        return Optional.of(iEvaluacionService.findEvaluacionByNotaBetweenn(0, 0).orElse(new ArrayList<>()));
    }

    @GetMapping("/evaluacion/{id}")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> evaluacionId(@PathVariable Integer id, HttpServletRequest request) {
        UserDetails userDetailss = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        CustomUserDatail customUserDatail = (CustomUserDatail) userDetailss;
        Optional<Evaluacion> optionalEvaluacion = Optional.ofNullable(iEvaluacionService.evaluacionPorId(id));
        if (optionalEvaluacion.isPresent()) {
            Map<String, Object> response = new HashMap<>();
            int dniLider = customUserDatail.getDni();
            String nombreLider = customUserDatail.getNombre() + " " + customUserDatail.getApellido();
            Empleado empleado = iEmpleadoService.findByDni(optionalEvaluacion.get().getEmpleado().getDniEmpleado());
            String nombreEmpleado = empleado.getNombreEmpleado() + " " + empleado.getApellidoEmpleado();

            String cuerpoMemorandum = "Hacemos la presente carta de compromiso para el empleado " + nombreEmpleado + ", quien incumplió su protocolo laboral, " +
                    "teniendo la finalidad de su pronta mejora en la gestión, perteneciente al area " + empleado.getArea() + ", resposable del lider " + nombreLider + ".";
            response.put("dniempleado", empleado.getDniEmpleado());
            response.put("nombreempleado", nombreEmpleado);
            response.put("dnilider", dniLider);
            response.put("cuerpo", cuerpoMemorandum);

            response.put("area", empleado.getArea());
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/fb-memo/save")
    public ResponseEntity<?> saveFeedbackAndMemorandum(@RequestBody Map<String, Object> data) {
        Empleado empleado = iEmpleadoService.findByDni(Integer.parseInt((String) data.get("dniEmpleado")));
        Lider lider = iLiderService.findByDni(Integer.parseInt((String) data.get("dniLider")));

        if (empleado == null || lider == null) {
            return ResponseEntity.badRequest().body("Empleado o Lider no encontrado");
        }

        Feedback feedback = new Feedback();
        feedback.setMotivo((String) data.get("motivo"));
        feedback.setEmpleado(empleado);
        feedback.setLider(lider);

        Memorandum memorandum = new Memorandum();
        memorandum.setAsunto((String) data.get("asunto"));
        memorandum.setCuerpo((String) data.get("cuerpo"));
        memorandum.setFecha(LocalDateTime.parse((String) data.get("fecha")));
        iFeedbackService.saveFeedbackAndMemorandum(feedback, memorandum);
        return ResponseEntity.ok().build();
    }

    /**
     * CAPACITACION Y ASISTENCIA
     * **/
    @GetMapping("/evaluacion/list-capacitacion")
    @ResponseBody
    public Optional<List<Object[]>> listCapacitaciones() {
        return Optional.of(iEvaluacionService.findEvaluacionByNotaBetweenn(11, 30).orElse(new ArrayList<>()));
    }

    @GetMapping("/sala/list")
    @ResponseBody
    public List<Sala> salas() {
        return iSalaService.findAll();
    }

    @PostMapping("/capac-asis/save")
    public ResponseEntity<?> saveTrainingAndAssistance(@RequestBody Map<String, Object> data) {
        Empleado empleado = iEmpleadoService.findByDni(Integer.parseInt((String) data.get("dniEmpleado")));
        Lider lider = iLiderService.findByDni(Integer.parseInt((String) data.get("dniLider")));
        Optional<Sala> optionalSala = iSalaService.findById(Integer.parseInt((String) data.get("idSala")));
        Sala sala = optionalSala.get();

        if (empleado == null || lider == null) {
            return ResponseEntity.badRequest().body("Empleado o Lider no encontrado");
        }

        Capacitacion capacitacion = new Capacitacion();
        capacitacion.setTema((String) data.get("tema"));
        capacitacion.setDetalle((String) data.get("detalle"));
        capacitacion.setLider(lider);
        capacitacion.setSala(sala);

        Asistencia asistencia = new Asistencia();
        asistencia.setEmpleado(empleado);
        asistencia.setModalidad((String) data.get("modalidad"));
        asistencia.setFecha(LocalDateTime.parse((String) data.get("fecha")));
        iCapacitacionService.saveTrainingAndAssistance(capacitacion, asistencia);
        return ResponseEntity.ok().build();
    }

}
