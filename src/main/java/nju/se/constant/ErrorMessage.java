package nju.se.constant;

/**
 * @author jh
 * @date 2022/2/17 15:22
 */
public class ErrorMessage {
    public static class UserError {
        public static final String NAME_PASSWD_ERROR = "用户名或密码错误";

        public static final String USER_EXISTED_ERROR = "用户已存在";
        public static final String USERNAME_EMPTY = "用户名不能为空";
        public static final String PASSWORD_EMPTY = "密码不能为空";
        public static final String USERNAME_LENGTH_INVALID = "用户名长度要在4至20个字符之间";
        public static final String PASSWORD_LENGTH_INVALID = "密码长度要在6至20个字符之间";
        public static final String USERNAME_CONTAIN_SPACE = "用户名不能含有空格";
        public static final String PASSWORD_CONTAIN_SPACE = "密码不能含有空格";

        public static final String USER_TYPE_INVALID_ERROR = "不存在该类型用户";
    }

    public static class HttpError {
        public static final String REQUEST_URL_NOT_EXIST = "请求路径有误";
    }
}
