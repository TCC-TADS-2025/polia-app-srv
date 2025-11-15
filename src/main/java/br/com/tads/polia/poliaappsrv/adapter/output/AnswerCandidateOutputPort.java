package br.com.tads.polia.poliaappsrv.adapter.output;

import br.com.tads.polia.poliaappsrv.adapter.output.bd.AnswerCandidateEntity;
import br.com.tads.polia.poliaappsrv.adapter.output.bd.AnswerEntity;
import br.com.tads.polia.poliaappsrv.adapter.output.mapper.AnswerCandidateOutputMapper;
import br.com.tads.polia.poliaappsrv.domain.entity.AnswerCandidate;
import br.com.tads.polia.poliaappsrv.port.output.IAnswerCandidateOutputPort;
import br.com.tads.polia.poliaappsrv.port.output.bd.repository.AnswerCandidateRepository;
import br.com.tads.polia.poliaappsrv.port.output.bd.repository.CandidateRepository;
import br.com.tads.polia.poliaappsrv.port.output.bd.repository.QuestionAnswerRepository;
import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AnswerCandidateOutputPort implements IAnswerCandidateOutputPort {

    @Autowired
    private AnswerCandidateOutputMapper MAPPER;

    private final AnswerCandidateRepository answerCandidateRepository;
    private final CandidateRepository candidateRepository;
    private final QuestionAnswerRepository questionAnswerRepository;

    public AnswerCandidateOutputPort(AnswerCandidateRepository answerCandidateRepository, CandidateRepository candidateRepository, QuestionAnswerRepository questionAnswerRepository) {
        this.answerCandidateRepository = answerCandidateRepository;
        this.candidateRepository = candidateRepository;
        this.questionAnswerRepository = questionAnswerRepository;
    }


    @Override
    public List<AnswerCandidate> createAnswers(List<AnswerCandidate> answers) {
        List<AnswerCandidateEntity> savedAnswers = new ArrayList<>();

        for (AnswerCandidate answer : answers) {
            UUID candidateId = answer.getCandidateId();
            UUID questionId = answer.getQuestionId();

            Optional<AnswerCandidateEntity> existing = answerCandidateRepository
                    .findByCandidateId_IdAndQuestionAnswer_Question_Id(candidateId, questionId);

            AnswerCandidateEntity entity = MAPPER.answerCandidateToAnswerCandidateEntity(answer);

            if (existing.isPresent()) {
                AnswerCandidateEntity existingEntity = existing.get();
                existingEntity.setAnswerWeight(entity.getAnswerWeight());
                existingEntity.setUpdatedAt(LocalDateTime.now());
                savedAnswers.add(answerCandidateRepository.save(existingEntity));
            } else {
                if (candidateId != null) {
                    var candidateRef = candidateRepository.findById(candidateId)
                                    .orElseThrow(() -> new EntityNotFoundException("Candidato com o ID " + candidateId + " não encontrado."));
                    entity.setCandidateId(candidateRef);
                }

                if (questionId != null) {
                    var qaRef = questionAnswerRepository.findByQuestion_Id(questionId)
                                                            .orElseThrow(() -> new EntityNotFoundException("Questão com o ID " + questionId + " não encontrada."));
                    entity.setQuestionAnswer(qaRef);
                }

                savedAnswers.add(answerCandidateRepository.save(entity));
            }
        }

        return MAPPER.listAnswerCandidateEntityToAnswerCandidate(savedAnswers);
    }

    @Override
    public List<AnswerCandidate> getAnswerById(UUID id) {
        List<AnswerCandidateEntity> entidades = answerCandidateRepository.findByCandidateId(id);
        if (entidades == null || entidades.isEmpty()) {
            return null;
        }
        return MAPPER.listAnswerCandidateEntityToAnswerCandidate(entidades);
    }
}
