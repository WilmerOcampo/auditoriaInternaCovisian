package pe.cibertec.auditoriaInternaCovisian.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.cibertec.auditoriaInternaCovisian.models.bd.Feedback;

public interface IFeedbackRepository extends JpaRepository<Feedback, Integer> {
}
