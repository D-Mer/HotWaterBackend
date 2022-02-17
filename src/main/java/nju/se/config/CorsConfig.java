package nju.se.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author jh
 * @date 2021/2/17
 */
@Configuration
public class CorsConfig  implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*")
                .allowedMethods("GET", "HEAD", "POST","PUT", "DELETE", "OPTIONS")
                .exposedHeaders("access-control-allow-headers","access-control-allow-methods","access-control-allow-origin", "access-control-max-age","X-Frame-Options")
                .allowCredentials(true).maxAge(3600);
    }
}
