package br.com.tads.polia.poliaappsrv.adapter.output.mapper;

import br.com.tads.polia.poliaappsrv.adapter.output.bd.UserEntity;
import br.com.tads.polia.poliaappsrv.domain.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserOutputMapper {

    UserOutputMapper INSTANCE = Mappers.getMapper(UserOutputMapper.class);

    UserEntity userToUserEntity(User user);
    User userEntityToUser(UserEntity entity);

    List<User> listUserEntityToListUser(List<UserEntity> entities);
}
