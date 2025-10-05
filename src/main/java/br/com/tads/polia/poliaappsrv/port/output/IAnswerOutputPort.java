package br.com.tads.polia.poliaappsrv.port.output;

import br.com.tads.polia.poliaappsrv.domain.entity.Answer;

import java.util.List;

public interface IAnswerOutputPort {

    List<Answer> createAnswers(List<Answer> answers);
}
