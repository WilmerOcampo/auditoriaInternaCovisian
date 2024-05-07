package pe.cibertec.auditoriaInternaCovisian.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pe.cibertec.auditoriaInternaCovisian.models.bd.Asistencia;

import java.util.List;

public interface IAsistenciaRepository extends JpaRepository<Asistencia, Integer> {
    @Query("SELECT a FROM Asistencia a JOIN a.capacitacion c JOIN a.empleado emp WHERE emp.dniEmpleado = :dni")
    List<Asistencia> findByEmpleado(@Param("dni") Integer dni);
}
