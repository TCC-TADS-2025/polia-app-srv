package br.com.tads.polia.poliaappsrv.adapter.input.controllers;


import br.com.tads.polia.poliaappsrv.domain.usecase.CoordinateCandidateCalculatorUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/coordinates/candidate")
public class CoordinateCandidateController {

    private final CoordinateCandidateCalculatorUseCase coordinateCandidateCalculatorUseCase;

    public CoordinateCandidateController(CoordinateCandidateCalculatorUseCase coordinateCandidateCalculatorUseCase) {
        this.coordinateCandidateCalculatorUseCase = coordinateCandidateCalculatorUseCase;
    }

    @GetMapping("/{candidateId}")
    public ResponseEntity<CoordinateCandidateCalculatorUseCase.Coordinate> getCoordinateCandidate(@PathVariable UUID candidateId) {
        return ResponseEntity.ok(coordinateCandidateCalculatorUseCase.calculateCoordinateForCandidate(candidateId));
    }


}
