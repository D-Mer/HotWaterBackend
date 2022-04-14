package nju.se.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author jh
 * @date 2021/2/17
 */
// 解决跨域问题只要配置CorsConfig和CorsFilter中的一个就行了
// 这里是使用springboot自带的拦截器，在请求进入servlet之后，Controller的方法之前，增加一个跨域配置
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")  // 允许所有跨域的域名
                .allowedMethods("*")  // 允许任何方法（post、get等）
                .allowedHeaders("*")  // 允许所有头
                .allowCredentials(true)  // 允许证书、cookie
                .maxAge(3600);  // maxAge(3600)表明在3600秒内，不需要再发送预检验请求，可以缓存该结果
    }
}
