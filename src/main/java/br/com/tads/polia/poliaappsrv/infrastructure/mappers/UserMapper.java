package br.com.tads.polia.poliaappsrv.infrastructure.mappers;

import br.com.tads.polia.poliaappsrv.adapter.input.api.request.AdminRequest;
import br.com.tads.polia.poliaappsrv.adapter.input.api.request.UserRequest;
import br.com.tads.polia.poliaappsrv.adapter.output.bd.UserEntity;
import br.com.tads.polia.poliaappsrv.domain.dto.user.UserDTO;

import java.util.List;

import br.com.tads.polia.poliaappsrv.domain.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    @Mapping(target = "authorities",
             expression = "java(user.getAuthorities()"
                        + ".stream()"
                        + ".map(org.springframework.security.core.GrantedAuthority::getAuthority)"
                        + ".collect(java.util.stream.Collectors.toList()))")
    UserDTO toDTO(UserEntity user);

    @Mapping(target = "authorities",
             expression = "java(user.getAuthorities()"
                        + ".stream()"
                        + ".map(org.springframework.security.core.GrantedAuthority::getAuthority)"
                        + ".collect(java.util.stream.Collectors.toList()))")
    User userEntityToUser(UserEntity user);
    
    UserEntity toEntity(UserDTO userDTO);
    
    List<UserDTO> toDTOList(List<UserEntity> users);
    
    List<UserEntity> toEntityList(List<UserDTO> userDTOs);

    UserEntity fromRegister(AdminRequest adminRequest);

    UserEntity fromRegisterUserRequest(UserRequest userRequest);
}