package br.com.tads.polia.poliaappsrv.domain.usecase;

import br.com.tads.polia.poliaappsrv.adapter.output.bd.UserEntity;
import br.com.tads.polia.poliaappsrv.port.output.bd.repository.UserRepository;
import br.com.tads.polia.poliaappsrv.domain.dto.user.UserDTO;
import br.com.tads.polia.poliaappsrv.domain.enums.Role;
import br.com.tads.polia.poliaappsrv.infrastructure.mappers.UserMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserUseCase {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRepository userRepository;

    public UserDTO createUser(UserDTO dto) {
        UserEntity user = new UserEntity();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setCpf(dto.getCpf());
        user.setPhone(dto.getPhone());
        user.setEnabled(true);
        user.setRole(Role.ADMIN);
        userRepository.save(user);

        return dto;
    }

    public List<UserDTO> getAllUsers() {
        List<UserEntity> users = userRepository.findAll();
        return userMapper.toDTOList(users);
    }

    public UserDTO getUserById(String id) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + id));
        return userMapper.toDTO(user);
    }

    public void deleteUser(String id) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + id));
        userRepository.deleteById(id);
    }

}
