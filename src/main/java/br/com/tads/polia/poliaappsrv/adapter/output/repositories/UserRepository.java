package br.com.tads.polia.poliaappsrv.adapter.output.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.tads.polia.poliaappsrv.domain.entity.User;

public interface UserRepository extends JpaRepository<User,String> {

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

}
