package pe.cibertec.auditoriaInternaCovisian.configurations;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import pe.cibertec.auditoriaInternaCovisian.services.CustomSuccessHandler;
import pe.cibertec.auditoriaInternaCovisian.services.CustomUserDetailService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	CustomSuccessHandler customSuccessHandler;

	@Autowired
	CustomUserDetailService customUserDetailService;

	
	@Bean
	public static PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(c -> c.disable())
		.authorizeHttpRequests(request -> request.requestMatchers("/auditar") //Permisos solo para admin (manipular a sus gustos)
				.hasAuthority("AUDITOR").requestMatchers("/home/inicio-page").hasAnyAuthority("EMPLEADO","AUDITOR")
				.requestMatchers("/css/**").permitAll()
				.anyRequest().authenticated())
		
		.formLogin(form -> form.loginPage("/home/login").loginProcessingUrl("/login")
				.successHandler(customSuccessHandler).permitAll())
		
		.logout(form -> form.logoutSuccessUrl("/home/login").invalidateHttpSession(true)).authenticationProvider(authenticationProvider());
		/*invalidateHttpSession(true).clearAuthentication(true)
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/login?logout").permitAll()*/
		return http.build();
	}

	@Bean
	public AuthenticationProvider authenticationProvider(){
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(customUserDetailService);
		daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
		return daoAuthenticationProvider;
	}

	@Autowired
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserDetailService).passwordEncoder(passwordEncoder());
	}
}
