package pe.cibertec.auditoriaInternaCovisian.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.cibertec.auditoriaInternaCovisian.models.bd.Empleado;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Integer>{

    // Es para poder obtener el empleado por su nombre de usuario GENESIS DESPOUX
    @Query("SELECT emp FROM Empleado emp WHERE emp.user.username = :username")
    Empleado findByUsername(@Param("username") String username);

    @Query(value = "SELECT * FROM Empleados WHERE id = :iduser", nativeQuery = true)
    Empleado findByIdUser(@Param("iduser") Long iduser);


    /*Metodo para obtener UN EMPLEADO por ID_EVALUACION
        Lo uso en mi AJAX de frmlistaevaluaciones de la vista lider (HUGO)*/
    @Query(value = "Select e.* from Empleados e inner join Evaluaciones ev on e.dni_empleado=ev.dni_empleado where ev.id_evaluacion= :idEvaluacion",nativeQuery = true)
    Empleado empleadoPorIdEvaluacion(@Param("idEvaluacion") int idEvaluacion);



    /*PUEDE SERVIR
    @Query(value = "SELECT * FROM Empleados WHERE area = :areaEmpleado", nativeQuery = true)
    List<Empleado> listaEmpleadoPorAreas(@Param("areaEmpleado") String area);*/

}
