package br.com.tads.polia.poliaappsrv.adapter.input.controllers;

import br.com.tads.polia.poliaappsrv.adapter.input.api.request.UserRequest;
import br.com.tads.polia.poliaappsrv.adapter.input.api.request.UserUpdateRequest;
import br.com.tads.polia.poliaappsrv.adapter.input.api.request.mapper.UserMapperRequest;
import br.com.tads.polia.poliaappsrv.adapter.input.api.response.CandidateResponse;
import br.com.tads.polia.poliaappsrv.adapter.input.api.response.UserResponse;
import br.com.tads.polia.poliaappsrv.adapter.input.api.response.mappers.CandidateMapperResponse;
import br.com.tads.polia.poliaappsrv.adapter.input.api.response.mappers.UserMapperResponse;
import br.com.tads.polia.poliaappsrv.domain.dto.auth.TokenSubjectDTO;
import br.com.tads.polia.poliaappsrv.domain.entity.Candidate;
import br.com.tads.polia.poliaappsrv.domain.entity.User;
import br.com.tads.polia.poliaappsrv.domain.usecase.AuthUseCase;
import br.com.tads.polia.poliaappsrv.domain.usecase.CandidateUseCase;
import br.com.tads.polia.poliaappsrv.domain.usecase.UserUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserMapperRequest MAPPER_REQUEST;

    @Autowired
    private UserMapperResponse MAPPER_RESPONSE;

    @Autowired
    private CandidateMapperResponse MAPPER_RESPONSE_CANDIDATE;

    @Autowired
    private UserUseCase userUseCase;

    @Autowired
    private CandidateUseCase candidateUseCase;

    @Autowired
    private AuthUseCase authUseCase;


    @PostMapping()
    public ResponseEntity<TokenSubjectDTO> registerUser(@RequestBody UserRequest userRequest) {
        User user = MAPPER_REQUEST.userRequestToUser(userRequest);
        TokenSubjectDTO response = authUseCase.registerUser(user);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        List<User> users = userUseCase.getAllUsers();
        if (users ==null || users.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        var userResponse = MAPPER_RESPONSE.listUserToListUserResponse(users);
        return ResponseEntity.ok(userResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable String id) {
        User user = userUseCase.getUserById(id);
        if (user == null) {
            return ResponseEntity.noContent().build();
        }
        var userResponse = MAPPER_RESPONSE.userToUserResponse(user);
        return ResponseEntity.ok(userResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable String id) {
        userUseCase.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUserById(@PathVariable String id, @RequestBody UserUpdateRequest userRequest) {
        User user = MAPPER_REQUEST.userUpdateRequestToUser(userRequest);
        user = userUseCase.updateUserById(id, user);
        if (user == null) {
            return ResponseEntity.noContent().build();
        }
        var userResponse = MAPPER_RESPONSE.userToUserResponse(user);
        return ResponseEntity.ok(userResponse);
    }

    @GetMapping("/{userId}/candidates")
    public ResponseEntity<List<CandidateResponse>> findNextsByUserId(@PathVariable String userId) {
        List<Candidate> candidates = candidateUseCase.findNextsByUserId(userId);
        if (candidates == null || candidates.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        var candidateResponse = MAPPER_RESPONSE_CANDIDATE.listCandidateToListCandidateResponse(candidates);
        return ResponseEntity.ok(candidateResponse);
    }

}
