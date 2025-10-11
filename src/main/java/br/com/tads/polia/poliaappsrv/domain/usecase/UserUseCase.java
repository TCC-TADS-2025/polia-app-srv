package br.com.tads.polia.poliaappsrv.domain.usecase;

import br.com.tads.polia.poliaappsrv.adapter.input.api.request.AdminUpdateRequest;
import br.com.tads.polia.poliaappsrv.adapter.input.api.request.UserRequest;
import br.com.tads.polia.poliaappsrv.domain.entity.Admin;
import br.com.tads.polia.poliaappsrv.domain.entity.User;
import br.com.tads.polia.poliaappsrv.domain.exception.ConfirmPasswordFailsException;
import br.com.tads.polia.poliaappsrv.infrastructure.mappers.UserMapper;
import br.com.tads.polia.poliaappsrv.port.output.IUserOutputPort;
import br.com.tads.polia.poliaappsrv.port.output.bd.repository.UserRepository;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserUseCase {

    @Autowired
    private UserMapper userMapper;

    private final IUserOutputPort outputPort;

    @Autowired
    private UserRepository userRepository;

    public UserUseCase(IUserOutputPort outputPort) {
        this.outputPort = outputPort;
    }

    public List<User> getAllUsers() {
        return outputPort.getAllUsers();
    }

    public User getUserById(String id) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("ID cannot be null or empty");
        }
        return outputPort.getUserById(id);
    }

    public void deleteUserById(String id) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("ID cannot be null or empty");
        }
        outputPort.deleteUserById(id);
    }

    public User updateUserById(String id, User user) {
        if (user == null || Strings.isEmpty(id)) {
            throw new IllegalArgumentException("User or User ID cannot be null or empty");
        }
        return outputPort.updateUserById(id,user);
    }

    public void checkPassword(UserRequest userRequest) {
        if(!userRequest.getPassword().equals(userRequest.getConfirmPassword())) {
            throw new ConfirmPasswordFailsException();
        }
    }

}
