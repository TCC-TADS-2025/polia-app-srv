package br.com.tads.polia.poliaappsrv.port.output;

import br.com.tads.polia.poliaappsrv.domain.entity.AnswerCandidate;

import java.util.List;
import java.util.UUID;

public interface IAnswerCandidateOutputPort {

    List<AnswerCandidate> createAnswers(List<AnswerCandidate> answers);
    List<AnswerCandidate> getAnswerById(UUID id);
    void deleteByCandidateId(UUID candidateId);
}
