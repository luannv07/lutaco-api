package vn.id.luannv.lutaco.service;

import org.springframework.validation.BindingResult;
import vn.id.luannv.lutaco.dto.request.LoginRequest;
import vn.id.luannv.lutaco.dto.request.UserCreationRequest;
import vn.id.luannv.lutaco.dto.response.UserResponse;

public interface AuthService {
    /**
     * Đăng nhập hệ thống, trả về lỗi nếu như sai tài khoản hoặc mật khẩu, trả về UserResponse khi login thành công
     * @param loginRequest Form body login
     * @param bindingResult Các lỗi từ form login
     * @return
     */
    UserResponse login(LoginRequest loginRequest, BindingResult bindingResult);

    /**
     * Đăng nhập hệ thống, trả về lỗi nếu như sai tài khoản, mật khẩu hoặc thiếu, sai thông tin,
     * trả về UserResponse khi đăng ký thành công
     * @param userCreationRequest Form body register
     * @param bindingResult Các lỗi từ form
     * @return nguười dùng vừa đăng kí
     */
    UserResponse register(UserCreationRequest userCreationRequest, BindingResult bindingResult);
}
