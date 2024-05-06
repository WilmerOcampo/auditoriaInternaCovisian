package pe.cibertec.auditoriaInternaCovisian.models.bd;

import jakarta.persistence.*;
import lombok.*;
import pe.cibertec.auditoriaInternaCovisian.models.bd.pk.AsistenciaId;

import java.time.LocalDate;

@Entity
@Table(name = "capacitaciones")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Asistencia {
    @EmbeddedId
    private AsistenciaId idAsistencia;
    private String modalidad;
    private LocalDate fecha;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_capacitacion", insertable = false, updatable = false)
    private Capacitacion capacitacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dni_empleado", insertable = false, updatable = false)
    private Empleado empleado;
}
