package vn.id.luannv.lutaco.dto.response;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import vn.id.luannv.lutaco.entity.Role;

import java.time.LocalDate;

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
    byte languageCd;//lay tu lookup value 0|1: vi|en
    byte statusCd;// lay tu lookup value
    String roleName;
    LocalDate createdAt;
    LocalDate updatedAt;
    LocalDate deletedAt;
}
