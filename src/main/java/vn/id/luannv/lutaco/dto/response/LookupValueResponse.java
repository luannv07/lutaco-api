package vn.id.luannv.lutaco.dto.response;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import vn.id.luannv.lutaco.entity.BaseEntity;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LookupValueResponse {
    String lookupValue;
    Integer lookupCd;
    String lookupGroup;
    byte statusFlg;
    String description;
}