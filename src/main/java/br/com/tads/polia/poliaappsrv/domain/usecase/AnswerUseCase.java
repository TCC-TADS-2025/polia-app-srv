package br.com.tads.polia.poliaappsrv.domain.usecase;

import br.com.tads.polia.poliaappsrv.domain.entity.Answer;
import br.com.tads.polia.poliaappsrv.port.output.IAnswerOutputPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerUseCase {

    private final IAnswerOutputPort answerOutputPort;
    public AnswerUseCase(IAnswerOutputPort answerOutputPort) {
        this.answerOutputPort = answerOutputPort;
    }

    public List<Answer> createAnswers(List<Answer> answers){
        return answerOutputPort.createAnswers(answers);
    }

    public List<Answer> getAnswerById(String id) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("ID cannot be null or empty");
        }
        return answerOutputPort.getAnswerById(id);
    }
}
