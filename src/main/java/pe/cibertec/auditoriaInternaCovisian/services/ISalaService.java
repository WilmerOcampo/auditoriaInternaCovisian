package pe.cibertec.auditoriaInternaCovisian.services;

import pe.cibertec.auditoriaInternaCovisian.models.bd.Sala;

import java.util.List;

public interface ISalaService {
    List<Sala> findAll();
}
