package br.com.tads.polia.poliaappsrv.adapter.input.controllers;

import br.com.tads.polia.poliaappsrv.adapter.input.api.request.UserRequest;
import br.com.tads.polia.poliaappsrv.adapter.input.api.request.mapper.UserMapperRequest;
import br.com.tads.polia.poliaappsrv.adapter.input.api.response.UserResponse;
import br.com.tads.polia.poliaappsrv.adapter.input.api.response.mappers.UserMapperResponse;
import br.com.tads.polia.poliaappsrv.domain.dto.auth.TokenSubjectDTO;
import br.com.tads.polia.poliaappsrv.domain.entity.User;
import br.com.tads.polia.poliaappsrv.domain.usecase.AuthUseCase;
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
    private UserUseCase userUseCase;

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
    public ResponseEntity<UserResponse> updateUserById(@PathVariable String id, @RequestBody UserRequest userRequest) {
        userUseCase.checkPassword(userRequest);
        User user = MAPPER_REQUEST.userRequestToUser(userRequest);
        user = userUseCase.updateUserById(id, user);
        if (user == null) {
            return ResponseEntity.noContent().build();
        }
        var userResponse = MAPPER_RESPONSE.userToUserResponse(user);
        return ResponseEntity.ok(userResponse);
    }



}
