package pe.cibertec.auditoriaInternaCovisian.models.dto;

import lombok.Data;

@Data
public class UserDto {
	
	private String username;
	private String password;
	private String role;
	private int dniAuditor;
	private int idAuditor;
	private String nombreAuditor;
	private String apellidoAuditor;
	
	public UserDto(String username, String password, String role) {
		super();
		this.username = username;
		this.password = password;
		this.role = role;
	}

	public UserDto() {

	}

}
