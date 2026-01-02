package vn.id.luannv.lutaco.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import vn.id.luannv.lutaco.dto.request.UserUpdateRequest;
import vn.id.luannv.lutaco.dto.response.DeleteResponse; 
import vn.id.luannv.lutaco.dto.response.UserResponse;
import vn.id.luannv.lutaco.entity.Role;
import vn.id.luannv.lutaco.entity.User;
import vn.id.luannv.lutaco.exception.ApiException;
import vn.id.luannv.lutaco.exception.ErrorCode;
import vn.id.luannv.lutaco.exception.ErrorField;
import vn.id.luannv.lutaco.mapper.UserMapper;
import vn.id.luannv.lutaco.repository.RoleRepository;
import vn.id.luannv.lutaco.repository.UserRepository;
import vn.id.luannv.lutaco.service.UserService;
import vn.id.luannv.lutaco.utility.ValidationErrorCollector;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    UserRepository userRepository;
    RoleRepository roleRepository;
    UserMapper userMapper;
    @Override
    @Transactional
    public UserResponse patchUser(String id, UserUpdateRequest request,BindingResult bindingResult) {
         List<ErrorField> errors = ValidationErrorCollector.addBindingErrors(bindingResult);
        ValidationErrorCollector.throwIfHasErrors(errors);
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ApiException(ErrorCode.USER_NOT_FOUND));

        if (user.getStatusCd() == 0) {
             throw new ApiException(ErrorCode.USER_LOCKED); 
        }
        userMapper.patchUser(user, request);

        if (request.getRoleId() != null) {
            Role role = roleRepository.findById(request.getRoleId())
                    .orElseThrow(() -> new ApiException(ErrorCode.INVALID_PARAM));
            user.setRole(role);
        }


        user.setUpdatedAt(LocalDate.now());


        return userMapper.toResponse(userRepository.save(user));
    }

   

    @Override
    public Page<UserResponse> getUsers(String name, Pageable pageable) {
        return userRepository.searchByName(name, pageable)
                .map(userMapper::toResponse);
    }

    @Override
    public UserResponse getUserById(String id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ApiException(ErrorCode.USER_NOT_FOUND));
        return userMapper.toResponse(user);
    }

    @Override
    @Transactional
    public UserResponse updateUser(String id, UserUpdateRequest request, BindingResult bindingResult) {
        // 1. Validate
        List<ErrorField> errors = ValidationErrorCollector.addBindingErrors(bindingResult);
        ValidationErrorCollector.throwIfHasErrors(errors);

        // 2. Check User
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ApiException(ErrorCode.USER_NOT_FOUND));

        if (user.getStatusCd() == 0) {
             throw new ApiException(ErrorCode.USER_LOCKED); 
        }

        // 3. Update
        userMapper.updateUser(user, request);

        if (request.getRoleId() != null) {
            Role role = roleRepository.findById(request.getRoleId())
                    .orElseThrow(() -> new ApiException(ErrorCode.INVALID_PARAM));
            user.setRole(role);
        }
        user.setUpdatedAt(LocalDate.now());

        return userMapper.toResponse(userRepository.save(user));
    }

    // --- SỬA LOGIC XÓA THEO INTERFACE MỚI ---

    @Override
    @Transactional
    public DeleteResponse deletebyId(String id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ApiException(ErrorCode.USER_NOT_FOUND));

        user.setStatusCd((byte) 0);
        user.setDeletedAt(LocalDate.now());
        userRepository.save(user);

        return DeleteResponse.builder()
                .message("Xóa người dùng thành công")
                .deletedId(id)
                .deletedAt(LocalDate.now())
                .build();
    }

    @Override
    @Transactional
    public DeleteResponse deletebyIds(List<String> ids) {
        List<User> users = userRepository.findAllById(ids);
        
        if (users.isEmpty()) {
            throw new ApiException(ErrorCode.NOTHING_UP_TO_DATE);
        }

        users.forEach(user -> {
            user.setStatusCd((byte) 0);
            user.setDeletedAt(LocalDate.now());
        });

        userRepository.saveAll(users);

        return DeleteResponse.builder()
                .message("Xóa danh sách người dùng thành công")
                .deletedIds(ids)
                .deletedAt(LocalDate.now())
                .build();
    }
}