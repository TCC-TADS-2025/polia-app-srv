package br.com.tads.polia.poliaappsrv.adapter.input.api.response.mappers;


import br.com.tads.polia.poliaappsrv.adapter.input.api.response.UserResponse;
import br.com.tads.polia.poliaappsrv.domain.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapperResponse {

    UserMapperResponse INSTANCE = org.mapstruct.factory.Mappers.getMapper(UserMapperResponse.class);

    UserResponse userToUserResponse (User user);

    List<UserResponse> listUserToListUserResponse (List<User> users);
}
