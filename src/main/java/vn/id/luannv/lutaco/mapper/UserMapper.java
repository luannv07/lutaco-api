package vn.id.luannv.lutaco.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.NullValuePropertyMappingStrategy;

import vn.id.luannv.lutaco.dto.request.UserCreationRequest;
import vn.id.luannv.lutaco.dto.request.UserUpdateRequest;
import vn.id.luannv.lutaco.dto.response.UserResponse;
import vn.id.luannv.lutaco.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "roleName", source = "role.roleName")
    UserResponse toResponse(User user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "statusCd", ignore = true)
    @Mapping(target = "languageCd", ignore = true)
    User toEntity(UserCreationRequest userCreationRequest);


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "username", ignore = true)
    @Mapping(target = "email", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "role", ignore = true) // Xử lý role thủ công bên Service
    void updateUser(@MappingTarget User user, UserUpdateRequest request);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "username", ignore = true)
    @Mapping(target = "email", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "role", ignore = true)
    void patchUser(@MappingTarget User user, UserUpdateRequest request);
}
