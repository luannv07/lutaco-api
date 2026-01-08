package vn.id.luannv.lutaco.service;

import vn.id.luannv.lutaco.dto.request.UserDtoRequest;
import vn.id.luannv.lutaco.dto.request.UserFilterRequest;
import vn.id.luannv.lutaco.dto.response.UserResponse;

import java.util.UUID;

public interface UserService extends
        BaseService<UserFilterRequest, UserResponse, UserDtoRequest, UUID>{
}
