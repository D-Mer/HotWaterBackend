package nju.se.vo;

import lombok.Data;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 后端处理结果返回类
 *
 * @author jh
 * @date 2022/2/6 15:22
 */
@Data
public class Response {

    private static final Logger LOGGER = LoggerFactory.getLogger(Response.class);
    /**
     * 是否成功
     */
    private Integer code;
    /**
     * 成功：需要的数据
     * 失败：失败原因
     */
    private Object data;

    /**
     * 没有返回值的成功信息
     */
    @NotNull
    public static Response buildSuccess() {
        Response r = new Response();
        r.setCode(200);
        r.setData(null);
        return r;
    }

    /**
     * 返回成功信息
     *
     * @param o 要返回的json数据
     */
    @NotNull
    public static Response buildSuccess(@NotNull Object o) {
        Response r = new Response();
        r.setCode(200);
        r.setData(o);
        return r;
    }

    /**
     * 返回失败消息
     *
     * @param s 错误原因/内容
     */
    @NotNull
    public static Response buildFailure(String s) {
        Response r = new Response();
        r.setCode(400);
        r.setData(s);
        LOGGER.info("返回错误信息:" + s);
        return r;
    }

    /**
     * 返回失败消息
     *
     * @param s 错误原因/内容
     */
    @NotNull
    public static Response buildFailure(Integer code, String s) {
        Response r = new Response();
        r.setCode(code);
        r.setData(s);
        LOGGER.info("返回错误信息:" + s);
        return r;
    }
}

