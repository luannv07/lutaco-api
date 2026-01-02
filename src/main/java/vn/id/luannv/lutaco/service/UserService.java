package vn.id.luannv.lutaco.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;
import vn.id.luannv.lutaco.dto.request.UserUpdateRequest;
import vn.id.luannv.lutaco.dto.response.DeleteResponse;
import vn.id.luannv.lutaco.dto.response.UserResponse;

import java.util.List;

public interface UserService {
    /**
     * Lấy danh sách user, hỗ trợ tìm kiếm và phân trang
     * @param name Từ khóa tìm kiếm (Firstname hoặc Lastname)
     * @param pageable Thông tin phân trang
     * @return Page<UserResponse>
     */
    Page<UserResponse> getUsers(String name, Pageable pageable);

    /**
     * Lấy chi tiết user theo ID
     * @param id ID của user
     * @return UserResponse
     */
    UserResponse getUserById(String id);

    /**
     * Cập nhật thông tin user.
     * Xử lý validate lỗi từ BindingResult ngay trong service (theo cấu trúc AuthServiceImpl)
     * @param id ID user cần sửa
     * @param request Form body update
     * @param bindingResult Các lỗi validate từ Controller truyền vào
     * @return UserResponse sau khi update
     */
    UserResponse updateUser(String id, UserUpdateRequest request, BindingResult bindingResult);
    UserResponse patchUser(String id, UserUpdateRequest request,BindingResult bindingResult);
    /**
     * Xóa mềm (Disable) 1 user
     * @param id ID user
     */
    DeleteResponse deletebyId(String id);

    /**
     * Xóa mềm (Disable) nhiều user cùng lúc
     * @param ids Danh sách ID
     */
    DeleteResponse deletebyIds(List<String> ids);
}