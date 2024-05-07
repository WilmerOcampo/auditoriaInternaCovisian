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
    @Query("SELECT m FROM Memorandum m JOIN m.feedback f JOIN f.empleado emp WHERE emp.dniEmpleado = :dni")
    List<Memorandum> findByEmpleado(@Param("dni") Integer dni);
}
