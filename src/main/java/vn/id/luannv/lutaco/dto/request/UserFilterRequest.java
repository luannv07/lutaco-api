package vn.id.luannv.lutaco.dto.request;

import jakarta.persistence.Column;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserFilterRequest {
    String username;
    String address;
    Boolean isActive;
    Integer roleId;
}
