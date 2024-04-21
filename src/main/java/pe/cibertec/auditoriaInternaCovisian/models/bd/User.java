package pe.cibertec.auditoriaInternaCovisian.models.bd;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="users",uniqueConstraints = @UniqueConstraint(columnNames = "username"))
@Data
@NoArgsConstructor
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String username;
	private String password;
	private String role;

	public User(String username, String password, String role ) {
		this.username = username;
		this.password = password;
		this.role = role;
	}

	public User(Long id, String username, String role) {
		super();
		this.id = id;
		this.username = username;
		this.role = role;
	}

	public User(Long id, String username, String password, String role) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.role = role;
	}
	
}
