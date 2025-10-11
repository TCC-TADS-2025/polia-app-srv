package br.com.tads.polia.poliaappsrv.adapter.input.api.response.mappers;


import br.com.tads.polia.poliaappsrv.adapter.input.api.response.AdminResponse;
import br.com.tads.polia.poliaappsrv.domain.entity.Admin;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AdminMapperResponse {

    AdminMapperResponse INSTANCE = Mappers.getMapper(AdminMapperResponse.class);

    AdminResponse adminTOAdminResponse (Admin admin);

    List<AdminResponse> listAdminToListAdminResponse (List<Admin> admins);

}
