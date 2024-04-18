package pe.cibertec.auditoriaInternaCovisian.models.dto;

import lombok.Data;

@Data
public class UserLider {
    private String username;
    private String password;
    private String role;
    private int dniLider;
    private String nombreLider;
    private String apellidoLider;
    private String area;

    public UserLider(){

    }

}
