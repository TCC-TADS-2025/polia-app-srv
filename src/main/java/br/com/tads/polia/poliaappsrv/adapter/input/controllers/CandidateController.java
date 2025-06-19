package br.com.tads.polia.poliaappsrv.adapter.input.controllers;


import br.com.tads.polia.poliaappsrv.domain.entity.Candidate;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/candidates")
public class CandidateController {

        @PostMapping("/create")
        Public ResponseEntity<CandidateDTO> createCandidate(@RequestBody @Valid CandidateDTO candidateDTO) {
          return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.create(cliente));
        }
}
