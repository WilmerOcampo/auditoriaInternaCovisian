package pe.cibertec.auditoriaInternaCovisian.models.bd;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "Auditores")
@NoArgsConstructor
public class Auditor {

    @Id
    @Column(name = "dni_auditor")
    private int dniAuditor;


    @Column(name = "nombre_auditor")
    private String nombreAuditor;

    @Column(name = "apellido_auditor")
    private String apellidoAuditor;

    @OneToOne
    @JoinColumn(name = "id")
    private User user;

    public Auditor(int dniAuditor, String nombreAuditor, String apellidoAuditor) {
        this.dniAuditor = dniAuditor;
        this.nombreAuditor = nombreAuditor;
        this.apellidoAuditor = apellidoAuditor;
    }


}
