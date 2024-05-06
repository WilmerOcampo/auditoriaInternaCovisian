package pe.cibertec.auditoriaInternaCovisian.services;

import pe.cibertec.auditoriaInternaCovisian.models.bd.Feedback;
import pe.cibertec.auditoriaInternaCovisian.models.bd.Memorandum;

public interface IFeedbackService {
    void saveFeedbackAndMemorandum(Feedback feedback, Memorandum memorandum);
}
