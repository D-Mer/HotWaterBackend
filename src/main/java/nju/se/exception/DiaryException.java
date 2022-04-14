package nju.se.exception;

/**
 * 日记相关异常
 * @author jh
 * @date 2022/4/12 20:03
 */
public class DiaryException extends BaseException {
    /**
     * 默认自定义异常码400
     */
    private static final Integer DEFAULT_CODE = 400;

    public DiaryException(String msg) {
        super(DEFAULT_CODE, msg);
    }

    public DiaryException(Integer code, String msg) {
        super(code, msg);
    }
}
