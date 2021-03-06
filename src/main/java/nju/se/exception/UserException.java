package nju.se.exception;

/**
 * 用户异常
 * @author jh
 * @date 2022/2/17 10:56
 */
public class UserException extends BaseException {
    /**
     * 默认自定义异常码400
     */
    private static final Integer DEFAULT_CODE = 400;

    public UserException(String msg) {
        super(DEFAULT_CODE, msg);
    }

    public UserException(Integer code, String msg) {
        super(code, msg);
    }
}
