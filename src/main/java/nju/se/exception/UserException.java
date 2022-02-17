package nju.se.exception;

/**
 * 用户异常
 * @author jh
 * @date 2022/2/17 10:56
 */
public class UserException extends BaseException {
    /**
     * 自定义异常码
     */
    public static final Integer code = 400;

    public UserException(String msg) {
        super(code, msg);
    }
}
