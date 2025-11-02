package br.com.tads.polia.poliaappsrv.domain.usecase;


import br.com.tads.polia.poliaappsrv.domain.entity.AnswerCandidate;
import br.com.tads.polia.poliaappsrv.port.output.IAnswerCandidateOutputPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerCandidateUseCase {

    private final IAnswerCandidateOutputPort answerCandidateOutputPort;
    public AnswerCandidateUseCase(IAnswerCandidateOutputPort answerCandidateOutputPort) {
        this.answerCandidateOutputPort = answerCandidateOutputPort;
    }

    public List<AnswerCandidate> createAnswers(List<AnswerCandidate> answers){
        return answerCandidateOutputPort.createAnswers(answers);
    }
}
