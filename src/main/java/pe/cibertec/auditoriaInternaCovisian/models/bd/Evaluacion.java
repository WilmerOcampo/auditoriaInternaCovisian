package pe.cibertec.auditoriaInternaCovisian.models.bd;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "Evaluaciones")
@NoArgsConstructor
public class Evaluacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_evaluacion")
    private int idEvaluacion;
    private int nota;
    @Column(name = "fechahora")
    private LocalDateTime fechahora;
    @Column(name = "observaciones_evaluacion",columnDefinition = "TEXT")
    private String observacionesEvaluacion;
    @Column(name = "estado_lider", columnDefinition = "TINYINT(1) DEFAULT 0")
    private boolean estadoLider;
    @Column(name = "estado_firma", columnDefinition = "TINYINT(1) DEFAULT 0")
    private boolean estadoFirma;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "dni_empleado")
    private Empleado empleado;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dni_auditor")
    private Auditor auditor;
    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "numero_orden")
    private Llamada llamada;

    public Evaluacion(int idEvaluacion, int nota, Empleado empleado, Auditor auditor) {
        this.idEvaluacion = idEvaluacion;
        this.nota = nota;
        this.empleado = empleado;
        this.auditor = auditor;
    }

    public Evaluacion(int idEvaluacion, int nota, String observacionesEvaluacion, Empleado empleado, Auditor auditor, Llamada llamada) {
        this.idEvaluacion = idEvaluacion;
        this.nota = nota;
        this.observacionesEvaluacion = observacionesEvaluacion;
        this.empleado = empleado;
        this.auditor = auditor;
        this.llamada = llamada;
    }
}
