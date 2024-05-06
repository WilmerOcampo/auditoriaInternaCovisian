package pe.cibertec.auditoriaInternaCovisian.models.bd.pk;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class AsistenciaId implements Serializable {
    @Column(name = "id_capacitacion")
    private Integer idCapacitacion;
    @Column(name = "dni_empleado")
    private Integer dniEmpleado;
}
