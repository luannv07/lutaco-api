package vn.id.luannv.lutaco.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import vn.id.luannv.lutaco.dto.response.ApiResponse;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(value =  Exception.class )
    public ResponseEntity<?> handleException(Exception e) {
        log.error("error: {}", e.getMessage());
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse
                        .builder()
                        .success(false)
                        .message(ErrorCode.UNCATEGORIZED_ERROR.getMessage())
                        .build());
    }
}
