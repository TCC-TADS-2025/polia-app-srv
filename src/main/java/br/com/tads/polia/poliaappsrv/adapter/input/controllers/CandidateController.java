package br.com.tads.polia.poliaappsrv.adapter.input.controllers;

import br.com.tads.polia.poliaappsrv.domain.dto.candidate.CandidateDTO;
import br.com.tads.polia.poliaappsrv.domain.usecase.CandidateUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/candidates")
public class CandidateController {

    @Autowired
    private CandidateUseCase candidateUseCase;

    @PostMapping
    public ResponseEntity<CandidateDTO> createCandidate(@Valid @RequestBody CandidateDTO candidateDTO) {
        CandidateDTO createdCandidate = candidateUseCase.createCandidate(candidateDTO);
        return new ResponseEntity<>(createdCandidate, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CandidateDTO>> getAllCandidates() {
        List<CandidateDTO> candidates = candidateUseCase.getAllCandidates();
        return ResponseEntity.ok(candidates);
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<CandidateDTO> getCandidateById(@PathVariable String cpf) {
        CandidateDTO candidate = candidateUseCase.getCandidateById(cpf);
        return ResponseEntity.ok(candidate);
    }

    @PutMapping("/{cpf}")
    public ResponseEntity<CandidateDTO> updateCandidate(
            @PathVariable String cpf,
            @Valid @RequestBody CandidateDTO candidateDTO) {
        CandidateDTO updatedCandidate = candidateUseCase.updateCandidate(cpf, candidateDTO);
        return ResponseEntity.ok(updatedCandidate);
    }

    @DeleteMapping("/{cpf}")
    public ResponseEntity<Void> deleteCandidate(@PathVariable String cpf) {
        candidateUseCase.deleteCandidate(cpf);
        return ResponseEntity.noContent().build();
    }
}
