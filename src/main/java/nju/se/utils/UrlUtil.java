package nju.se.utils;

import nju.se.constant.ErrorMessage;
import nju.se.exception.UrlUtilException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author jh
 * @date 2022/4/15 19:53
 */
@Component
public class UrlUtil {

    @Value("${server.port}")
    private int port;

    private static UrlUtil singleton;

    private UrlUtil() {
        singleton = this;
    }

    public static String getUrl() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            throw new UrlUtilException(ErrorMessage.HttpError.INVALID_REQUEST_CONTEXT);
        }
        InetAddress ip = null;
        try {
            ip = Inet4Address.getLocalHost();
        } catch (UnknownHostException ignored) {
        }
        assert ip != null;
        return "http://" + ip.getHostAddress() + ":" + singleton.port + "/";
    }
}
