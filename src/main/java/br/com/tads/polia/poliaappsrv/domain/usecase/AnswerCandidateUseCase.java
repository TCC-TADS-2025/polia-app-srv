package br.com.tads.polia.poliaappsrv.domain.usecase;


import br.com.tads.polia.poliaappsrv.domain.entity.AnswerCandidate;
import br.com.tads.polia.poliaappsrv.port.output.IAnswerCandidateOutputPort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AnswerCandidateUseCase {

    private final IAnswerCandidateOutputPort answerCandidateOutputPort;
    public AnswerCandidateUseCase(IAnswerCandidateOutputPort answerCandidateOutputPort) {
        this.answerCandidateOutputPort = answerCandidateOutputPort;
    }

    public List<AnswerCandidate> createAnswers(List<AnswerCandidate> answers){
        return answerCandidateOutputPort.createAnswers(answers);
    }

    public List<AnswerCandidate> getAnswerById(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null or empty");
        }
        return answerCandidateOutputPort.getAnswerById(id);
    }
}
