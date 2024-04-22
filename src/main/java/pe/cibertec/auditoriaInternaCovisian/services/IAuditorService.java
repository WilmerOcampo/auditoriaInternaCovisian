package pe.cibertec.auditoriaInternaCovisian.services;


import pe.cibertec.auditoriaInternaCovisian.models.bd.Auditor;

public interface IAuditorService {
	Auditor findByDni(int dni);
}
