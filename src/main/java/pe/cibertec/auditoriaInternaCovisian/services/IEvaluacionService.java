package pe.cibertec.auditoriaInternaCovisian.services;


import org.springframework.data.repository.query.Param;
import pe.cibertec.auditoriaInternaCovisian.models.bd.Empleado;
import pe.cibertec.auditoriaInternaCovisian.models.bd.Evaluacion;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface IEvaluacionService {
    Map<String, Double> obtenerPromediosNotasPorAreas();
	//Evaluacion save(Evaluacion e);
    void save(Evaluacion evaluacion);
    List<Evaluacion> evaluacionesPorArea(String area);
    List<Evaluacion> ultimas5Evaluaciones(String area);
    Evaluacion evaluacionPorId(int id);
    int cantEvaluacionesVistasPorLider(String area);
    int cantEvaluacionesNoVistasPorLider(String area);
    public Double promedioNotasPorArea(String area);
    List<Evaluacion> obtenerEvaluacionesPorEmpleado(Empleado empleado);
    Evaluacion obtenerEvaluacionPorNumeroOrden(int numeroOrden);
    public Evaluacion evalaucionPorOrden(int orden);
    Evaluacion findEvaluacionAndLlamadaByIdAndNumeroOrden(Long idEvaluacion, int numeroOrden);

    Optional<List<Evaluacion>> findEvaluacionByNotaBetween(Integer from, Integer to);

    List<Evaluacion> findAll();

    Optional<List<Object[]>> findEvaluacionByNotaBetweenn(Integer from, Integer to);
}
