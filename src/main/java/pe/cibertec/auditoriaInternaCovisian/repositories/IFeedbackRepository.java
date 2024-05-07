package pe.cibertec.auditoriaInternaCovisian.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pe.cibertec.auditoriaInternaCovisian.models.bd.Empleado;
import pe.cibertec.auditoriaInternaCovisian.models.bd.Feedback;
import pe.cibertec.auditoriaInternaCovisian.models.bd.Memorandum;

import java.util.List;
import java.util.Optional;

public interface IFeedbackRepository extends JpaRepository<Feedback, Integer> {
    //@Query(value = "SELECT f.id_feedback, f.motivo, emp.nombre_empleado, emp.apellido_empleado, emp.area, m.asunto, m.cuerpo, m.fecha FROM Feedbacks f JOIN Empleados emp ON f.dni_empleado = emp.dni_empleado JOIN Memorandums m ON m.id_feedback = f.id_feedback WHERE emp.dni_empleado=:dni", nativeQuery = true)
    @Query("SELECT m FROM Memorandum m JOIN m.feedback f JOIN f.empleado emp WHERE emp.dniEmpleado = :dni")
    List<Memorandum> findByEmpleado(@Param("dni") Integer dni);
}
