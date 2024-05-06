package pe.cibertec.auditoriaInternaCovisian.models.bd;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "memorandums")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Memorandum {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_memorandum")
    private Integer idMemorandum;
    private String asunto;
    private String cuerpo;
    private LocalDateTime fecha;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_feedback")
    private Feedback feedback;
}
