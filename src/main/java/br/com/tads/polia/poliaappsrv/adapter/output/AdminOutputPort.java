package br.com.tads.polia.poliaappsrv.adapter.output;

import br.com.tads.polia.poliaappsrv.adapter.output.bd.AdminEntity;
import br.com.tads.polia.poliaappsrv.adapter.output.mapper.AdminOutputMapper;
import br.com.tads.polia.poliaappsrv.domain.entity.Admin;
import br.com.tads.polia.poliaappsrv.port.output.IAdminOutputPort;
import br.com.tads.polia.poliaappsrv.port.output.bd.repository.AdminRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AdminOutputPort implements IAdminOutputPort {

    @Autowired
    private AdminOutputMapper MAPPER;

    private final PasswordEncoder passwordEncoder;
    private final AdminRepository adminRepository;

    public AdminOutputPort(PasswordEncoder passwordEncoder, AdminRepository adminRepository) {
        this.passwordEncoder = passwordEncoder;
        this.adminRepository = adminRepository;
    }

    @Override
    public Admin createAdmin(Admin admin) {
        AdminEntity adminEntity = MAPPER.adminToAdminEntity(admin);
        adminEntity.setId(UUID.randomUUID().toString());
        adminEntity.setPassword(passwordEncoder.encode(admin.getPassword()));
        adminEntity.setEnabled(true);
        adminRepository.save(adminEntity);
        return MAPPER.adminEntityToAdmin(adminEntity);
    }

    @Override
    public List<Admin> getAllAdmins() {
        var result = MAPPER.listAdminEntityToListAdmin(adminRepository.findAll());
        if(result == null || result.isEmpty()) {
            return null;
        }
        return result;
    }

    @Override
    public Admin getAdminById(String id) {
        var result = MAPPER.adminEntityToAdmin(adminRepository.findById(id).orElse(null));
        if (result == null) {
            return null;
        }
        return result;
    }

    @Override
    public void deleteAdminBy(String id) {
        AdminEntity admin = adminRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Admin not found with ID: " + id));
        adminRepository.deleteById(id);
    }

    @Override
    public Admin updateAdminById(String id, Admin admin) {
        var result = MAPPER.adminEntityToAdmin(adminRepository.findById(id).orElse(null));
        if (result == null) {
            return null;
        }
        result.setName(admin.getName());
        result.setEmail(admin.getEmail());
        result.setPhone(admin.getPhone());
        adminRepository.save(MAPPER.adminToAdminEntity(result));
        return result;
    }


}
