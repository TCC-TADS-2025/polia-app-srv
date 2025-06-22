package br.com.tads.polia.poliaappsrv.adapter.input.controllers;

import br.com.tads.polia.poliaappsrv.domain.dto.auth.LoginDTO;
import br.com.tads.polia.poliaappsrv.domain.dto.auth.RegisterDTO;
import br.com.tads.polia.poliaappsrv.domain.dto.auth.TokenSubjectDTO;
import br.com.tads.polia.poliaappsrv.domain.usecase.AuthUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private AuthUseCase authUseCase;

    @PostMapping("/login")
    public ResponseEntity<TokenSubjectDTO> login(@RequestBody LoginDTO loginDTO) {
        TokenSubjectDTO response = authUseCase.login(loginDTO);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<TokenSubjectDTO> register(@RequestBody RegisterDTO userDTO) {
        TokenSubjectDTO response = authUseCase.register(userDTO);
        return ResponseEntity.ok(response);
    }
}
