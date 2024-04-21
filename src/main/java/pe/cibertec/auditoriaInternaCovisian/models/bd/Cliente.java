package pe.cibertec.auditoriaInternaCovisian.models.bd;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

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
