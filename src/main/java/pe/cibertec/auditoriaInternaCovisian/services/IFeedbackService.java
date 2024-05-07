package pe.cibertec.auditoriaInternaCovisian.services;

import org.springframework.data.repository.query.Param;
import pe.cibertec.auditoriaInternaCovisian.models.bd.Feedback;
import pe.cibertec.auditoriaInternaCovisian.models.bd.Memorandum;

import java.util.List;

public interface IFeedbackService {
    void saveFeedbackAndMemorandum(Feedback feedback, Memorandum memorandum);
    List<Memorandum> findByEmpleado(Integer dni);
}
