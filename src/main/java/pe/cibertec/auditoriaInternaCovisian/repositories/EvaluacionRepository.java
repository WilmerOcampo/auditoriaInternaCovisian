package pe.cibertec.auditoriaInternaCovisian.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.cibertec.auditoriaInternaCovisian.models.bd.Empleado;
import pe.cibertec.auditoriaInternaCovisian.models.bd.Evaluacion;

import java.util.List;

@Repository
public interface EvaluacionRepository extends JpaRepository<Evaluacion, Integer>{



    //METODOS GENESIS DESPOUX
    List<Evaluacion> findByEmpleado(Empleado empleado);
    @Query("SELECT e FROM Evaluacion e WHERE e.llamada.numeroOrden = ?1")
    Evaluacion findByNumeroOrden(int numeroOrden);

    //METODO WILMER
    @Query(value = "SELECT * FROM Evaluaciones WHERE numero_orden = :numeroOrden", nativeQuery = true)
    Evaluacion findByOrden(@Param("numeroOrden") int numeroOrden);

    //Obteniendo Lista de Evaluaciones de distintos empleados por su area
    @Query("SELECT e FROM Evaluacion e JOIN e.empleado em WHERE em.area = :areaEmpleado")
    List<Evaluacion> listaEvaluacionesPorArea(@Param("areaEmpleado") String area);

    /*Metodo para obtener UNA Evaluacion por ID_EVALUACION
        Lo uso en mi AJAX de frmlistaevaluaciones de la carpeta lider*/
    @Query(value="SELECT * FROM Evaluaciones WHERE id_evaluacion = :idEvaluacion",nativeQuery = true)
    Evaluacion obtenerEvaluacionPorId(@Param("idEvaluacion") int id);

    //METODOS PARA CHART JS (ANALISIS DE DATOS MEDIANTE JS) HUGO
    //VER AMBOS 2 PRIMEROS
    @Query(value = "select count(*) from Evaluaciones e inner join empleados em on e.dni_empleado = em.dni_empleado where em.area =:area AND e.estado_lider= 1" ,nativeQuery = true)
    int cantidadEvaluacionesVistasPorLider(@Param("area") String area);

    @Query(value = "select count(*) from Evaluaciones e inner join empleados em on e.dni_empleado = em.dni_empleado where em.area =:area AND e.estado_lider= 0" ,nativeQuery = true)
    int cantidadEvaluacionesNoVistasPorLider(@Param("area") String area);

    @Query(value = "select cast(avg(e.nota) as decimal(10,2)) from Evaluaciones e inner join empleados em on e.dni_empleado = em.dni_empleado where em.area =:area",nativeQuery = true)
    public Double promedioNotasPorArea(@Param("area") String area);

}
