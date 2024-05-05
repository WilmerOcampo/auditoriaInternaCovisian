package pe.cibertec.auditoriaInternaCovisian.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.cibertec.auditoriaInternaCovisian.models.bd.Feedback;
import pe.cibertec.auditoriaInternaCovisian.repositories.IFeedbackRepository;

@Service
@RequiredArgsConstructor
public class FeedbackServiceImpl implements IFeedbackService {
    private final IFeedbackRepository feedbackRepository;

    @Override
    public void save(Feedback feedback) {
        feedbackRepository.save(feedback);
    }
}
