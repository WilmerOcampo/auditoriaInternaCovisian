package pe.cibertec.auditoriaInternaCovisian.models.bd;

import jakarta.persistence.*;
import lombok.*;
import pe.cibertec.auditoriaInternaCovisian.models.bd.pk.AsistenciaId;

import java.time.LocalDateTime;

@Entity
@Table(name = "asistencias")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Asistencia {
    @EmbeddedId
    private AsistenciaId idAsistencia;
    private String modalidad;
    private LocalDateTime fecha;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_capacitacion", insertable = false, updatable = false)
    private Capacitacion capacitacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dni_empleado", insertable = false, updatable = false)
    private Empleado empleado;
}
