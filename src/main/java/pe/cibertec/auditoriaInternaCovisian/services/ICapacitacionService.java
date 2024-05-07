package pe.cibertec.auditoriaInternaCovisian.services;

import org.springframework.data.repository.query.Param;
import pe.cibertec.auditoriaInternaCovisian.models.bd.Asistencia;
import pe.cibertec.auditoriaInternaCovisian.models.bd.Capacitacion;

import java.util.List;

public interface ICapacitacionService {
    void saveTrainingAndAssistance(Capacitacion capacitacion, Asistencia asistencia);
    List<Asistencia> findByEmpleado(Integer dni);
}
