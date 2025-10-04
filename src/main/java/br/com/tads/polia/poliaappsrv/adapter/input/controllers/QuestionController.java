package br.com.tads.polia.poliaappsrv.adapter.input.controllers;

import br.com.tads.polia.poliaappsrv.domain.entity.Question;
import br.com.tads.polia.poliaappsrv.domain.usecase.QuestionUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/questions")
public class QuestionController {

    private final QuestionUseCase questionUseCase;

    public QuestionController(QuestionUseCase questionUseCase) {
        this.questionUseCase = questionUseCase;
    }


    @GetMapping
    public ResponseEntity<List<Question>> getAllQuestions() {
        var result = questionUseCase.getAllQuestions();
        if(result == null || result.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(result);
    }


}
