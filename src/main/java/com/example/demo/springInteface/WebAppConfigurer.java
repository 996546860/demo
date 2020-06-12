package com.example.demo.springInteface;

import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration
public class WebAppConfigurer implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 可添加多个
        //registry.addInterceptor(new SpringInterface()).addPathPatterns("/**");
    }

}
