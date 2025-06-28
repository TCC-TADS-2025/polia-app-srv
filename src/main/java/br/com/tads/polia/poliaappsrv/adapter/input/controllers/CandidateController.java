package br.com.tads.polia.poliaappsrv.adapter.input.controllers;

import br.com.tads.polia.poliaappsrv.adapter.input.api.request.CandidateRequest;
import br.com.tads.polia.poliaappsrv.adapter.input.api.request.mapper.CandidateMapper;
import br.com.tads.polia.poliaappsrv.adapter.input.api.response.CandidateResponse;
import br.com.tads.polia.poliaappsrv.domain.dto.candidate.CandidateDTO;
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

    @Autowired
    private CandidateUseCase candidateUseCase;

    @PostMapping
    public ResponseEntity<CandidateResponse> createCandidate(@Valid @RequestBody CandidateRequest candidateRequest) {
        CandidateDTO candidateDTO = candidateUseCase.createCandidate(CandidateMapper.INSTANCE.CandidateResquestToCandidateDTO(candidateRequest));
        CandidateResponse candidateResponse = br.com.tads.polia.poliaappsrv.adapter.input.api.response.mappers.CandidateMapper.INSTANCE.CandidateDTOToCandidateResponse(candidateDTO);
        return new ResponseEntity<>(candidateResponse, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CandidateDTO>> getAllCandidates() {
        List<CandidateDTO> candidates = candidateUseCase.getAllCandidates();
        return ResponseEntity.ok(candidates);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CandidateDTO> getCandidateById(@PathVariable UUID id) {
        CandidateDTO candidate = candidateUseCase.getCandidateById(id);
        return ResponseEntity.ok(candidate);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CandidateResponse> updateCandidate(
            @PathVariable UUID id,
            @RequestBody @Valid CandidateRequest candidateRequest) {
        CandidateDTO candidateDTO = candidateUseCase.updateCandidate(id,CandidateMapper.INSTANCE.CandidateResquestToCandidateDTO(candidateRequest));
        CandidateResponse candidateResponse = br.com.tads.polia.poliaappsrv.adapter.input.api.response.mappers.CandidateMapper.INSTANCE.CandidateDTOToCandidateResponse(candidateDTO);
        return ResponseEntity.ok().body(candidateResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCandidate(@PathVariable UUID id) {
        candidateUseCase.deleteCandidate(id);
        return ResponseEntity.noContent().build();
    }
}
