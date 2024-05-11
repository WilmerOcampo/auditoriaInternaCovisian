package pe.cibertec.auditoriaInternaCovisian.controllers.backoffice;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pe.cibertec.auditoriaInternaCovisian.models.bd.Feedback;
import pe.cibertec.auditoriaInternaCovisian.models.bd.Memorandum;
import pe.cibertec.auditoriaInternaCovisian.services.IFeedbackService;

import java.util.List;

@Controller
@RequestMapping("/feedback")
@RequiredArgsConstructor
public class FeedbackController {
    private final IFeedbackService feedbackService;

    @GetMapping("/{dni}")
    public String feedbacksPorDni(@PathVariable("dni") Integer dni, Model model){
        List<Memorandum> memorandums = feedbackService.findByEmpleado(dni);
        model.addAttribute("memorandums", memorandums);
        return "backoffice/feedback/feedbacks";
    }
    @GetMapping("/a/{area}")
    public String feedbacksPorArea(@PathVariable("area") String area, Model model){
        List<Memorandum> memorandums = feedbackService.findByArea(area);
        model.addAttribute("memorandums", memorandums);
        return "backoffice/lider/feedbacks";
    }
}
