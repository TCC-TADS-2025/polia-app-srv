package br.com.tads.polia.poliaappsrv.port.output.bd.repository;

import br.com.tads.polia.poliaappsrv.adapter.output.bd.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<AdminEntity,String> {
    boolean existsByEmail(String email);
    boolean existsByCpf(String cpf);
    boolean existsByPhone(String phone);
    AdminEntity findByEmail(String email);
}
