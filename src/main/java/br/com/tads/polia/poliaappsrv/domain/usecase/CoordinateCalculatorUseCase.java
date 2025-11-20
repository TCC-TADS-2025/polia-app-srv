package br.com.tads.polia.poliaappsrv.domain.usecase;

import br.com.tads.polia.poliaappsrv.adapter.output.bd.AnswerEntity;
import br.com.tads.polia.poliaappsrv.adapter.output.bd.QuestionAnswerEntity;
import br.com.tads.polia.poliaappsrv.adapter.output.bd.UserEntity;
import br.com.tads.polia.poliaappsrv.port.output.bd.repository.AnswerRepository;
import br.com.tads.polia.poliaappsrv.port.output.bd.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoordinateCalculatorUseCase {

    private final AnswerRepository answerRepository;
    private final UserRepository userRepository;

    // Limites fixos para normalização
    private static final int MIN_X = -36;
    private static final int MAX_X = 36;
    private static final int MIN_Y = -38;
    private static final int MAX_Y = 38;

    public CoordinateCalculatorUseCase(AnswerRepository answerRepository, UserRepository userRepository) {
        this.answerRepository = answerRepository;
        this.userRepository = userRepository;
    }

    public Coordinate calculateCoordinateForUser(String userId) {
        List<AnswerEntity> answers = answerRepository.findByUserId_Id(userId);
        if(answers == null || answers.isEmpty()) {
            return new Coordinate(null, null);
        }
        int totalX = 0;
        int totalY = 0;

        for (AnswerEntity answer : answers) {
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
        UserEntity userEntity = userRepository.findById(userId).orElseThrow();
        userEntity.setCoordinateX((int) normalizedX);
        userEntity.setCoordinateY((int) normalizedY);
        userRepository.save(userEntity);

        return new Coordinate((int) normalizedX, (int) normalizedY);
    }

    public record Coordinate(Integer x, Integer y) {}
}
