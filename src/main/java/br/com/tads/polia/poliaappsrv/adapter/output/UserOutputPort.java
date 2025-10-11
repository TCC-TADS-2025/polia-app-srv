package br.com.tads.polia.poliaappsrv.adapter.output;

import br.com.tads.polia.poliaappsrv.adapter.output.bd.UserEntity;
import br.com.tads.polia.poliaappsrv.adapter.output.mapper.UserOutputMapper;
import br.com.tads.polia.poliaappsrv.domain.entity.User;
import br.com.tads.polia.poliaappsrv.domain.enums.Role;
import br.com.tads.polia.poliaappsrv.port.output.IUserOutputPort;
import br.com.tads.polia.poliaappsrv.port.output.bd.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserOutputPort implements IUserOutputPort {

    @Autowired
    private UserOutputMapper MAPPER;

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public UserOutputPort(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User user) {
        UserEntity userEntity = MAPPER.userToUserEntity(user);
        userEntity.setId(UUID.randomUUID().toString());
        userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
        userEntity.setEnabled(true);
        userEntity.setRole(Role.USER);
        userRepository.save(userEntity);
        return MAPPER.userEntityToUser(userEntity);
    }

    @Override
    public List<User> getAllUsers() {
        var result = MAPPER.listUserEntityToListUser(userRepository.findAll());
        if(result == null || result.isEmpty()) {
            return null;
        }
        return result;
    }

    @Override
    public User getUserById(String id) {
        var result = MAPPER.userEntityToUser(userRepository.findById(id).orElse(null));
        if (result == null) {
            return null;
        }
        return result;
    }

    @Override
    public void deleteUserById(String id) {
        UserEntity user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + id));
        userRepository.deleteById(id);
    }

    @Override
    public User updateUserById(String id, User user) {
        var result = MAPPER.userEntityToUser(userRepository.findById(id).orElse(null));
        if (result == null) {
            return null;
        }
        result.setName(user.getName());
        result.setEmail(user.getEmail());
        result.setPassword(passwordEncoder.encode(user.getPassword()));
        UserEntity userEntity = MAPPER.userToUserEntity(result);
        userEntity.setEnabled(true);
        userEntity.setRole(Role.USER);
        userRepository.save(userEntity);
        User userUpdated = MAPPER.userEntityToUser(userEntity);
        return userUpdated;
    }
}
