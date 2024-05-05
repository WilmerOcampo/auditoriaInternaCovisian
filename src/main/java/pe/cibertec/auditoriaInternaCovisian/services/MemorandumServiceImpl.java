package pe.cibertec.auditoriaInternaCovisian.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.cibertec.auditoriaInternaCovisian.models.bd.Memorandum;
import pe.cibertec.auditoriaInternaCovisian.repositories.IMemorandumRepository;

@Service
@RequiredArgsConstructor
public class MemorandumServiceImpl implements IMemorandumService {
    private final IMemorandumRepository memorandumRepository;


    @Override
    public void save(Memorandum memorandum) {
        memorandumRepository.save(memorandum);
    }
}
