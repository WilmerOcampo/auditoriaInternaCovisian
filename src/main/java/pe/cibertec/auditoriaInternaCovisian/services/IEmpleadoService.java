package pe.cibertec.auditoriaInternaCovisian.services;

import pe.cibertec.auditoriaInternaCovisian.models.bd.Empleado;

import java.util.List;

public interface IEmpleadoService {

    Empleado empleadoPorIdEvaluacion(int id);

    //MIRAR
    List<Empleado> listaEmpleados();
    Empleado findByUsername(String username);
}
