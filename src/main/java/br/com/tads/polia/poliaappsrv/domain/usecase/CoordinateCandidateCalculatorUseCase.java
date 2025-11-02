package br.com.tads.polia.poliaappsrv.domain.usecase;

import br.com.tads.polia.poliaappsrv.adapter.output.bd.AnswerCandidateEntity;
import br.com.tads.polia.poliaappsrv.adapter.output.bd.CandidateEntity;
import br.com.tads.polia.poliaappsrv.adapter.output.bd.QuestionAnswerEntity;
import br.com.tads.polia.poliaappsrv.port.output.bd.repository.AnswerCandidateRepository;
import br.com.tads.polia.poliaappsrv.port.output.bd.repository.CandidateRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CoordinateCandidateCalculatorUseCase {

    private final AnswerCandidateRepository answerCandidateRepository;
    private final CandidateRepository candidateRepository;

    // Limites fixos para normalização
    private static final int MIN_X = -36;
    private static final int MAX_X = 36;
    private static final int MIN_Y = -38;
    private static final int MAX_Y = 38;


    public CoordinateCandidateCalculatorUseCase(AnswerCandidateRepository answerCandidateRepository, CandidateRepository candidateRepository) {
        this.answerCandidateRepository = answerCandidateRepository;
        this.candidateRepository = candidateRepository;
    }

    public Coordinate calculateCoordinateForCandidate(UUID userId) {
        List<AnswerCandidateEntity> answers = answerCandidateRepository.findByCandidateId_Id(userId);

        int totalX = 0;
        int totalY = 0;

        for (AnswerCandidateEntity answer : answers) {
            QuestionAnswerEntity qa = answer.getQuestionAnswer();
            int weight = answer.getAnswerWeight();

            int x = weight * (qa.getWeightX() != null ? qa.getWeightX() : 0);
            int y = weight * (qa.getWeightY() != null ? qa.getWeightY() : 0);

            totalX += x;
            totalY += y;
        }

        // Normalização para intervalo [-10, 10]
        double normalizedX = ((double)(totalX - MIN_X) / (MAX_X - MIN_X)) * 20 - 10;
        double normalizedY = ((double)(totalY - MIN_Y) / (MAX_Y - MIN_Y)) * 20 - 10;

        // Atualiza o usuário com coordenadas normalizadas
        CandidateEntity candidateEntity = candidateRepository.findById(userId).orElseThrow();
        candidateEntity.setCoordinateX((int) normalizedX);
        candidateEntity.setCoordinateY((int) normalizedY);
        candidateRepository.save(candidateEntity);

        return new Coordinate((int) normalizedX, (int) normalizedY);
    }

    public record Coordinate(int x, int y) {}

}
