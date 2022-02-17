package nju.se.exception;

/**
 * @author jh
 * @date 2022/2/17 16:01
 */
public class BaseException extends RuntimeException {
    /**
     * 自定义异常码，基本与http异常码保持一致，根据业务逻辑有一些调整
     */
    public Integer code = 400;

    public BaseException(String msg) {
        super(msg);
    }

    public BaseException(Integer code, String msg) {
        super(msg);
        this.code = code;
    }

}
