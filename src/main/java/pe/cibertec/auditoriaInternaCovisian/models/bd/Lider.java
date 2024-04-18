package pe.cibertec.auditoriaInternaCovisian.models.bd;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "lider")
public class Lider {
    @Id
    @Column(name = "dni_lider")
    private int dniLider;
    @Column(name = "nombre_lider")
    private String nombreLider;
    @Column(name = "apellido_lider")
    private String apellidoLider;
    @Column(name = "area")
    private String area;


    @OneToOne
    @JoinColumn(name = "id")
    private User user;

    public Lider(){

    }
}
