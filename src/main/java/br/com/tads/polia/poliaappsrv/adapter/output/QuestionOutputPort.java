package br.com.tads.polia.poliaappsrv.adapter.output;

import br.com.tads.polia.poliaappsrv.adapter.output.bd.QuestionAnswerEntity;
import br.com.tads.polia.poliaappsrv.adapter.output.mapper.QuestionOutputMapper;
import br.com.tads.polia.poliaappsrv.domain.entity.Question;
import br.com.tads.polia.poliaappsrv.domain.entity.Weight;
import br.com.tads.polia.poliaappsrv.port.output.IQuestionOutputPort;
import br.com.tads.polia.poliaappsrv.port.output.bd.repository.QuestionAnswerRepository;
import br.com.tads.polia.poliaappsrv.port.output.bd.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class QuestionOutputPort implements IQuestionOutputPort {

    @Autowired
    private QuestionOutputMapper MAPPER;

    private final QuestionRepository repository;
    private final QuestionAnswerRepository questionAnswerEntity;

    public QuestionOutputPort(QuestionRepository repository, QuestionAnswerRepository questionAnswerEntity) {
        this.repository = repository;
        this.questionAnswerEntity = questionAnswerEntity;
    }

    @Override
    public List<Question> getAllQuestions() {
        var result = MAPPER.listQuestionEntityToQuestion(repository.findAll());
        if(result == null || result.isEmpty()) {
            return null;
        }
        return result;
    }

    @Override
    public List<Weight> getAllQuestionWeights() {
        var result = MAPPER.listWeightEntityToWeight(questionAnswerEntity.findAll());
        if(result == null || result.isEmpty()) {
            return null;
        }
        return result;
    }
}
