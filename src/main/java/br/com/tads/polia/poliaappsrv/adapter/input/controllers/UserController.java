package br.com.tads.polia.poliaappsrv.adapter.input.controllers;

import br.com.tads.polia.poliaappsrv.adapter.input.api.request.AdminRequest;
import br.com.tads.polia.poliaappsrv.adapter.input.api.request.UserRequest;
import br.com.tads.polia.poliaappsrv.adapter.input.api.request.mapper.UserMapperRequest;
import br.com.tads.polia.poliaappsrv.adapter.input.api.response.UserResponse;
import br.com.tads.polia.poliaappsrv.adapter.input.api.response.mappers.UserMapperResponse;
import br.com.tads.polia.poliaappsrv.domain.dto.auth.RegisterDTO;
import br.com.tads.polia.poliaappsrv.domain.dto.auth.TokenSubjectDTO;
import br.com.tads.polia.poliaappsrv.domain.dto.user.UserDTO;
import br.com.tads.polia.poliaappsrv.domain.usecase.AuthUseCase;
import br.com.tads.polia.poliaappsrv.domain.usecase.UserUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserUseCase userUseCase;

    @Autowired
    private AuthUseCase authUseCase;

//    @PostMapping("/admin")
//    public ResponseEntity<TokenSubjectDTO> registerAdmin(@RequestBody AdminRequest adminRequest) {
//        TokenSubjectDTO response = authUseCase.register(adminRequest);
//        return ResponseEntity.ok(response);
//    }

    @PostMapping()
    public ResponseEntity<TokenSubjectDTO> registerUser(@RequestBody UserRequest userRequest) {
        TokenSubjectDTO response = authUseCase.registerUser(userRequest);
        return ResponseEntity.ok(response);
    }

//    @PostMapping
//    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody AdminRequest userRequest) {
//        UserDTO userDTO = userUseCase.createUser(UserMapperRequest.INSTANCE.UserRequestToUserDTO(userRequest));
//        UserResponse userResponse = UserMapperResponse.INSTANCE.userDTOToUserResponse(userDTO);
//        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
//    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> userDTOs = userUseCase.getAllUsers();
        return ResponseEntity.ok(userDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable String id) {
        UserDTO userDTO = userUseCase.getUserById(id);
        return ResponseEntity.ok(userDTO);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
        userUseCase.deleteUser(id);
        return ResponseEntity.noContent().build();
    }


}
