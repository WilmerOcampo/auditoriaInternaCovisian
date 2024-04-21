package pe.cibertec.auditoriaInternaCovisian.models.bd;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "Empleados")
@NoArgsConstructor
public class Empleado {

    @Id
    @Column(name = "dni_empleado")
    private int dniEmpleado;
    @Column(name = "area")
    private String area;
    @Column(name = "nombre_empleado")
    private String nombreEmpleado;
    @Column(name = "apellido_empleado")
    private String apellidoEmpleado;

    @OneToOne
    @JoinColumn(name = "id")
    private User user;

    public Empleado(int dniEmpleado, String area, String nombreEmpleado, String apellidoEmpleado) {
        this.dniEmpleado = dniEmpleado;
        this.area = area;
        this.nombreEmpleado = nombreEmpleado;
        this.apellidoEmpleado = apellidoEmpleado;
    }

}
