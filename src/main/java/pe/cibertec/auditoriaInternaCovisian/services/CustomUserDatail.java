package pe.cibertec.auditoriaInternaCovisian.services;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pe.cibertec.auditoriaInternaCovisian.models.bd.Auditor;
import pe.cibertec.auditoriaInternaCovisian.models.bd.Empleado;
import pe.cibertec.auditoriaInternaCovisian.models.bd.Lider;
import pe.cibertec.auditoriaInternaCovisian.models.bd.User;

import java.util.Collection;
import java.util.List;

public class CustomUserDatail implements UserDetails{

	private User user;
	private Auditor auditor;
	private Empleado empleado;
	private Lider lider;
	
	public CustomUserDatail(User user, Auditor auditor, Empleado empleado,Lider lider) {
		super();
		this.user = user;
		this.auditor = auditor;
		this.empleado = empleado;
		this.lider = lider;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return List.of(()-> user.getRole());
	}

	
	public String getNombreCompleto() {
		if (auditor != null) {
			return auditor.getNombreAuditor() + " " + auditor.getApellidoAuditor();
		} else if (empleado != null) {
			return empleado.getNombreEmpleado() + " " + empleado.getApellidoEmpleado();
		} else if(lider != null){
			return lider.getNombreLider() + " " +lider.getApellidoLider();
		}
		else {
			return "Nombre no disponible";
		}
	}
	public String getNombre(){
		if (auditor != null) {
			return auditor.getNombreAuditor();
		} else if (empleado != null) {
			return empleado.getNombreEmpleado();
		} else if (lider != null) {
			return lider.getNombreLider();
		} else {
			return "Nombre no disponible";
		}
	}
	public String getApellido(){
		if (auditor != null) {
			return auditor.getApellidoAuditor();
		} else if (empleado != null) {
			return empleado.getApellidoEmpleado();
		} else if (lider != null) {
			return lider.getApellidoLider();
		} else {
			return "Nombre no disponible";
		}
	}
	public int getDni(){
		if (auditor != null) {
			return auditor.getDniAuditor();
		} else if (empleado != null) {
			return empleado.getDniEmpleado();
		} else if (lider != null) {
			return lider.getDniLider();
		} else {
			return 0;
		}
	}
	public String getArea(){
		if(lider != null){
			return lider.getArea();
		}
		else if( empleado != null){
			return empleado.getArea();
		}else{
			return "Sin area";
		}
	}

	public String getRole() {
		return user.getRole();
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
