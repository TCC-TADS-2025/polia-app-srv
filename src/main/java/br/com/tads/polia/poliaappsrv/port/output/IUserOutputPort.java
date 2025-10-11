package br.com.tads.polia.poliaappsrv.port.output;

import br.com.tads.polia.poliaappsrv.domain.entity.User;

import java.util.List;

public interface IUserOutputPort {
    User createUser(User user);
    List<User> getAllUsers();
    User getUserById(String id);
    void deleteUserById(String id);
}
