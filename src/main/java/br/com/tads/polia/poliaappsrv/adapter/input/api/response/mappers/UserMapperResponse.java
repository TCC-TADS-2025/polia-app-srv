package br.com.tads.polia.poliaappsrv.adapter.input.api.response.mappers;


import br.com.tads.polia.poliaappsrv.adapter.input.api.response.UserResponse;
import br.com.tads.polia.poliaappsrv.domain.dto.user.UserDTO;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapperResponse {

    UserMapperResponse INSTANCE = org.mapstruct.factory.Mappers.getMapper(UserMapperResponse.class);

    UserResponse userDTOToUserResponse(UserDTO userDTO);
}
