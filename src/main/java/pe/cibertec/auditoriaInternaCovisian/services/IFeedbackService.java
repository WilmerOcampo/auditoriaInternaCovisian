package pe.cibertec.auditoriaInternaCovisian.services;

import pe.cibertec.auditoriaInternaCovisian.models.bd.Feedback;

import java.util.Optional;

public interface IFeedbackService {
    void save(Feedback feedback);
    Optional<Feedback> findById(Integer id);
}
