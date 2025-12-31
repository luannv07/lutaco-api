package vn.id.luannv.lutaco.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import vn.id.luannv.lutaco.dto.response.ApiResponse;
import vn.id.luannv.lutaco.utility.EnumerateUtils;

import java.util.List;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<?> handleException(Exception e) {
        log.error("Exception error message: {}", e.getMessage());
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse
                        .builder()
                        .success(false)
                        .message(ErrorCode.UNCATEGORIZED_ERROR.getMessage())
                        .build());
    }

    @ExceptionHandler(value = ApiException.class)
    public ResponseEntity<?> handleApiException(ApiException e) {
        log.error("ApiException error message: {}", e.getMessage());
        ErrorCode errorCode = e.getErrorCode();
        List<ErrorField> errorFields = e.getErrorFields()
                .stream()
                .map(EnumerateUtils::convertFieldMessageToMessage)
                .toList();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse
                        .builder()
                        .success(false)
                        .message(errorCode.getMessage())
                        .errors(errorFields)
                        .build());
    }
}
