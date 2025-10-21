package br.com.tads.polia.poliaappsrv.adapter.input.controllers;

import br.com.tads.polia.poliaappsrv.domain.usecase.CoordinateCalculatorUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/coordinates")
public class CoordinateController {

    private final CoordinateCalculatorUseCase coordinateCalculatorUseCase;

    public CoordinateController(CoordinateCalculatorUseCase coordinateCalculatorUseCase) {
        this.coordinateCalculatorUseCase = coordinateCalculatorUseCase;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<CoordinateCalculatorUseCase.Coordinate> getCoordinate(@PathVariable String userId) {
        return ResponseEntity.ok(coordinateCalculatorUseCase.calculateCoordinateForUser(userId));
    }


}
