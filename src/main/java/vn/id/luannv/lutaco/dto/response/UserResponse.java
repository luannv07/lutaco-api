package vn.id.luannv.lutaco.dto.response;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import vn.id.luannv.lutaco.entity.Role;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    String email;
    String username;
    String firstName;
    String lastName;
    String address;
    String avatarLink;
    byte languageCd;
    byte statusCd;
    String role;
}
