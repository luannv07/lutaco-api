package vn.id.luannv.lutaco.constants;

import vn.id.luannv.lutaco.enumerate.FieldName;

import java.util.Map;

public class DBFieldsConstants {
    public static final int MAX_LENGTH = 255;
    public static final int MIN_LENGTH = 1;

    public static final Map<FieldName, int[]> FIELD_LIMIT_SIZE = Map.of(
            FieldName.EMAIL, new int[] {6, 100},
            FieldName.PASSWORD, new int[] {6, 20},
            FieldName.FIRST_NAME, new int[] {4, 18},
            FieldName.LAST_NAME, new int[] {4, 18},
            FieldName.CATEGORY_NAME, new int[] {2, 50},
            FieldName.LOOKUP_GROUP, new int[] {4, 50}
    );
    public static final Map<FieldName, String> FIELD_RULE = Map.of(
            FieldName.EMAIL, ""
    );
}
