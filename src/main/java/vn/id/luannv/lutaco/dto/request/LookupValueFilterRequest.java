package vn.id.luannv.lutaco.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LookupValueFilterRequest {
    String lookupValue;
    String lookupGroup;
    Byte statusFlg;
}
