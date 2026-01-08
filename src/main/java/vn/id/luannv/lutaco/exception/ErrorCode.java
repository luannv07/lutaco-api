package vn.id.luannv.lutaco.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Getter
public enum ErrorCode {
    // System errors
    SYSTEM_ERROR("sys.error", HttpStatus.INTERNAL_SERVER_ERROR),
    DATABASE_ERROR("db.error", HttpStatus.INTERNAL_SERVER_ERROR),
    UNAUTHORIZED("unauthorized", HttpStatus.UNAUTHORIZED),
    FORBIDDEN("forbidden", HttpStatus.FORBIDDEN),

    // Validation / Input errors
    VALIDATION_FAILED("input.invalid", HttpStatus.BAD_REQUEST),
    REQUIRED_FIELD_MISSING("input.required", HttpStatus.BAD_REQUEST),
    FIELD_TOO_LONG("input.tooLong", HttpStatus.BAD_REQUEST),
    FIELD_TOO_SHORT("input.tooShort", HttpStatus.BAD_REQUEST),

    // Generic entity errors
    ENTITY_NOT_FOUND("entity.notFound", HttpStatus.NOT_FOUND),
    ENTITY_EXISTS("entity.exists", HttpStatus.BAD_REQUEST),

    // Business rules
    OPERATION_NOT_ALLOWED("operation.notAllowed", HttpStatus.BAD_REQUEST),
    RESOURCE_CONFLICT("resource.conflict", HttpStatus.CONFLICT)
    ;

    String message;
    HttpStatus httpStatus;
}
