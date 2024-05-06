package pe.cibertec.auditoriaInternaCovisian.services;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pe.cibertec.auditoriaInternaCovisian.models.bd.*;
import pe.cibertec.auditoriaInternaCovisian.repositories.*;
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

        return new CustomUserDatail(user,auditor,empleado,lider);

	}

}
