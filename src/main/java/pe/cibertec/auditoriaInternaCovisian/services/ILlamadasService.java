package pe.cibertec.auditoriaInternaCovisian.services;

import pe.cibertec.auditoriaInternaCovisian.models.bd.Llamada;

import java.util.List;

public interface ILlamadasService {
	List<Llamada> listarLlamadasPorDni(int dniEmpleado);
	Llamada llamadaPorOrden(int orden);
	void save(Llamada llamada);

}
