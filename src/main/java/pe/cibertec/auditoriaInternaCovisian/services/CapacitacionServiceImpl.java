package pe.cibertec.auditoriaInternaCovisian.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.cibertec.auditoriaInternaCovisian.models.bd.*;
import pe.cibertec.auditoriaInternaCovisian.models.bd.pk.AsistenciaId;
import pe.cibertec.auditoriaInternaCovisian.repositories.*;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CapacitacionServiceImpl implements ICapacitacionService {
    private final ICapacitacionRepository capacitacionRepository;
    private final IAsistenciaRepository asistenciaRepository;

    @Override
    @Transactional
    public void saveTrainingAndAssistance(Capacitacion capacitacion, Asistencia asistencia) {
        Capacitacion savedCapacitacion = capacitacionRepository.save(capacitacion);
        AsistenciaId idAsistencia = new AsistenciaId(savedCapacitacion.getIdCapacitacion(), asistencia.getEmpleado().getDniEmpleado());
        asistencia.setIdAsistencia(idAsistencia);
        asistenciaRepository.save(asistencia);
    }

    @Override
    public List<Asistencia> findByEmpleado(Integer dni) {
        return asistenciaRepository.findByEmpleado(dni);
    }

    @Override
    public List<Asistencia> findByArea(String area) {
        return asistenciaRepository.findByArea(area);
    }
}
