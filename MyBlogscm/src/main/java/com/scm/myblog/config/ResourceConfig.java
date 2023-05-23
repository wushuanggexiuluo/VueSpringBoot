package com.scm.myblog.config;

import com.scm.myblog.config.utils.ResourceUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Map;

/*
开启静态资源可访问

 */
@Configuration
public class ResourceConfig implements WebMvcConfigurer {


    // 环境配置
    @Value("${swagger.enabled}")
    private Boolean swagger_env_is_enable;
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        if (swagger_env_is_enable){
            Map<String, String> resource = ResourceUtils.getSpringMVCResource();
            for (Map.Entry<String,String> s :resource.entrySet()){
                registry.addResourceHandler(s.getKey()).addResourceLocations(s.getValue());
            }
        }

        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
        // 解决静态资源无法访问，注意这里不是一成不变的
        // 放行后缀只有一个的
        registry.addResourceHandler("/*.*").addResourceLocations("classpath:/static/");
        // 放行后缀多个 的（特别是使用vue等框架生成的，一般后缀都是2个以上的，所以必须加这个才行！！）
        registry.addResourceHandler("/**/*.*.*").addResourceLocations("classpath:/static/");
        //如果有三个后缀名的加这个，以此类推
        registry.addResourceHandler("/**/*.*.*.*").addResourceLocations("classpath:/static/");

        WebMvcConfigurer.super.addResourceHandlers(registry);

    }

}