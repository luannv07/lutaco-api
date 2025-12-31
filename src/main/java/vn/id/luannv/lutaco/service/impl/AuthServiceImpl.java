package vn.id.luannv.lutaco.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import vn.id.luannv.lutaco.dto.request.LoginRequest;
import vn.id.luannv.lutaco.dto.request.UserCreationRequest;
import vn.id.luannv.lutaco.dto.response.UserResponse;
import vn.id.luannv.lutaco.entity.User;
import vn.id.luannv.lutaco.enumerate.RoleEnum;
import vn.id.luannv.lutaco.exception.ApiException;
import vn.id.luannv.lutaco.exception.ErrorCode;
import vn.id.luannv.lutaco.exception.ErrorField;
import vn.id.luannv.lutaco.mapper.UserMapper;
import vn.id.luannv.lutaco.repository.RoleRepository;
import vn.id.luannv.lutaco.repository.UserRepository;
import vn.id.luannv.lutaco.service.AuthService;
import vn.id.luannv.lutaco.utility.ValidationErrorCollector;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    UserRepository userRepository;
    PasswordEncoder passwordEncoder;
    UserMapper userMapper;
    RoleRepository roleRepository;

    @Override
    public UserResponse login(LoginRequest loginRequest, BindingResult bindingResult) {
        log.info("AuthService - Login method called with username: {}", loginRequest.getUsername());

        List<ErrorField> errors = ValidationErrorCollector.addBindingErrors(bindingResult);

        ValidationErrorCollector.throwIfHasErrors(errors);

        String username = loginRequest.getUsername();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ApiException(ErrorCode.LOGIN_FAILED));

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword()))
            throw new ApiException(ErrorCode.LOGIN_FAILED);

        User savedUser = userRepository.save(user);

        return userMapper.toResponse(savedUser);
    }

    @Override
    public UserResponse register(UserCreationRequest userCreationRequest, BindingResult bindingResult) {
        log.info("AuthService - Register method called with username: {}", userCreationRequest.getUsername());

        List<ErrorField> errors = ValidationErrorCollector.addBindingErrors(bindingResult);
        if (userRepository.existsByUsername(userCreationRequest.getUsername()))
            errors.add(ErrorField.builder()
                    .field("username")
                    .message(ErrorCode.USER_EXISTED.name())
                    .build());

        if (userRepository.existsByEmail(userCreationRequest.getEmail()))
            errors.add(ErrorField.builder()
                    .field("email")
                    .message(ErrorCode.EMAIL_EXISTED.name())
                    .build());
        ValidationErrorCollector.throwIfHasErrors(errors);
        User savedUser = userMapper.toEntity(userCreationRequest);
        savedUser.setPassword(passwordEncoder.encode(userCreationRequest.getPassword()));
        savedUser.setRole(roleRepository.findByRoleName(RoleEnum.NORMAL_USER.getRoleName()).get());
        savedUser = userRepository.save(savedUser);

        return userMapper.toResponse(savedUser);
    }
}
