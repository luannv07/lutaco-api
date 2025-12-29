package vn.id.luannv.lutaco.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class ApiException {
    ErrorCode errorCode;
    List<ErrorField> errorFields;

    public ApiException(ErrorCode errorCode) {
        this(errorCode, List.of());
    }
}
