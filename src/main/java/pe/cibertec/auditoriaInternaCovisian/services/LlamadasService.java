package pe.cibertec.auditoriaInternaCovisian.services;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.cibertec.auditoriaInternaCovisian.models.bd.Llamada;
import pe.cibertec.auditoriaInternaCovisian.repositories.LlamadasRepository;

import java.util.List;


@Service
public class LlamadasService implements ILlamadasService {

	@Autowired
	private LlamadasRepository llamadasRepository;
	@Override
	public List<Llamada> listarLlamadasPorDni(int dniEmpleado) {
		return llamadasRepository.findByDniEmpleado(dniEmpleado);
	}

	@Override
	public Llamada llamadaPorOrden(int orden) {
		return llamadasRepository.findByOrden(orden);
	}

	@Override
	public void save(Llamada llamada) {
		 llamadasRepository.save(llamada);
	}

}
