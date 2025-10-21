package br.com.tads.polia.poliaappsrv.domain.usecase;

import br.com.tads.polia.poliaappsrv.adapter.output.bd.AnswerEntity;
import br.com.tads.polia.poliaappsrv.adapter.output.bd.QuestionAnswerEntity;
import br.com.tads.polia.poliaappsrv.port.output.bd.repository.AnswerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoordinateCalculatorUseCase {

    private final AnswerRepository answerRepository;

    public CoordinateCalculatorUseCase(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    public Coordinate calculateCoordinateForUser(String userId) {
        List<AnswerEntity> answers = answerRepository.findByUserId_Id(userId);

        int totalX = 0;
        int totalY = 0;

        for (AnswerEntity answer : answers) {
            QuestionAnswerEntity qa = answer.getQuestionAnswer();
            int weight = answer.getAnswerWeight();

            totalX += weight * (qa.getWeightX() != null ? qa.getWeightX() : 0);
            totalY += weight * (qa.getWeightY() != null ? qa.getWeightY() : 0);
        }

        return new Coordinate(totalX, totalY);
    }

    public record Coordinate(int x, int y) {}

}
