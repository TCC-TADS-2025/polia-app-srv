package br.com.tads.polia.poliaappsrv.port.output;

import br.com.tads.polia.poliaappsrv.domain.entity.Admin;

import java.util.List;

public interface IAdminOutputPort {

    Admin  createAdmin(Admin admin);
    List<Admin> getAllAdmins();
    Admin getAdminById(String id);
    void deleteAdminBy(String id);
    Admin updateAdminById(String id, Admin admin);
}
