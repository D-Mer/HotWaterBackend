package nju.se.my_enum;

import nju.se.constant.ErrorMessage;
import nju.se.exception.UserException;

/**
 * @author jh
 * @date 2022/2/17 10:55
 */
public enum UserType {
    //
    Admin("admin"),
    Normal("normal");

    public final String value;

    UserType(String value) {
        this.value = value;
    }

    public static UserType getType(String msg) {
        if (msg == null) {
            return null;
        }
        for (UserType type : UserType.values()) {
            if (msg.equals(type.value)) {
                return type;
            }
        }
        throw new UserException(ErrorMessage.UserError.USER_TYPE_INVALID_ERROR);
    }
}
