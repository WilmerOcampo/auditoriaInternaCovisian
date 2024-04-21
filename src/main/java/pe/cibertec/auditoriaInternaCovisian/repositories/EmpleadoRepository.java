package pe.cibertec.auditoriaInternaCovisian.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.cibertec.auditoriaInternaCovisian.models.bd.Empleado;

import java.util.List;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Integer>{

    // Es para poder obtener el empleado por su nombre de usuario GENESIS DESPOUX
    @Query("SELECT emp FROM Empleado emp WHERE emp.user.username = :username")
    Empleado findByUsername(@Param("username") String username);

    @Query(value = "SELECT * FROM Empleados WHERE id = :iduser", nativeQuery = true)
    Empleado findByIdUser(@Param("iduser") Long iduser);

    List<Empleado> findByArea(String area);

    /*PUEDE SERVIR
    @Query(value = "SELECT * FROM Empleados WHERE area = :areaEmpleado", nativeQuery = true)
    List<Empleado> listaEmpleadoPorAreas(@Param("areaEmpleado") String area);*/

}
