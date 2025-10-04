package br.com.tads.polia.poliaappsrv.port.output;

import br.com.tads.polia.poliaappsrv.domain.entity.Question;

import java.util.List;

public interface IQuestionOutputPort {

    List<Question> getAllQuestions();
}
