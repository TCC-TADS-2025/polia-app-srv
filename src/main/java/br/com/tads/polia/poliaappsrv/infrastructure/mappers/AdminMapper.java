package br.com.tads.polia.poliaappsrv.infrastructure.mappers;

import br.com.tads.polia.poliaappsrv.adapter.output.bd.AdminEntity;
import br.com.tads.polia.poliaappsrv.domain.entity.Admin;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AdminMapper {

    Admin adminEntityToAdmin(AdminEntity admin);

}
