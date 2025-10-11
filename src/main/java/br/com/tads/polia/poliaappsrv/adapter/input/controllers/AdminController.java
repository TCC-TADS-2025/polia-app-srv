package br.com.tads.polia.poliaappsrv.adapter.input.controllers;


import br.com.tads.polia.poliaappsrv.adapter.input.api.request.AdminRequest;
import br.com.tads.polia.poliaappsrv.adapter.input.api.request.AdminUpdateRequest;
import br.com.tads.polia.poliaappsrv.adapter.input.api.request.mapper.AdminMapperRequest;
import br.com.tads.polia.poliaappsrv.adapter.input.api.request.mapper.AdminUpdateMapperRequest;
import br.com.tads.polia.poliaappsrv.adapter.input.api.response.AdminResponse;
import br.com.tads.polia.poliaappsrv.adapter.input.api.response.mappers.AdminMapperResponse;
import br.com.tads.polia.poliaappsrv.domain.dto.auth.TokenSubjectAdminDTO;
import br.com.tads.polia.poliaappsrv.domain.entity.Admin;
import br.com.tads.polia.poliaappsrv.domain.usecase.AdminUseCase;
import br.com.tads.polia.poliaappsrv.domain.usecase.AuthUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminMapperRequest MAPPER_REQUEST;

    @Autowired
    private AdminUpdateMapperRequest MAPPER_UPDATE_REQUEST;

    @Autowired
    private AdminMapperResponse MAPPER_RESPONSE;

    private final AuthUseCase authUseCase;
    private final AdminUseCase adminUseCase;

    public AdminController(AuthUseCase authUseCase, AdminUseCase adminUseCase) {
        this.authUseCase = authUseCase;
        this.adminUseCase = adminUseCase;
    }


    @PostMapping
    public ResponseEntity<TokenSubjectAdminDTO> registerAdmin(@RequestBody AdminRequest adminRequest) {
        Admin admin = MAPPER_REQUEST.adminRequestToAdmin(adminRequest);
        TokenSubjectAdminDTO response = authUseCase.register(admin);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<AdminResponse>> getAllAdmins() {
        List<Admin> admins = adminUseCase.getAllAdmins();
        if (admins ==null || admins.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        var adminResponse = MAPPER_RESPONSE.listAdminToListAdminResponse(admins);
        return ResponseEntity.ok(adminResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdminResponse> getAdminById(@PathVariable String id) {
        Admin admin = adminUseCase.getAdminById(id);
        if (admin == null) {
            return ResponseEntity.noContent().build();
        }
        var adminResponse = MAPPER_RESPONSE.adminTOAdminResponse(admin);
        return ResponseEntity.ok(adminResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdminById(@PathVariable String id) {
        adminUseCase.deleteAdminById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdminResponse> updateAdminById(@PathVariable String id, @RequestBody AdminUpdateRequest adminUpdateRequest) {
        adminUseCase.checkPassword(adminUpdateRequest);
        Admin admin = MAPPER_UPDATE_REQUEST.adminUpdateRequestToAdmin(adminUpdateRequest);
        admin = adminUseCase.updateAdminById(id, admin);
        if (admin == null) {
            return ResponseEntity.noContent().build();
        }
        var adminResponse = MAPPER_RESPONSE.adminTOAdminResponse(admin);
        return ResponseEntity.ok(adminResponse);
    }

}
