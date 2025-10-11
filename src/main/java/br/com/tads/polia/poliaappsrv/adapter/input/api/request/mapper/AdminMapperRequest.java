package br.com.tads.polia.poliaappsrv.adapter.input.api.request.mapper;

import br.com.tads.polia.poliaappsrv.adapter.input.api.request.AdminRequest;
import br.com.tads.polia.poliaappsrv.domain.entity.Admin;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AdminMapperRequest {

    AdminMapperRequest INSTANCE = Mappers.getMapper(AdminMapperRequest.class);

    Admin adminRequestToAdmin(AdminRequest adminRequest);
}
