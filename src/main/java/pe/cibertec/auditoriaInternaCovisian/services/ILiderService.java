package pe.cibertec.auditoriaInternaCovisian.services;

import pe.cibertec.auditoriaInternaCovisian.models.bd.Lider;

public interface ILiderService {
    Lider findByDni(int dni);
}
