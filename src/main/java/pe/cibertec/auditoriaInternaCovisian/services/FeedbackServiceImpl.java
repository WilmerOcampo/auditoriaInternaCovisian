package pe.cibertec.auditoriaInternaCovisian.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.cibertec.auditoriaInternaCovisian.models.bd.Feedback;
import pe.cibertec.auditoriaInternaCovisian.models.bd.Memorandum;
import pe.cibertec.auditoriaInternaCovisian.repositories.IFeedbackRepository;
import pe.cibertec.auditoriaInternaCovisian.repositories.IMemorandumRepository;

@Service
@RequiredArgsConstructor
public class FeedbackServiceImpl implements IFeedbackService {
    private final IFeedbackRepository feedbackRepository;
    private final IMemorandumRepository memorandumRepository;

    @Override
    @Transactional
    public void saveFeedbackAndMemorandum(Feedback feedback, Memorandum memorandum) {
        Feedback savedFeedback = feedbackRepository.save(feedback);
        memorandum.setFeedback(savedFeedback);
        memorandumRepository.save(memorandum);
    }
}
