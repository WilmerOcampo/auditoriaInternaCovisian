package pe.cibertec.auditoriaInternaCovisian.models.bd;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "Cliente")
@NoArgsConstructor
public class Cliente {

    @Id
    @Column(name = "dni_cliente")
    private int dniCliente;
    @Column(name = "nombre_cliente")
    private String nombreCliente;
    @Column(name = "apellido_cliente")
    private  String apellidoCliente;
    @Column(name = "numero_cliente")
    private int numeroCliente;

}
