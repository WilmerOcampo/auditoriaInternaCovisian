package pe.cibertec.auditoriaInternaCovisian.models.bd;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "feedbacks")
@NoArgsConstructor
@AllArgsConstructor
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_feedback")
    private Integer idFeedback;
    private String motivo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dni_empleado")
    private Empleado empleado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dni_lider")
    private Lider lider;
}
