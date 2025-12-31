package vn.id.luannv.lutaco.controller;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import vn.id.luannv.lutaco.dto.request.LoginRequest;
import vn.id.luannv.lutaco.dto.request.UserCreationRequest;
import vn.id.luannv.lutaco.dto.response.ApiResponse;
import vn.id.luannv.lutaco.dto.response.UserResponse;
import vn.id.luannv.lutaco.service.AuthService;

@Slf4j
@RestController
@RequestMapping("/api/auth")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class AuthController {

    AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest, BindingResult bindingResult) {
        UserResponse userResponse = authService.login(loginRequest, bindingResult);
        return ResponseEntity.ok(ApiResponse.builder()
                .success(true)
                .message("Login Successful")
                .data(userResponse)
                .build());
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserCreationRequest userCreationRequest, BindingResult bindingResult) {
        UserResponse userResponse = authService.register(userCreationRequest, bindingResult);
        return ResponseEntity.ok(ApiResponse.builder()
                .success(true)
                .message("Register Successful")
                .data(userResponse)
                .build());
    }
}
