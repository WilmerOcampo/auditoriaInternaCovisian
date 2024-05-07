package pe.cibertec.auditoriaInternaCovisian.models.bd;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "Llamadas")
@NoArgsConstructor
public class Llamada {
    @Id
    @Column(name = "numero_orden")
    private int numeroOrden;
    private String tipo;
    private String area;
    private String subarea;
    private LocalDateTime fechahora;
    @Column(name = "estado", columnDefinition = "ENUM('Cerrado', 'Abierto')")
    private String estado;
    @Column(name = "estado_auditoria", columnDefinition = "TINYINT(1) DEFAULT 0")
    private boolean estadoAuditoria;
    @Column(columnDefinition = "TEXT")
    private String observaciones;
    @Column(name = "url_llamada", columnDefinition = "VARCHAR(500)")
    private String url;
    @OneToOne(mappedBy = "llamada", cascade = CascadeType.ALL)
    private Evaluacion evaluacion;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dni_empleado")
    private Empleado empleado;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dni_cliente")
    private Cliente cliente;

    public Llamada(int numeroOrden, String tipo, String area, String subarea, String estado , String observaciones, String url, Empleado empleado, Cliente cliente) {
        this.numeroOrden = numeroOrden;
        this.tipo = tipo;
        this.area = area;
        this.subarea = subarea;
        this.estado = estado;
        this.observaciones = observaciones;
        this.url = url;
        this.empleado = empleado;
        this.cliente = cliente;
    }

}
