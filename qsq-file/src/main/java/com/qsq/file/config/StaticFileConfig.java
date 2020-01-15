package com.qsq.file.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author QSQ
 * @create 2020/1/14 22:42
 * No, again
 * 〈静态资源配置〉
 */
@Configuration
public class StaticFileConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //文件地址
        registry.addResourceHandler("/file/static/**")
                .addResourceLocations("file:E:/java-code/future-project-shop/file-path/");

    }

}