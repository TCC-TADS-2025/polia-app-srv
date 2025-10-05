package br.com.tads.polia.poliaappsrv.adapter.input.controllers;

import br.com.tads.polia.poliaappsrv.adapter.input.api.request.AnswerRequest;
import br.com.tads.polia.poliaappsrv.adapter.input.api.request.mapper.AnswerMapperRequest;
import br.com.tads.polia.poliaappsrv.adapter.input.api.response.AnswerResponse;
import br.com.tads.polia.poliaappsrv.adapter.input.api.response.mappers.AnswerMapperResponse;
import br.com.tads.polia.poliaappsrv.domain.usecase.AnswerUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/answers")
public class AnswerController {
    @Autowired
    private AnswerMapperRequest MAPPER;

    @Autowired
    private AnswerMapperResponse MAPPER_RESPONSE;

    private final AnswerUseCase answerUseCase;
    public AnswerController(AnswerUseCase answerUseCase) {
        this.answerUseCase = answerUseCase;
    }


    @PostMapping
    public ResponseEntity<List<AnswerResponse>> createAnswerById(@Valid @RequestBody List<AnswerRequest> answers) {
        var result = answerUseCase.createAnswers(
                MAPPER.listAnswerResquestToAnswer(answers)
        );
        if(result == null || result.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        var teste = MAPPER_RESPONSE.listAnswersToAnswerResponses(result);
        return ResponseEntity.ok(teste);
    }

}
