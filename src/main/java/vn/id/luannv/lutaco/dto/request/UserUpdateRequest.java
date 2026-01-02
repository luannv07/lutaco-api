package vn.id.luannv.lutaco.dto.request;

import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserUpdateRequest {
    @Size(max = 50, message = "FIELD_TOO_LONG")
    @Size(min = 2, message = "FIELD_TOO_SHORT")
    String firstName;
    @Size(max = 50, message = "FIELD_TOO_LONG")
    @Size(min = 2, message = "FIELD_TOO_SHORT")
    String lastName;
    @Size(max = 200, message = "FIELD_TOO_LONG")
    String address;
    Integer roleId;
}