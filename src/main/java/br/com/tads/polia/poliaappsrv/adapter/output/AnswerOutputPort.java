package br.com.tads.polia.poliaappsrv.adapter.output;

import br.com.tads.polia.poliaappsrv.adapter.output.bd.AnswerEntity;
import br.com.tads.polia.poliaappsrv.adapter.output.mapper.AnswerOutputMapper;
import br.com.tads.polia.poliaappsrv.domain.entity.Answer;
import br.com.tads.polia.poliaappsrv.port.output.IAnswerOutputPort;
import br.com.tads.polia.poliaappsrv.port.output.bd.repository.AnswerRepository;
import br.com.tads.polia.poliaappsrv.port.output.bd.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import br.com.tads.polia.poliaappsrv.port.output.bd.repository.QuestionAnswerRepository;
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
    private final UserRepository userRepository;
    private final QuestionAnswerRepository questionAnswerRepository;

    public AnswerOutputPort(AnswerRepository answerRepository,
                            UserRepository userRepository,
                            QuestionAnswerRepository questionAnswerRepository) {
        this.answerRepository = answerRepository;
        this.userRepository = userRepository;
        this.questionAnswerRepository = questionAnswerRepository;
    }

    @Override
    public List<Answer> createAnswers(List<Answer> answers) {
        List<AnswerEntity> savedAnswers = new ArrayList<>();

        for (Answer answer : answers) {
            String userId = answer.getUserId();
            UUID questionId = answer.getQuestionId();

            Optional<AnswerEntity> existing = answerRepository
                    .findByUserId_IdAndQuestionAnswer_Question_Id(userId, questionId);

            AnswerEntity entity = MAPPER.answerToAnswerEntity(answer);

            if (existing.isPresent()) {
                AnswerEntity existingEntity = existing.get();
                existingEntity.setAnswerWeight(entity.getAnswerWeight());
                existingEntity.setUpdatedAt(LocalDateTime.now());
                savedAnswers.add(answerRepository.save(existingEntity));
            } else {
                if (userId != null) {
                    var userRef = userRepository.findById(userId)
                                    .orElseThrow(() -> new EntityNotFoundException("Usuário com o ID " + userId + " não encontrado."));
                    entity.setUserId(userRef);
                }

                if (questionId != null) {
                    var qaRef = questionAnswerRepository.findByQuestion_Id(questionId)
                                                            .orElseThrow(() -> new EntityNotFoundException("Questão com o ID " + questionId + " não encontrada."));
                    entity.setQuestionAnswer(qaRef);
                }

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
