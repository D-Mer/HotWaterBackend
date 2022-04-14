package nju.se.my_enum;

import nju.se.constant.ErrorMessage;
import nju.se.exception.DiaryException;
import nju.se.exception.UserException;

/**
 * @author jh
 * @date 2022/4/13 4:00
 */
public enum SexType {
    //
    Man("男性"),
    Woman("女性"),
    Unknown("未知");

    public final String value;

    SexType(String value) {
        this.value = value;
    }

    public static SexType valueToType(String value) {
        if (value == null) {
            return null;
        }
        for (SexType type : SexType.values()) {
            if (value.equals(type.value)) {
                return type;
            }
        }
        throw new UserException(ErrorMessage.UserError.SEX_TYPE_INVALID_ERROR);
    }

    public static SexType voToType(String formValue) {
        if (formValue == null) {
            return null;
        }
        switch (formValue) {
            case "1":
                return Man;
            case "0":
                return Woman;
            default:
                return Unknown;
        }
    }

    public static String typeToVO(SexType type) {
        switch (type) {
            case Man:
                return "1";
            case Woman:
                return "0";
        }
        throw new UserException(ErrorMessage.UserError.SEX_TYPE_INVALID_ERROR);
    }
}
