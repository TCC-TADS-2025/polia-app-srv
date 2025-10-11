package br.com.tads.polia.poliaappsrv.adapter.input.controllers;

import br.com.tads.polia.poliaappsrv.adapter.input.api.request.UserRequest;
import br.com.tads.polia.poliaappsrv.adapter.input.api.request.mapper.UserMapperRequest;
import br.com.tads.polia.poliaappsrv.domain.dto.auth.TokenSubjectDTO;
import br.com.tads.polia.poliaappsrv.domain.entity.User;
import br.com.tads.polia.poliaappsrv.domain.usecase.AuthUseCase;
import br.com.tads.polia.poliaappsrv.domain.usecase.UserUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserMapperRequest MAPPER_REQUEST;

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

//    @PostMapping
//    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody AdminRequest userRequest) {
//        UserDTO userDTO = userUseCase.createUser(UserMapperRequest.INSTANCE.UserRequestToUserDTO(userRequest));
//        UserResponse userResponse = UserMapperResponse.INSTANCE.userDTOToUserResponse(userDTO);
//        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
//    }
//
//    @GetMapping
//    public ResponseEntity<List<UserDTO>> getAllUsers() {
//        List<UserDTO> userDTOs = userUseCase.getAllUsers();
//        return ResponseEntity.ok(userDTOs);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<UserDTO> getUserById(@PathVariable String id) {
//        UserDTO userDTO = userUseCase.getUserById(id);
//        return ResponseEntity.ok(userDTO);
//    }
//
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
//        userUseCase.deleteUser(id);
//        return ResponseEntity.noContent().build();
//    }


}
