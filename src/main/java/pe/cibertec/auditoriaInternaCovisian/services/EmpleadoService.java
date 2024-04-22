package pe.cibertec.auditoriaInternaCovisian.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pe.cibertec.auditoriaInternaCovisian.models.bd.Empleado;
import pe.cibertec.auditoriaInternaCovisian.repositories.EmpleadoRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class EmpleadoService implements IEmpleadoService{

    private final EmpleadoRepository empleadoRepository;

    @Override
    public List<Empleado> listaEmpleados() {
        return empleadoRepository.findAll();
    }

    @Override
    public Empleado findByUsername(String username) {
        return empleadoRepository.findByUsername(username);
    }

    @Override
    public List<Empleado> findByArea(String area) {
        return empleadoRepository.findByArea(area);
    }

    @Override
    public Empleado findByDni(int dni) {
        return empleadoRepository.findByDniEmpleado(dni);
    }
}
