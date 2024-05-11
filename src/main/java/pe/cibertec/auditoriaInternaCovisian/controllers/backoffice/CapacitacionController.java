package pe.cibertec.auditoriaInternaCovisian.controllers.backoffice;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pe.cibertec.auditoriaInternaCovisian.models.bd.Asistencia;
import pe.cibertec.auditoriaInternaCovisian.services.ICapacitacionService;

import java.util.List;

@Controller
@RequestMapping("/capacitacion")
@RequiredArgsConstructor
public class CapacitacionController {
    private final ICapacitacionService capacitacionService;

    @GetMapping("/{dni}")
    public String capacitacionesPorDni(@PathVariable("dni") Integer dni, Model model){
        List<Asistencia> asistencias = capacitacionService.findByEmpleado(dni);
        model.addAttribute("asistencias", asistencias);
        return "backoffice/capacitacion/capacitaciones";
    }

    @GetMapping("/a/{area}")
    public String capacitacionesPorArea(@PathVariable("area") String area, Model model){
        List<Asistencia> asistencias = capacitacionService.findByArea(area);
        model.addAttribute("asistencias", asistencias);
        return "backoffice/capacitacion/capacitaciones";
    }
}
