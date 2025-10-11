package br.com.tads.polia.poliaappsrv.adapter.output.mapper;

import br.com.tads.polia.poliaappsrv.adapter.output.bd.AdminEntity;
import br.com.tads.polia.poliaappsrv.domain.entity.Admin;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AdminOutputMapper {

    AdminOutputMapper INSTANCE = Mappers.getMapper(AdminOutputMapper.class);

    AdminEntity adminToAdminEntity(Admin admin);
    Admin adminEntityToAdmin(AdminEntity entity);

    List<Admin> listAdminEntityToListAdmin(List<AdminEntity> entities);

}
