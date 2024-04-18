package pe.cibertec.auditoriaInternaCovisian.services;


import pe.cibertec.auditoriaInternaCovisian.models.bd.Llamada;

import java.util.List;

public interface ILlamadasService {
	public List<Llamada> listarLlamadasPorDni(int dniEmpleado);
	public Llamada llamadaPorOrden(int orden);
	void save(Llamada llamada);
	Llamada llamadaPorIdEvaluacion(int id);

}
