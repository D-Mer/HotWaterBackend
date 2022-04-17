package nju.se.constant;

/**
 * @author jh
 * @date 2022/2/17 15:22
 */
public class ErrorMessage {
    public static class UserError {
        public static final String NAME_PSW_ERROR = "用户名或密码错误";

        public static final String USER_EXISTED_ERROR = "用户已存在";
        public static final String USERNAME_EMPTY = "用户名不能为空";
        public static final String PSW_EMPTY = "密码不能为空";
        public static final String USERNAME_LENGTH_INVALID = "用户名长度要在4至20个字符之间";
        public static final String PSW_LENGTH_INVALID = "密码长度要在6至20个字符之间";
        public static final String USERNAME_CONTAIN_SPACE = "用户名不能含有空格";
        public static final String PSW_CONTAIN_SPACE = "密码不能含有空格";

        public static final String USER_TYPE_INVALID_ERROR = "不存在该类型用户";
        public static final String SEX_TYPE_INVALID_ERROR = "不存在该类型性别";

        public static final String USER_NOT_EXISTED = "用户不存在";

        public static final String OLD_PSW_ERROR = "旧密码错误";
        public static final String INVALID_AVATAR_POSTFIX = "头像文件名不合法";
        public static final String INVALID_AVATAR_FILE = "头像文件不合法";
        public static final String AVATAR_NOT_EXIST = "头像文件不存在";
    }

    public static class DiaryError {
        public static final String INVALID_PRIVACY = "不支持的隐私级别";
        public static final String DIARY_NOT_EXIST = "日记不存在";
        public static final String NO_SUCH_DIARY_OF_USER = "用户没有该日记";
    }

    public static class HttpError {
        public static final String REQUEST_URL_NOT_EXIST = "请求路径有误";
        public static final String INVALID_REQUEST_CONTEXT = "请求状态有误";
    }
}
