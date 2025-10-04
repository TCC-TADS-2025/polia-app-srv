package br.com.tads.polia.poliaappsrv.adapter.input.api.request.mapper;


import br.com.tads.polia.poliaappsrv.adapter.input.api.request.AdminRequest;
import br.com.tads.polia.poliaappsrv.adapter.output.bd.UserEntity;
import br.com.tads.polia.poliaappsrv.domain.dto.user.UserDTO;
import org.mapstruct.Mapper;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface UserMapperRequest {

    UserMapperRequest INSTANCE = org.mapstruct.factory.Mappers.getMapper(UserMapperRequest.class);

    UserDTO UserRequestToUserDTO(AdminRequest request);

    UserDTO userToUserDTO(UserEntity user);

    default List<String> mapAuthorities(Collection<? extends GrantedAuthority> authorities) {
        if (authorities == null) return null;
        return authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
    }

}
