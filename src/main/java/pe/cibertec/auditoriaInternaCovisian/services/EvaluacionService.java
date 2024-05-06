package pe.cibertec.auditoriaInternaCovisian.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pe.cibertec.auditoriaInternaCovisian.models.bd.Empleado;
import pe.cibertec.auditoriaInternaCovisian.models.bd.Evaluacion;
import pe.cibertec.auditoriaInternaCovisian.repositories.EvaluacionRepository;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EvaluacionService implements IEvaluacionService {

    private EvaluacionRepository evaluacionRepository;

    @Override
    public Map<String, Double> obtenerPromediosNotasPorAreas() {
        Map<String, Double> promediosPorArea = new HashMap<>();

        // Obtener promedio de notas por área
        promediosPorArea.put("Atencion al Cliente", promedioNotasPorArea("Atencion al Cliente"));
        promediosPorArea.put("Hogar", promedioNotasPorArea("Hogar"));
        promediosPorArea.put("Ventas", promedioNotasPorArea("Ventas"));
        promediosPorArea.put("Cross-selling", promedioNotasPorArea("Cross-selling"));

        // Manejar el caso en el que el valor es null y convertirlo a Double
        for (Map.Entry<String, Double> entry : promediosPorArea.entrySet()) {
            entry.setValue(entry.getValue() != null ? entry.getValue() : 0.0);
        }

        return promediosPorArea;
    }

    @Override
    public void save(Evaluacion evaluacion) {
        evaluacionRepository.save(evaluacion);
    }
    @Override
    public List<Evaluacion> evaluacionesPorArea(String area) {
        return evaluacionRepository.listaEvaluacionesPorArea(area);
    }
    @Override
    public List<Evaluacion> ultimas5Evaluaciones(String area) {
        List<Evaluacion> evaluaciones = evaluacionRepository.listaEvaluacionesPorArea(area);

        List<Evaluacion> evalucionesOrdenadas = evaluaciones.stream().sorted(Comparator.comparing(Evaluacion::getFechahora).reversed()).collect(Collectors.toList());
        int total = evalucionesOrdenadas.size();
        int inicio = Math.max(total - 5, 0);
        int fin = total;
        List<Evaluacion> ultima5 = evaluaciones.subList(inicio,fin);
        return ultima5;
    }

    @Override
    public Evaluacion evaluacionPorId(int id) {
        return evaluacionRepository.obtenerEvaluacionPorId(id);
    }

    @Override
    public int cantEvaluacionesVistasPorLider(String area) {
        return evaluacionRepository.cantidadEvaluacionesVistasPorLider(area);
    }
    @Override
    public int cantEvaluacionesNoVistasPorLider(String area) {
        return evaluacionRepository.cantidadEvaluacionesNoVistasPorLider(area);
    }

    @Override
    public int evaluacionesAuditadasArea(String area) {
        return evaluacionRepository.cantidadEvaluaciones(area);
    }

    @Override
    public int evaluacionesAprobadasArea(String area) {
        return evaluacionRepository.cantidadEvaluacionesAprobadas(area);
    }

    @Override
    public Double promedioNotasPorArea(String area) {
        return evaluacionRepository.promedioNotasPorArea(area);
    }

    @Override
    public List<Evaluacion> obtenerEvaluacionesPorEmpleado(Empleado empleado) {
        return evaluacionRepository.findByEmpleado(empleado);
    }

    @Override
    public Evaluacion obtenerEvaluacionPorNumeroOrden(int numeroOrden) {
        return evaluacionRepository.findByNumeroOrden(numeroOrden);
    }

    @Override
    public Evaluacion evalaucionPorOrden(int orden) {
        return evaluacionRepository.findByNumeroOrden(orden);
    }

    @Override
    public Evaluacion findEvaluacionAndLlamadaByIdAndNumeroOrden(Long idEvaluacion, int numeroOrden) {
        return evaluacionRepository.findEvaluacionAndLlamadaByIdAndNumeroOrden(idEvaluacion, numeroOrden);
    }

    @Override
    public Evaluacion obtenerEvaluacionesNoVistas(String area) {
        return null;
    }
}
