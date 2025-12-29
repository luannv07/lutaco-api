package vn.id.luannv.lutaco.enumerate;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@Getter
public enum RoleEnum {
    SYS_ADMIN("SYS_ADMIN", "Sys Admin quyền cao nhất, quản lí hệ thống."),
    ADMIN("ADMIN", "Admin quản lí người dùng."),
    NORMAL_USER("NORMAL_USER", "Người dùng bình thường"),
    PREMIUM_USER("PREMIUM_USER", "Nguười dùng chuyên nghiệp")

    ;
    String roleName;
    String description;
}
