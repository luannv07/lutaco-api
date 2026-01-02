package vn.id.luannv.lutaco.enumerate;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@Getter
public enum LookupValueEnum {

    GENDER_MALE("GENDER", "MALE", "Giới tính nam", 1),
    GENDER_FEMALE("GENDER", "FEMALE", "Giới tính nữ", 2),
    GENDER_OTHER("GENDER", "OTHER", "Giới tính khác hoặc không xác định", 3),

    PAYMENT_CASH("PAYMENT_METHOD", "CASH", "Thanh toán tiền mặt", 4),
    PAYMENT_BANK("PAYMENT_METHOD", "BANK", "Thanh toán chuyển khoản ngân hàng", 5),
    PAYMENT_WALLET("PAYMENT_METHOD", "WALLET", "Thanh toán ví điện tử", 6),

    TRANSACTION_EXPENSE("TRANSACTION_TYPE", "EXPENSE", "Giao dịch chi tiêu", 7),
    TRANSACTION_INCOME("TRANSACTION_TYPE", "INCOME", "Giao dịch thu nhập", 8);

    String lookupGroup;
    String lookupValue;
    String description;
    Integer lookupCd;
    byte statusFlg = 1;
}
