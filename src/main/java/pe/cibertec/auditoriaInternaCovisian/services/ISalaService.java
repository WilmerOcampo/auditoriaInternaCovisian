package pe.cibertec.auditoriaInternaCovisian.services;

import pe.cibertec.auditoriaInternaCovisian.models.bd.Sala;

import java.util.List;
import java.util.Optional;

public interface ISalaService {
    Optional<Sala> findById(int idSala);
    List<Sala> findAll();
}
