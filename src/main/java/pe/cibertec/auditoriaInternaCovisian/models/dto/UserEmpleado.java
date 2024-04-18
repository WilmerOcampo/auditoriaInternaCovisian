package pe.cibertec.auditoriaInternaCovisian.models.dto;

import lombok.Data;

@Data
public class UserEmpleado {

    private String username;
    private String password;
    private String role;

    private int dniEmpleado;
    private String area;
    private String nombreEmpleado;
    private String apellidoEmpleado;

}
