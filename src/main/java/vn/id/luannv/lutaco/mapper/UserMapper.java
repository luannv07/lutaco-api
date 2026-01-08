package vn.id.luannv.lutaco.mapper;

import org.mapstruct.*;
import vn.id.luannv.lutaco.dto.request.UserDtoRequest;
import vn.id.luannv.lutaco.dto.response.UserResponse;
import vn.id.luannv.lutaco.entity.Role;
import vn.id.luannv.lutaco.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toEntity(UserDtoRequest request);

    @Mapping(target = "roleName", source = "role.name")
    UserResponse toResponse(User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUser(UserDtoRequest request, @MappingTarget User user);
}
