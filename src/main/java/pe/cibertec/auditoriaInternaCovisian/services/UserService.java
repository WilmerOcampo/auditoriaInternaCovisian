package pe.cibertec.auditoriaInternaCovisian.services;


import lombok.AllArgsConstructor;
import net.sf.jasperreports.engine.JRException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.cibertec.auditoriaInternaCovisian.models.dto.UserDto;
import pe.cibertec.auditoriaInternaCovisian.models.dto.UserEmpleado;
import pe.cibertec.auditoriaInternaCovisian.models.dto.UserLider;
import pe.cibertec.auditoriaInternaCovisian.models.bd.Auditor;
import pe.cibertec.auditoriaInternaCovisian.models.bd.Empleado;
import pe.cibertec.auditoriaInternaCovisian.models.bd.Lider;
import pe.cibertec.auditoriaInternaCovisian.models.bd.User;
import pe.cibertec.auditoriaInternaCovisian.repositories.AuditorRepository;
import pe.cibertec.auditoriaInternaCovisian.repositories.EmpleadoRepository;
import pe.cibertec.auditoriaInternaCovisian.repositories.LiderRepository;
import pe.cibertec.auditoriaInternaCovisian.repositories.UserRepository;
import pe.cibertec.auditoriaInternaCovisian.util.reportes.UserReportGenerator;

import java.io.FileNotFoundException;
import java.util.List;
// CAMBIARLO
@Service
@AllArgsConstructor
public class UserService implements IUserService{

	private final PasswordEncoder passwordEncoder;
	private final UserRepository userRepository;
	private final UserReportGenerator userReportGenerator;
	private final AuditorRepository auditorRepository;
	private final EmpleadoRepository empleadoRepository;
	private final LiderRepository liderRepository;

	/*@Override
	public User save(UserDto userDto) {
		User user = new User(userDto.getUsername(),passwordEncoder.encode(userDto.getPassword()),userDto.getRole(),userDto.getFullname());
		return userRepository.save(user);
	}*/

	@Override
	@Transactional
	public void saveUserAndAuditor(UserDto userAndAuditorDto) {
		User user = new User();
		user.setUsername(userAndAuditorDto.getUsername());
		user.setPassword(passwordEncoder.encode(userAndAuditorDto.getPassword()));
		user.setRole(userAndAuditorDto.getRole());
		userRepository.save(user);

		Auditor auditor = new Auditor();
		auditor.setDniAuditor(userAndAuditorDto.getDniAuditor());
		auditor.setNombreAuditor(userAndAuditorDto.getNombreAuditor());
		auditor.setApellidoAuditor(userAndAuditorDto.getApellidoAuditor());
		auditor.setUser(user);
		auditorRepository.save(auditor);
	}

	@Override
	@Transactional
	public void saveUserAndLider(UserLider userLider) {
		User user = new User();
		user.setUsername(userLider.getUsername());
		user.setPassword(passwordEncoder.encode(userLider.getPassword()));
		user.setRole(userLider.getRole());
		userRepository.save(user);

		Lider lider = new Lider();
		lider.setDniLider(userLider.getDniLider());
		lider.setNombreLider(userLider.getNombreLider());
		lider.setApellidoLider(userLider.getApellidoLider());
		lider.setArea(userLider.getArea());
		lider.setUser(user);
		liderRepository.save(lider);
	}

	@Override
	@Transactional
	public void saveUserAndEmpleado(UserEmpleado userEmpleado) {
		User user = new User();
		user.setUsername(userEmpleado.getUsername());
		user.setPassword(passwordEncoder.encode(userEmpleado.getPassword()));
		user.setRole(userEmpleado.getRole());
		userRepository.save(user);

		Empleado empleado = new Empleado();
		empleado.setDniEmpleado(userEmpleado.getDniEmpleado());
		empleado.setNombreEmpleado(userEmpleado.getNombreEmpleado());
		empleado.setApellidoEmpleado(userEmpleado.getApellidoEmpleado());
		empleado.setArea(userEmpleado.getArea());
		empleado.setUser(user);
		empleadoRepository.save(empleado);
	}

	@Override
	public List<User> listarUsers() {
		return userRepository.findAll();
	}

	@Override
	public int ultimoId() {
		return userRepository.ultimoCodigo();
	}

	@Override
	public byte[] exportPdf() throws JRException, FileNotFoundException {
		return userReportGenerator.exportToPdf(userRepository.findAll());
	}

	@Override
	public byte[] exportXls() throws JRException, FileNotFoundException {
		return userReportGenerator.exportToXls(userRepository.findAll());
	}

	@Override
	public User getUserById(Long id) {
		 return userRepository.findById(id).orElse(null);
	}

	/*@Override
	public void saveUser(User user) {
		User u=new User(user.getId(),user.getUsername(),user.getPassword(),user.getRole(),user.getFullname());
		userRepository.save(u);
		
	}*/

	@Override
	public void saveUser(User user) {
	    // Recupera el usuario existente de la base de datos
	    User existingUser = userRepository.findById(user.getId()).orElse(null);

	    // Si es un usuario existente y la contraseña no ha cambiado, mantén la contraseña sin encriptar
	    if (existingUser != null && user.getPassword().equals(existingUser.getPassword())) {
	        user.setPassword(existingUser.getPassword());
	    } else {
	        // Si la contraseña ha cambiado o es un nuevo usuario, encripta la nueva contraseña
	        user.setPassword(passwordEncoder.encode(user.getPassword()));
	    }

	    // Guarda el usuario en la base de datos
	    userRepository.save(user);
	}
	@Override
	public void cambiarContraseña(String username, String password){
		User user = userRepository.findByUsername(username);
		user.setPassword(passwordEncoder.encode(password));
		userRepository.save(user);
	}
	@Override
	public void deleteUser(Long id) {
		userRepository.deleteById(id);
		
	}



}
