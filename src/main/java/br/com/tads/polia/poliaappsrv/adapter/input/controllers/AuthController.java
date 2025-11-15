package br.com.tads.polia.poliaappsrv.adapter.input.controllers;

import br.com.tads.polia.poliaappsrv.domain.dto.auth.LoginDTO;
import br.com.tads.polia.poliaappsrv.domain.dto.auth.RegisterDTO;
import br.com.tads.polia.poliaappsrv.domain.dto.auth.TokenSubjectAdminDTO;
import br.com.tads.polia.poliaappsrv.domain.dto.auth.TokenSubjectDTO;
import br.com.tads.polia.poliaappsrv.domain.dto.auth.PasswordRecoveryDTO;
import br.com.tads.polia.poliaappsrv.domain.dto.auth.PasswordRecoveryResponseDTO;
import br.com.tads.polia.poliaappsrv.domain.dto.user.UserDTO;
import br.com.tads.polia.poliaappsrv.domain.usecase.AuthUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private AuthUseCase authUseCase;

    @PostMapping("/login")
    public ResponseEntity<TokenSubjectAdminDTO> loginAdmin(@RequestBody LoginDTO loginDTO) {
        TokenSubjectAdminDTO response = authUseCase.loginAdmin(loginDTO);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login/user")
    public ResponseEntity<TokenSubjectDTO> loginUser(@RequestBody LoginDTO loginDTO) {
        TokenSubjectDTO response = authUseCase.loginUser(loginDTO);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/recover-password")
    public ResponseEntity<PasswordRecoveryResponseDTO> recoverPassword(@RequestBody PasswordRecoveryDTO recoveryDTO) throws Exception {
        try {
            // Tentar recuperar senha do usuário
            PasswordRecoveryResponseDTO response = authUseCase.recoverUserPassword(recoveryDTO);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            // Se não encontrar usuário, tentar administrador
            try {
                PasswordRecoveryResponseDTO response = authUseCase.recoverAdminPassword(recoveryDTO);
                return ResponseEntity.ok(response);
            } catch (Exception adminException) {
                throw new RuntimeException("Nenhuma conta encontrada com o email fornecido", adminException);
            }
        }
    }

}
