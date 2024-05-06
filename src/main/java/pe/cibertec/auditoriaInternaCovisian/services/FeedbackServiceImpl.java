package pe.cibertec.auditoriaInternaCovisian.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.cibertec.auditoriaInternaCovisian.models.bd.*;
import pe.cibertec.auditoriaInternaCovisian.repositories.*;

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
