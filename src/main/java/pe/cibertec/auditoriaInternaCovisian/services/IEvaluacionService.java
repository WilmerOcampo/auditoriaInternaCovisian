package pe.cibertec.auditoriaInternaCovisian.services;


import pe.cibertec.auditoriaInternaCovisian.models.bd.Empleado;
import pe.cibertec.auditoriaInternaCovisian.models.bd.Evaluacion;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface IEvaluacionService {
    Map<String, Double> obtenerPromediosNotasPorAreas();
    void save(Evaluacion evaluacion);
    List<Evaluacion> evaluacionesPorArea(String area);
    List<Evaluacion> ultimas5Evaluaciones(String area);
    Evaluacion evaluacionPorId(int id);
    List<Evaluacion> obtenerEvaluacionesPorEmpleado(Empleado empleado);
    Evaluacion obtenerEvaluacionPorNumeroOrden(int numeroOrden);
    Evaluacion evalaucionPorOrden(int orden);
    Evaluacion findEvaluacionAndLlamadaByIdAndNumeroOrden(Long idEvaluacion, int numeroOrden);
    Optional<List<Object[]>> findEvaluacionByNotaBetweenn(Integer from, Integer to);
    Evaluacion obtenerEvaluacionesNoVistas(String area);
    //Metodos para CHART JS
    int cantEvaluacionesVistasPorLider(String area);
    int cantEvaluacionesNoVistasPorLider(String area);

    Double promedioNotasPorArea(String area);
    int evaluacionesAuditadasArea(String area);
    int evaluacionesAprobadasArea(String area);
    List<Evaluacion> listaEvaluacionNotification(String area);

}
