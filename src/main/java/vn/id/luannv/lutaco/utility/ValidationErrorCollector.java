package vn.id.luannv.lutaco.utility;

import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import vn.id.luannv.lutaco.exception.ApiException;
import vn.id.luannv.lutaco.exception.ErrorCode;
import vn.id.luannv.lutaco.exception.ErrorField;

@Slf4j
public class ValidationErrorCollector {
    /**
     * Xử lý lỗi tập trung từ phía body raw ở client
     * @param bindingResult Lỗi từ phía body
     * @return Một danh sách lỗi List<ErrorField>
     */
    public static List<ErrorField> addBindingErrors(BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) return new ArrayList<>();

        return bindingResult.getFieldErrors()
                .stream()
                .map(fieldError ->
                        {
                            log.error("{} | {}", fieldError.getField(), fieldError.getDefaultMessage());

                            return new ErrorField(
                                    fieldError.getField(),
                                    fieldError.getDefaultMessage()
                            );
                        }
                ).toList();
    }

    /**
     * Ném ra lỗi Tham số nếu như mảng errors khác rỗng
     * @param errors Mảng lỗi, dùng để kiểm tra sự rỗng
     */
    public static void throwIfHasErrors(List<ErrorField> errors) {
        if (!errors.isEmpty()) {
            throw new ApiException(ErrorCode.INVALID_PARAM, errors);
        }
    }
}
