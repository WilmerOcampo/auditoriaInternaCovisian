package pe.cibertec.auditoriaInternaCovisian.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.cibertec.auditoriaInternaCovisian.models.bd.Sala;
import pe.cibertec.auditoriaInternaCovisian.repositories.ISalaRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SalaServiceImpl implements ISalaService {
    private final ISalaRepository salaRepository;

    @Override
    public Optional<Sala> findById(int idSala) {
        return salaRepository.findById(idSala);
    }

    @Override
    public List<Sala> findAll() {
        return salaRepository.findAll();
    }
}
