package br.com.tads.polia.poliaappsrv.domain.usecase;

import br.com.tads.polia.poliaappsrv.adapter.input.api.request.AdminUpdateRequest;
import br.com.tads.polia.poliaappsrv.domain.entity.Admin;
import br.com.tads.polia.poliaappsrv.domain.exception.ConfirmPasswordFailsException;
import br.com.tads.polia.poliaappsrv.port.output.IAdminOutputPort;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminUseCase {

    private final IAdminOutputPort outputPort;

    public AdminUseCase(IAdminOutputPort outputPort) {
        this.outputPort = outputPort;
    }

    public List<Admin> getAllAdmins() {
        return outputPort.getAllAdmins();
    }

    public Admin getAdminById(String id) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("ID cannot be null or empty");
        }
        return outputPort.getAdminById(id);
    }

    public void deleteAdminById(String id) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("ID cannot be null or empty");
        }
        outputPort.deleteAdminById(id);
    }

    public  Admin updateAdminById(String id, Admin admin) {
        if (admin == null || Strings.isEmpty(id)) {
            throw new IllegalArgumentException("Admin or Admin ID cannot be null or empty");
        }
        return outputPort.updateAdminById(id,admin);
    }

    public void checkPassword(AdminUpdateRequest adminUpdateRequest) {
        if(!adminUpdateRequest.getPassword().equals(adminUpdateRequest.getConfirmPassword())) {
            throw new ConfirmPasswordFailsException();
        }
    }


}
