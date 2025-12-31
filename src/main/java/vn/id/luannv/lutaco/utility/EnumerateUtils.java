package vn.id.luannv.lutaco.utility;

import lombok.extern.slf4j.Slf4j;
import vn.id.luannv.lutaco.exception.ApiException;
import vn.id.luannv.lutaco.exception.ErrorCode;
import vn.id.luannv.lutaco.exception.ErrorField;

@Slf4j
public class EnumerateUtils {
    /**
     * Chuyển đổi từ Field message dạng Enumerate sang Field message dạng String
     * @param errorField tham số dạng ErrorField (field: message enum)
     * @return tham số dạng ErrorField (field: message string)
     */
    public static ErrorField convertFieldMessageToMessage(ErrorField errorField) {
        String field =  errorField.getField();
        StringBuilder errorMessageBuilder = new StringBuilder();

        try {
            String converted = ErrorCode.valueOf(errorField.getMessage())
                    .getMessage();

            errorMessageBuilder.append(converted);
        } catch (Exception e) {
            log.error("Convert ErrorField to Message error: {}", e.getMessage());
            return ErrorField.builder()
                    .field(errorField.getMessage())
                    .build();
        }

        return ErrorField.builder()
                .field(field)
                .message(errorMessageBuilder.toString())
                .build();
    }
}
