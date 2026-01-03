package vn.id.luannv.lutaco.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public enum ErrorCode {
    UNCATEGORIZED_ERROR(500, "Đã xảy ra lỗi không xác định."),
    INVALID_PARAM(400, "Tham số không hợp lệ."),
    FIELD_BLANK(400, "Trường này không được để trống."),
    FIELD_TOO_LONG(400, "Giá trị nhập quá dài."),
    FIELD_TOO_SHORT(400, "Giá trị nhập quá ngắn."),
    ENUM_INVALID(400, "Giá trị không nằm trong danh sách cho phép."),
    NOTHING_UP_TO_DATE(422, "Không có dữ liệu nào để cập nhật."),
    METHOD_NOT_ALLOWED(405, "Địa chỉ không hỗ trợ"),
    FORM_BODY_MISSING(400, "Vui lòng gửi đúng định dạng body."),

    LOGIN_FAILED(400, "Sai tài khoản hoặc mật khẩu."),
    USER_NOT_FOUND(400, "Không tìm thấy người dùng."),

    USER_INVALID(400, "Tên người dùng không hợp lệ."),
    USER_EXISTED(400, "Tên người dùng đã tồn tại."),
    PASSWORD_INVALID(400, "Mật khẩu không hợp lệ."),
    EMAIL_INVALID(400, "Địa chỉ email không hợp lệ."),
    EMAIL_EXISTED(400, "Địa chỉ email đã tồn tại."),
    USER_LOCKED(400,"Không thể sử dụng tài khoản"), LOOKUP_VALUE_NOT_FOUND(400, "Không tìm thấy tham số hệ thống");

    Integer status;
    String message;
}
