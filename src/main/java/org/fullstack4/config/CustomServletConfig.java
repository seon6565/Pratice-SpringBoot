package org.fullstack4.config;

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class CustomServletConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("/resources/**")
                .addResourceLocations("/resources/");
        registry.addResourceHandler("/templates/**")
                .addResourceLocations("classpath:/templates/");
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/assets/**")
                .addResourceLocations("classpath:/static/assets");
        registry.addResourceHandler("/css/**")
                .addResourceLocations("classpath:/static/css");
        registry.addResourceHandler("/js/**")
                .addResourceLocations("classpath:/static/js");
        registry.addResourceHandler("/images/**")
                .addResourceLocations("classpath:/static/images");
        registry.addResourceHandler("/fonts/**")
                .addResourceLocations("classpath:/static/fonts");
        registry.addResourceHandler("/fonts/**")
                .addResourceLocations("classpath:/static/fonts");
    }
}
