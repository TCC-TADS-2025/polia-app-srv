package br.com.tads.polia.poliaappsrv.port.output;

import br.com.tads.polia.poliaappsrv.domain.entity.User;

public interface IUserOutputPort {
    User createUser(User user);
}
