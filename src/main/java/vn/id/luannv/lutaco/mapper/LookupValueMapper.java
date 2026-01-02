package vn.id.luannv.lutaco.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import vn.id.luannv.lutaco.dto.request.UserCreationRequest;
import vn.id.luannv.lutaco.dto.response.LookupValueResponse;
import vn.id.luannv.lutaco.dto.response.UserResponse;
import vn.id.luannv.lutaco.entity.LookupValue;
import vn.id.luannv.lutaco.entity.User;

@Mapper(componentModel = "spring")
public interface LookupValueMapper {
    LookupValueResponse toResponse(LookupValue lookupValue);
}
