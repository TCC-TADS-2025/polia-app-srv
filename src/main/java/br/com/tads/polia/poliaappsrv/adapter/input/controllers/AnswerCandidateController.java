package br.com.tads.polia.poliaappsrv.adapter.input.controllers;

import br.com.tads.polia.poliaappsrv.adapter.input.api.request.AnswerCandidateRequest;
import br.com.tads.polia.poliaappsrv.adapter.input.api.request.mapper.AnswerCandidateMapperRequest;
import br.com.tads.polia.poliaappsrv.adapter.input.api.response.AnswerResponse;
import br.com.tads.polia.poliaappsrv.adapter.input.api.response.mappers.AnswerCandidateMapperResponse;
import br.com.tads.polia.poliaappsrv.domain.usecase.AnswerCandidateUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/answers/candidate")
public class AnswerCandidateController {

    @Autowired
    private AnswerCandidateMapperRequest MAPPER;

    @Autowired
    private AnswerCandidateMapperResponse MAPPER_RESPONSE;

    private final AnswerCandidateUseCase answerCandidateUseCase;

    public AnswerCandidateController(AnswerCandidateUseCase answerCandidateUseCase) {
        this.answerCandidateUseCase = answerCandidateUseCase;
    }

    @PostMapping
    public ResponseEntity<List<AnswerResponse>> createAnswerById(@Valid @RequestBody List<AnswerCandidateRequest> answers) {
        var result = answerCandidateUseCase.createAnswers(
                MAPPER.listAnswerCandidateResquestToAnswer(answers)
        );
        if(result == null || result.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        var response = MAPPER_RESPONSE.listAnswersCandidateToAnswerResponses(result);
        return ResponseEntity.ok(response);
    }
}
