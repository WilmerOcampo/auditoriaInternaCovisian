package pe.cibertec.auditoriaInternaCovisian.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.cibertec.auditoriaInternaCovisian.models.bd.Llamada;

import java.util.List;

@Repository
public interface LlamadasRepository extends JpaRepository<Llamada, Integer>{
    @Query(value = "SELECT * FROM Llamadas WHERE dni_empleado = :dniEmpleado", nativeQuery = true)
    List<Llamada> findByDniEmpleado(@Param("dniEmpleado") int dniEmpleado);

    @Query(value = "SELECT * FROM Llamadas WHERE numero_orden = :numeroOrden", nativeQuery = true)
    Llamada findByOrden(@Param("numeroOrden") int numeroOrden);


    /*Metodo para obtener UNA LLAMADA por ID_EVALUACION
        Lo uso en mi AJAX de frmlistaevaluaciones de la vista lider (HUGO)*/
    @Query(value = "Select l.* from Llamadas l inner join Evaluaciones ev on l.numero_orden =ev.numero_orden  where ev.id_evaluacion = :idEvaluacion",nativeQuery = true)
    Llamada llamadaPorIdEvaluacion(@Param("idEvaluacion") int idEvaluacion);

}
