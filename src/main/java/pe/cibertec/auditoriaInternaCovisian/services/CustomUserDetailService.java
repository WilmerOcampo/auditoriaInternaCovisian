package pe.cibertec.auditoriaInternaCovisian.services;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pe.cibertec.auditoriaInternaCovisian.models.bd.Auditor;
import pe.cibertec.auditoriaInternaCovisian.models.bd.Empleado;
import pe.cibertec.auditoriaInternaCovisian.models.bd.Lider;
import pe.cibertec.auditoriaInternaCovisian.models.bd.User;
import pe.cibertec.auditoriaInternaCovisian.repositories.AuditorRepository;
import pe.cibertec.auditoriaInternaCovisian.repositories.EmpleadoRepository;
import pe.cibertec.auditoriaInternaCovisian.repositories.LiderRepository;
import pe.cibertec.auditoriaInternaCovisian.repositories.UserRepository;
//CAMBIAR
@Service
@AllArgsConstructor
public class CustomUserDetailService implements UserDetailsService{

	private final UserRepository userRepository;
	private final AuditorRepository auditorRepository;
	private final EmpleadoRepository empleadoRepository;
	private final LiderRepository liderRepository;


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		Long codigo = user.getId();
		Auditor auditor = auditorRepository.findByIdUser(codigo);
		Empleado empleado = empleadoRepository.findByIdUser(codigo);
		Lider lider = liderRepository.findByIdUser(codigo);

		if(user == null) {
			throw new UsernameNotFoundException("User no encontrado");
		}
		
		return new CustomUserDatail(user,auditor,empleado,lider);

	}

}
