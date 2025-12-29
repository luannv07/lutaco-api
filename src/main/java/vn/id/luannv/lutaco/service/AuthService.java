package vn.id.luannv.lutaco.service;

import org.springframework.validation.BindingResult;
import vn.id.luannv.lutaco.dto.request.LoginRequest;
import vn.id.luannv.lutaco.dto.response.UserResponse;

public interface AuthService {
    UserResponse login(LoginRequest loginRequest, BindingResult bindingResult);
}
