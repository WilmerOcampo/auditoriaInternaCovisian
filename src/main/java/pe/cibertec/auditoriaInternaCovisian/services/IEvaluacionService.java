package pe.cibertec.auditoriaInternaCovisian.services;


import pe.cibertec.auditoriaInternaCovisian.models.bd.Empleado;
import pe.cibertec.auditoriaInternaCovisian.models.bd.Evaluacion;

import java.util.List;

public interface IEvaluacionService {
	//Evaluacion save(Evaluacion e);
    void save(Evaluacion evaluacion);
    List<Evaluacion> evaluacionesPorArea(String area);
    Evaluacion evaluacionPorId(int id);
    int cantEvaluacionesVistasPorLider(String area);
    int cantEvaluacionesNoVistasPorLider(String area);
    public Double promedioNotasPorArea(String area);
    List<Evaluacion> obtenerEvaluacionesPorEmpleado(Empleado empleado);
    Evaluacion obtenerEvaluacionPorNumeroOrden(int numeroOrden);
    public Evaluacion evalaucionPorOrden(int orden);


}
