package vn.id.luannv.lutaco.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import vn.id.luannv.lutaco.exception.ErrorCode;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDtoRequest {

    @NotBlank(message = "{input.required}")
    @Size(max = 50, message = "{input.tooLong}")
    String username;

    @NotBlank(message = "{input.required}")
    @Size(min = 6, message = "{input.tooShort}")
    String password;

    @NotBlank(message = "{input.required}")
    String fullName;

    String address;

    @NotBlank(message = "{input.required}")
    String email;

    @NotBlank(message = "{input.required}")
    String gender;

    @NotNull(message = "{input.required}")
    Integer roleId;

    Boolean isActive = true;
}
