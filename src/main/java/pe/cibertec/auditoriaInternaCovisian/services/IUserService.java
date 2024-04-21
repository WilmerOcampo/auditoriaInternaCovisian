package pe.cibertec.auditoriaInternaCovisian.services;



import net.sf.jasperreports.engine.JRException;
import pe.cibertec.auditoriaInternaCovisian.models.dto.UserDto;
import pe.cibertec.auditoriaInternaCovisian.models.dto.UserEmpleado;
import pe.cibertec.auditoriaInternaCovisian.models.dto.UserLider;
import pe.cibertec.auditoriaInternaCovisian.models.bd.User;

import java.io.FileNotFoundException;
import java.util.List;

public interface IUserService {
	public  List<User> listarUsers();
	int ultimoId();
	User getUserById(Long id);
    void saveUser(User user);
    void deleteUser(Long id);
	void cambiarContraseña(String username, String password);
	void saveUserAndEmpleado(UserEmpleado userEmpleado);
	void saveUserAndAuditor(UserDto userDto);
	void saveUserAndLider(UserLider userLider);
	byte[] exportPdf() throws JRException, FileNotFoundException;
    byte[] exportXls() throws JRException, FileNotFoundException;
}
