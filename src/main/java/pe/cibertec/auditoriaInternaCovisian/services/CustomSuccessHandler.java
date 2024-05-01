package pe.cibertec.auditoriaInternaCovisian.services;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class CustomSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		var authourities= authentication.getAuthorities();
		var roles = authourities.stream().map(r->r.getAuthority()).findFirst();
		
		
		if(roles.orElse("").equals("AUDITOR")) {
			response.sendRedirect("/home/inicio-page");
		}else if(roles.orElse("").equals("LIDER")) {
			response.sendRedirect("/lider/inicio-page");
		}else if(roles.orElse("").equals("EMPLEADO")) {
			response.sendRedirect("/home/inicio-page");
		}else if(roles.orElse("").equals("ADMIN")){
			response.sendRedirect("/home/admin-inicio-page");
		}
		else {
			response.sendRedirect("/error");
		}
		
	}

}
