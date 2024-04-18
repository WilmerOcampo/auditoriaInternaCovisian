package pe.cibertec.auditoriaInternaCovisian.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.cibertec.auditoriaInternaCovisian.models.bd.Auditor;
import pe.cibertec.auditoriaInternaCovisian.repositories.AuditorRepository;

@Service
public class AuditorService implements IAuditorService {

    @Autowired
    AuditorRepository auditorRepository;

    @Override
    public Auditor auditorPorDni(int dni) {
        return auditorRepository.findByDni(dni);
    }
}
