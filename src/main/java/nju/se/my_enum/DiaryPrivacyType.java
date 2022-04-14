package nju.se.my_enum;

import nju.se.constant.ErrorMessage;
import nju.se.exception.DiaryException;
import nju.se.exception.UserException;

/**
 * @author jh
 * @date 2022/2/17 10:55
 */
public enum DiaryPrivacyType {
    //
    Public("公开"),
    Private("私密");

    public final String value;

    DiaryPrivacyType(String value) {
        this.value = value;
    }

    public static DiaryPrivacyType valueToType(String value) {
        if (value == null) {
            return null;
        }
        for (DiaryPrivacyType type : DiaryPrivacyType.values()) {
            if (value.equals(type.value)) {
                return type;
            }
        }
        throw new DiaryException(ErrorMessage.DiaryError.INVALID_PRIVACY);
    }

    public static DiaryPrivacyType voToType(String formValue) {
        if (formValue == null) {
            return null;
        }
        switch (formValue) {
            case "1":
                return Public;
            case "0":
                return Private;
        }
        throw new DiaryException(ErrorMessage.DiaryError.INVALID_PRIVACY);
    }

    public static String typeToVO(DiaryPrivacyType type) {
        switch (type) {
            case Public:
                return "0";
            case Private:
                return "1";
        }
        return null;
    }
}
