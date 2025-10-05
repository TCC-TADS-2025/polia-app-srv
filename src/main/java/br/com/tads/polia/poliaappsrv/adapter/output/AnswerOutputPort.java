package br.com.tads.polia.poliaappsrv.adapter.output;

import br.com.tads.polia.poliaappsrv.adapter.output.bd.AnswerEntity;
import br.com.tads.polia.poliaappsrv.adapter.output.mapper.AnswerOutputMapper;
import br.com.tads.polia.poliaappsrv.domain.entity.Answer;
import br.com.tads.polia.poliaappsrv.port.output.IAnswerOutputPort;
import br.com.tads.polia.poliaappsrv.port.output.bd.repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerOutputPort implements IAnswerOutputPort {
    @Autowired
    private AnswerOutputMapper MAPPER;

    private final AnswerRepository answerRepository;

    public AnswerOutputPort(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    @Override
    public List<Answer> createAnswers(List<Answer> answers) {
        List<AnswerEntity> result = MAPPER.listAnswerToAnswerEntity(answers);
        List<AnswerEntity> createdAnswers = answerRepository.saveAll(result);
        return MAPPER.listAnswerEntityToAnswer(createdAnswers);
    }
}
