package br.com.tads.polia.poliaappsrv.adapter.input.api.request.mapper;


import br.com.tads.polia.poliaappsrv.adapter.input.api.request.AdminUpdateRequest;
import br.com.tads.polia.poliaappsrv.domain.entity.Admin;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AdminUpdateMapperRequest {

    AdminUpdateMapperRequest INSTANCE = Mappers.getMapper(AdminUpdateMapperRequest.class);

    Admin adminUpdateRequestToAdmin(AdminUpdateRequest adminUpdateRequest);

}
