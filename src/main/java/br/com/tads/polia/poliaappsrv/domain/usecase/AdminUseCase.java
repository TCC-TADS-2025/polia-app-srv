package br.com.tads.polia.poliaappsrv.domain.usecase;

import br.com.tads.polia.poliaappsrv.domain.entity.Admin;
import br.com.tads.polia.poliaappsrv.port.output.IAdminOutputPort;
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


}
