package vn.id.luannv.lutaco.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.id.luannv.lutaco.dto.request.UserDtoRequest;
import vn.id.luannv.lutaco.dto.request.UserFilterRequest;
import vn.id.luannv.lutaco.dto.response.UserResponse;
import vn.id.luannv.lutaco.entity.User;
import vn.id.luannv.lutaco.exception.BusinessException;
import vn.id.luannv.lutaco.exception.ErrorCode;
import vn.id.luannv.lutaco.mapper.UserMapper;
import vn.id.luannv.lutaco.repository.UserRepository;
import vn.id.luannv.lutaco.service.UserService;

import java.util.Map;
import java.util.UUID;

@Slf4j
@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class UserServiceImpl implements UserService {
    UserRepository userRepository;
    UserMapper userMapper;

    @Override
    public UserResponse create(UserDtoRequest request) {
        log.info("UserServiceImpl create: {}", request);

        User entity = userRepository.save(userMapper.toEntity(request));

        return userMapper.toResponse(entity);
    }

    @Override
    public UserResponse getDetail(UUID id) {
        log.info("UserServiceImpl getDetail: {}", id);

        return userRepository.findById(id)
                .map(userMapper::toResponse)
                .orElseThrow(() ->
                        new BusinessException(ErrorCode.ENTITY_NOT_FOUND,
                                Map.of("id", ErrorCode.ENTITY_NOT_FOUND.getMessage())));
    }

    @Override
    public Page<UserResponse> search(UserFilterRequest request, Integer page, Integer size) {
        log.info("UserServiceImpl search: {}", request);
        Pageable pageable = PageRequest.of(page, size);

        return userRepository.findByFilters(request, pageable)
                .map(userMapper::toResponse);
    }

    @Override
    public UserResponse update(UUID id, UserDtoRequest request) {
        log.info("UserServiceImpl update: {}", request);
        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new BusinessException(ErrorCode.ENTITY_NOT_FOUND,
                                Map.of("id", ErrorCode.ENTITY_NOT_FOUND.getMessage())));

        userMapper.updateUser(request, user);

        return userMapper.toResponse(user);
    }

    @Override
    public void deleteById(UUID id) {
        log.info("UserServiceImpl delete: {}", id);
        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new BusinessException(ErrorCode.ENTITY_NOT_FOUND,
                                Map.of("id", ErrorCode.ENTITY_NOT_FOUND.getMessage())));

        user.setIsActive(false);
    }
}
