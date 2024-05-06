package pe.cibertec.auditoriaInternaCovisian.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.cibertec.auditoriaInternaCovisian.models.bd.Sala;
import pe.cibertec.auditoriaInternaCovisian.repositories.ISalaRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SalaServiceImpl implements ISalaService {
    private final ISalaRepository salaRepository;

    @Override
    public List<Sala> findAll() {
        return salaRepository.findAll();
    }
}
