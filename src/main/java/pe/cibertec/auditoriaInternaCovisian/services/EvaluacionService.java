package pe.cibertec.auditoriaInternaCovisian.services;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pe.cibertec.auditoriaInternaCovisian.models.bd.Empleado;
import pe.cibertec.auditoriaInternaCovisian.models.bd.Evaluacion;
import pe.cibertec.auditoriaInternaCovisian.repositories.EvaluacionRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class EvaluacionService implements IEvaluacionService {


    private EvaluacionRepository evaluacionRepository;

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
}
