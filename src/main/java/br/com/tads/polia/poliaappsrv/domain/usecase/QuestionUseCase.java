package br.com.tads.polia.poliaappsrv.domain.usecase;

import br.com.tads.polia.poliaappsrv.domain.entity.Question;
import br.com.tads.polia.poliaappsrv.domain.entity.Weight;
import br.com.tads.polia.poliaappsrv.port.output.IQuestionOutputPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionUseCase {

    private final IQuestionOutputPort outputPort;

    public QuestionUseCase(IQuestionOutputPort outputPort) {
        this.outputPort = outputPort;
    }

    public List<Question> getAllQuestions() {
        return outputPort.getAllQuestions();
    }

    public List<Weight> getAllQuestionWeights() {
        return outputPort.getAllQuestionWeights();
    }
}
