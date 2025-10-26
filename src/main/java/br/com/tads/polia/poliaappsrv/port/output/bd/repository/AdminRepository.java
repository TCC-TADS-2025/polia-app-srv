package br.com.tads.polia.poliaappsrv.port.output.bd.repository;

import br.com.tads.polia.poliaappsrv.adapter.output.bd.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<AdminEntity,String> {
    boolean existsByEmail(String email);
    boolean existsByCpf(String cpf);
    boolean existsByPhone(String phone);
    Optional<AdminEntity> findByEmail(String email);
}
