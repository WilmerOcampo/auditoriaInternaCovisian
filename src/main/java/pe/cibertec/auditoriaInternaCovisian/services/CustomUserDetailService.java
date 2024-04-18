package pe.cibertec.auditoriaInternaCovisian.services;


import org.springframework.beans.factory.annotation.Autowired;
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
public class CustomUserDetailService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AuditorRepository auditorRepository;
	@Autowired
	private EmpleadoRepository empleadoRepository;
	@Autowired
	private LiderRepository liderRepository;


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
