package nju.se.config;


import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * @author jh
 * @date 2021/2/17
 */
// 解决跨域问题只要配置CorsConfig和CorsFilter中的一个就行了
// 这个是servlet的过滤器，与springboot无关，请求进入tomcat之后，先通过Filter，才会进入servlet
// @WebFilter注解是Servlet3.0的规范，并不是Spring boot提供的
@WebFilter(urlPatterns = "/*", filterName = "CorsFilter")
// 因为Filter本身不是springboot的东西，所以需要@Component注解来实例化，由springboot-starter-web包来注入过滤器链
@Component
public class CorsFilter implements Filter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Headers", "*");
        response.setHeader("Access-Control-Allow-Methods", "PUT,POST,GET,DELETE,OPTIONS");
        response.setHeader("Access-Control-Max-Age", "3600");
        chain.doFilter(req, res);
    }


    @Override
    public void init(FilterConfig filterConfig) {
        System.out.println("=======================================================initializing CorsFilter=================================================");
    }

    @Override
    public void destroy() {
    }
}
