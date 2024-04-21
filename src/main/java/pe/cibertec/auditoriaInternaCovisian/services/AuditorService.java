package pe.cibertec.auditoriaInternaCovisian.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pe.cibertec.auditoriaInternaCovisian.models.bd.Auditor;
import pe.cibertec.auditoriaInternaCovisian.repositories.AuditorRepository;

@Service
@AllArgsConstructor
public class AuditorService implements IAuditorService {

    private final AuditorRepository auditorRepository;

    @Override
    public Auditor auditorPorDni(int dni) {
        return auditorRepository.findByDni(dni);
    }
}
