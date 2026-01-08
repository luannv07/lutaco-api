package vn.id.luannv.lutaco.controller;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import vn.id.luannv.lutaco.constant.MessageKeyConst;
import vn.id.luannv.lutaco.dto.request.UserDtoRequest;
import vn.id.luannv.lutaco.dto.request.UserFilterRequest;
import vn.id.luannv.lutaco.dto.response.BaseResponse;
import vn.id.luannv.lutaco.dto.response.UserResponse;
import vn.id.luannv.lutaco.service.UserService;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Tag(name = "User API", description = "API quản lý người dùng")
public class UserController {

    UserService userService;

    @Operation(summary = "Lấy danh sách người dùng", description = "Tìm kiếm và phân trang danh sách người dùng")
    @GetMapping
    public ResponseEntity<BaseResponse<Page<UserResponse>>> getUsers(
            @Parameter(description = "Các tiêu chí lọc người dùng") @ModelAttribute UserFilterRequest request,
            @Parameter(description = "Số trang, mặc định 1") @RequestParam(required = false, defaultValue = "1") Integer page,
            @Parameter(description = "Kích thước trang, mặc định 10") @RequestParam(required = false, defaultValue = "10") Integer size
    ) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(BaseResponse.success(
                        userService.search(request, page, size),
                        MessageKeyConst.Success.SENT
                ));
    }

    @Operation(summary = "Tạo người dùng mới", description = "Tạo một người dùng mới với thông tin được cung cấp")
    @PostMapping
    public ResponseEntity<BaseResponse<UserResponse>> create(
            @Valid @RequestBody UserDtoRequest request
    ) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(BaseResponse.success(
                        userService.create(request),
                        MessageKeyConst.Success.CREATED
                ));
    }

    @Operation(summary = "Lấy thông tin chi tiết người dùng", description = "Lấy chi tiết người dùng theo ID")
    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<UserResponse>> getUser(
            @Parameter(description = "ID người dùng cần lấy") @PathVariable UUID id
    ) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(BaseResponse.success(
                        userService.getDetail(id),
                        MessageKeyConst.Success.SENT
                ));
    }

    @Operation(summary = "Cập nhật người dùng", description = "Cập nhật thông tin người dùng theo ID")
    @PatchMapping("/{id}")
    public ResponseEntity<BaseResponse<UserResponse>> update(
            @Parameter(description = "ID người dùng cần cập nhật") @PathVariable UUID id,
            @Valid @RequestBody UserDtoRequest request
    ) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(BaseResponse.success(
                        userService.update(id, request),
                        MessageKeyConst.Success.UPDATED
                ));
    }
}
