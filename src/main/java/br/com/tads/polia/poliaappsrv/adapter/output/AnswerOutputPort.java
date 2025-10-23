package br.com.tads.polia.poliaappsrv.adapter.output;

import br.com.tads.polia.poliaappsrv.adapter.output.bd.AnswerEntity;
import br.com.tads.polia.poliaappsrv.adapter.output.mapper.AnswerOutputMapper;
import br.com.tads.polia.poliaappsrv.domain.entity.Answer;
import br.com.tads.polia.poliaappsrv.port.output.IAnswerOutputPort;
import br.com.tads.polia.poliaappsrv.port.output.bd.repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
        List<AnswerEntity> savedAnswers = new ArrayList<>();

        for (Answer answer : answers) {
            String userId = answer.getUserId();
            UUID questionId = answer.getQuestionId();

            Optional<AnswerEntity> existing = answerRepository
                    .findByUserId_IdAndQuestionAnswer_IdQuestionWeight(userId, questionId);

            AnswerEntity entity = MAPPER.answerToAnswerEntity(answer);

            if (existing.isPresent()) {
                AnswerEntity existingEntity = existing.get();
                existingEntity.setAnswerWeight(entity.getAnswerWeight());
                existingEntity.setUpdatedAt(LocalDateTime.now());
                savedAnswers.add(answerRepository.save(existingEntity));
            } else {
                savedAnswers.add(answerRepository.save(entity));
            }
        }

        return MAPPER.listAnswerEntityToAnswer(savedAnswers);
    }

    @Override
    public List<Answer> getAnswerById(String id) {
        List<AnswerEntity> entidades = answerRepository.findByUserId(id);
        if (entidades == null || entidades.isEmpty()) {
            return null;
        }
        return MAPPER.listAnswerEntityToAnswer(entidades);
    }
}
