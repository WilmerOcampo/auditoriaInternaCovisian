package pe.cibertec.auditoriaInternaCovisian.services;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pe.cibertec.auditoriaInternaCovisian.models.bd.Lider;
import pe.cibertec.auditoriaInternaCovisian.repositories.LiderRepository;

@AllArgsConstructor
@Service
public class LiderService implements ILiderService{

    private LiderRepository liderRepository;
    @Override
    public Lider findByDni(int dni) {
        return liderRepository.findByDniLider(dni);
    }
}
