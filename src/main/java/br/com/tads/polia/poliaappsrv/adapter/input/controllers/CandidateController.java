package br.com.tads.polia.poliaappsrv.adapter.input.controllers;

import br.com.tads.polia.poliaappsrv.adapter.input.api.request.CandidateRequest;
import br.com.tads.polia.poliaappsrv.adapter.input.api.request.mapper.CandidateMapperRequest;
import br.com.tads.polia.poliaappsrv.adapter.input.api.response.CandidateResponse;
import br.com.tads.polia.poliaappsrv.adapter.input.api.response.mappers.CandidateMapperResponse;
import br.com.tads.polia.poliaappsrv.domain.entity.Candidate;
import br.com.tads.polia.poliaappsrv.domain.usecase.CandidateUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/candidates")
public class CandidateController {


    private final CandidateUseCase candidateUseCase;

    @Autowired
    private CandidateMapperRequest MAPPER_REQUEST;

    @Autowired
    private CandidateMapperResponse MAPPER_RESPONSE;

    public CandidateController(CandidateUseCase candidateUseCase) {
        this.candidateUseCase = candidateUseCase;
    }

    @PostMapping
    public ResponseEntity<CandidateResponse> createCandidate(@Valid @RequestBody CandidateRequest candidateRequest) {
        Candidate candidate = MAPPER_REQUEST.candidateRequestToCandidate(candidateRequest);
        candidate = candidateUseCase.createCandidate(candidate);
        var candidateResponse = MAPPER_RESPONSE.candidateTOCandidateResponse(candidate);
        return new ResponseEntity<>(candidateResponse, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CandidateResponse>> getAllCandidates() {
        List<Candidate> candidates = candidateUseCase.getAllCandidates();
        if (candidates == null || candidates.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        var candidatesResponse = MAPPER_RESPONSE.listCandidateToListCandidateResponse(candidates);
        return ResponseEntity.ok(candidatesResponse);
    }

    @GetMapping("/sorted-by-name")
    public ResponseEntity<List<CandidateResponse>> getAllCandidatesSortedByName() {
        List<Candidate> candidates = candidateUseCase.getAllCandidatesSortedByName();
        if (candidates == null || candidates.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        var candidatesResponse = MAPPER_RESPONSE.listCandidateToListCandidateResponse(candidates);
        return ResponseEntity.ok(candidatesResponse);
    }

    @GetMapping("/sorted")
    public ResponseEntity<List<CandidateResponse>> getAllCandidatesSorted(
            @RequestParam(name = "sortBy", defaultValue = "name") String sortBy,
            @RequestParam(name = "direction", defaultValue = "ASC") String direction) {
        List<Candidate> candidates = candidateUseCase.getAllCandidatesSorted(sortBy, direction);
        if (candidates == null || candidates.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        var candidatesResponse = MAPPER_RESPONSE.listCandidateToListCandidateResponse(candidates);
        return ResponseEntity.ok(candidatesResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CandidateResponse> getCandidateById(@PathVariable UUID id) {
        Candidate candidate = candidateUseCase.getCandidateById(id);
        if (candidate == null) {
            return ResponseEntity.noContent().build();
        }
        var candidateResponse = MAPPER_RESPONSE.candidateTOCandidateResponse(candidate);
        return ResponseEntity.ok(candidateResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CandidateResponse> updateCandidateById(
            @PathVariable UUID id,
            @RequestBody @Valid CandidateRequest candidateRequest) {
        Candidate candidate = MAPPER_REQUEST.candidateRequestToCandidate(candidateRequest);
        candidate = candidateUseCase.updateAdminById(id, candidate);
        if (candidate == null) {
            return ResponseEntity.noContent().build();
        }
        var candidateResponse = MAPPER_RESPONSE.candidateTOCandidateResponse(candidate);
        return ResponseEntity.ok(candidateResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCandidateById(@PathVariable UUID id) {
        candidateUseCase.deleteCandidateById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{candidateId}/favorite")
    public ResponseEntity<Void> addFavorite(@PathVariable UUID candidateId, @RequestParam String userId) {
        candidateUseCase.addFavorite(userId, candidateId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{candidateId}/favorite")
    public ResponseEntity<Void> removeFavorite(@PathVariable UUID candidateId, @RequestParam String userId) {
        candidateUseCase.removeFavorite(userId, candidateId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/favorites")
    public ResponseEntity<List<CandidateResponse>> getUserFavorites(@RequestParam String userId) {
        var result = candidateUseCase.getUserFavoriteCandidates(userId);
        if (result == null || result.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        var candidatesResponse = MAPPER_RESPONSE.listCandidateToListCandidateResponse(result);
        return ResponseEntity.ok(candidatesResponse);
    }
}
