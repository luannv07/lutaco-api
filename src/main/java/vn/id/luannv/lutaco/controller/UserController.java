package vn.id.luannv.lutaco.controller;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import vn.id.luannv.lutaco.dto.request.UserUpdateRequest;
import vn.id.luannv.lutaco.dto.response.DeleteResponse;
import vn.id.luannv.lutaco.dto.response.UserResponse;
import vn.id.luannv.lutaco.service.UserService;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {

    UserService userService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllUsers(
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "name", required = false) String name 
    ) {
        int size = 10;
        Sort sort = Sort.by("createdDate").descending();
        int pageIndex = (page < 1) ? 0 : page - 1;
        Pageable pageable = PageRequest.of(pageIndex, size, sort);

        Page<UserResponse> pageData = userService.getUsers(name, pageable);

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("content", pageData.getContent());
        response.put("currentPage", page);
        response.put("totalPages", pageData.getTotalPages());
        response.put("totalElements", pageData.getTotalElements());
        response.put("pageSize", size);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable String id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(
            @PathVariable String id,
            @RequestBody @Valid UserUpdateRequest request,
            BindingResult bindingResult
    ) {
        return ResponseEntity.ok(userService.updateUser(id, request, bindingResult));
    }
    // ... Code cũ

    @PatchMapping("/{id}")
    public ResponseEntity<UserResponse> patchUser(
            @PathVariable String id,
            @RequestBody UserUpdateRequest request,
             BindingResult bindingResult
    ) {
        // Lưu ý: Có thể bỏ @Valid hoặc giữ tùy logic validate partial của bạn
        return ResponseEntity.ok(userService.patchUser(id, request,bindingResult));
    }

    // DELETE ONE
    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteResponse> deleteUser(@PathVariable String id) {
        return ResponseEntity.ok(userService.deletebyId(id));
    }

    // DELETE BATCH
    @DeleteMapping("/")
    public ResponseEntity<DeleteResponse> deleteUsers(@RequestBody List<String> ids) {
        return ResponseEntity.ok(userService.deletebyIds(ids));
    }
}