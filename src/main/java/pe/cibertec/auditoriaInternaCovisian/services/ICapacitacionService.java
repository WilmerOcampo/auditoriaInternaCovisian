package pe.cibertec.auditoriaInternaCovisian.services;

import pe.cibertec.auditoriaInternaCovisian.models.bd.Asistencia;
import pe.cibertec.auditoriaInternaCovisian.models.bd.Capacitacion;

public interface ICapacitacionService {
    void saveTrainingAndAssistance(Capacitacion capacitacion, Asistencia asistencia);
}
