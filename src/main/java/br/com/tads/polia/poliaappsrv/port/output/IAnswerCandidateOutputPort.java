package br.com.tads.polia.poliaappsrv.port.output;

import br.com.tads.polia.poliaappsrv.domain.entity.AnswerCandidate;

import java.util.List;

public interface IAnswerCandidateOutputPort {

    List<AnswerCandidate> createAnswers(List<AnswerCandidate> answers);
}
