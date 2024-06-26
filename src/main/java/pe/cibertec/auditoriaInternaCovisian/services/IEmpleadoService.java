package pe.cibertec.auditoriaInternaCovisian.services;

import pe.cibertec.auditoriaInternaCovisian.models.bd.Empleado;

import java.util.List;

public interface IEmpleadoService {

    List<Empleado> listaEmpleados();
    Empleado findByUsername(String username);
    List<Empleado> findByArea(String area);
    Empleado findByDni(int dni);
    int EmpleadosArea(String area);
}
