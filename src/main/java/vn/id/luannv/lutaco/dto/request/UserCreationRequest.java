package vn.id.luannv.lutaco.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreationRequest {
    @Pattern(message = "USERNAME_INVALID", regexp = "^[a-z][a-z0-9]{3,}$")
    @NotBlank(message = "FIELD_BLANK")
    String username;
    @Pattern(message = "PASSWORD_INVALID", regexp = "^.{3,}$")
    @NotBlank(message = "FIELD_BLANK")
    @Size(max = 50, message = "FIELD_TOO_LONG")
    String password;
    @Email(regexp = "^[a-zA-Z0-9._%+-]+@gmail\\.com$", message = "EMAIL_INVALID")
    @Size(max = 200, message = "FIELD_TOO_LONG")
    @NotBlank(message = "FIELD_BLANK")
    String email;
    @Size(max = 200, message = "FIELD_TOO_LONG")
    String address;
    @NotBlank(message = "FIELD_BLANK")
    @Size(max = 50, message = "FIELD_TOO_LONG")
    @Size(min = 2, message = "FIELD_TOO_SHORT")
    String firstName;
    @NotBlank(message = "FIELD_BLANK")
    @Size(max = 50, message = "FIELD_TOO_LONG")
    @Size(min = 2, message = "FIELD_TOO_SHORT")
    String lastName;
    @NotNull(message = "FIELD_BLANK")
    Integer roleId;
}
