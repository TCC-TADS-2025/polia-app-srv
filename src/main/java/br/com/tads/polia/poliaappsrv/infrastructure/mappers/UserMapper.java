package br.com.tads.polia.poliaappsrv.infrastructure.mappers;

import br.com.tads.polia.poliaappsrv.domain.dto.auth.RegisterDTO;
import br.com.tads.polia.poliaappsrv.domain.dto.user.UserDTO;
import br.com.tads.polia.poliaappsrv.domain.entity.User;

import java.util.List;

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
    UserDTO toDTO(User user);
    
    User toEntity(UserDTO userDTO);
    
    List<UserDTO> toDTOList(List<User> users);
    
    List<User> toEntityList(List<UserDTO> userDTOs);

    User fromRegister(RegisterDTO registerDTO);
}