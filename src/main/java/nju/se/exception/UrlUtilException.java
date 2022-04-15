package nju.se.exception;

/**
 * @author jh
 * @date 2022/4/15 19:56
 */
public class UrlUtilException extends RuntimeException {
    /**
     * 自定义异常码，基本与http异常码保持一致，根据业务逻辑有一些调整
     */
    private Integer code = 400;

    public UrlUtilException(String msg) {
        super(msg);
    }

    public UrlUtilException(Integer code, String msg) {
        super(msg);
        this.code = code;
    }

    public Integer getCode() {
        return this.code;
    };
}
