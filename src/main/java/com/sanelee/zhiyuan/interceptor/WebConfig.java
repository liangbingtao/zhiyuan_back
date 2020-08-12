package com.sanelee.zhiyuan.interceptor;//package com.sanelee.zhiyuan.interceptor;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Configuration
//public class WebConfig implements WebMvcConfigurer {
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        List exclude = new ArrayList();//不拦截的列表
//        exclude.add("/");
//        exclude.add("/index.html");
//        exclude.add("/school/**");
//        exclude.add("/profession/**");
//        exclude.add("/schoolHomepage/**");
//        exclude.add("/professionHomepage/**");
//        exclude.add("/front/login");
//        exclude.add("/front/logout");
//        exclude.add("/front/**");
//        exclude.add("/css/**");
//        exclude.add("/images/**");
//        exclude.add("/fonts/**");
//        exclude.add("/js/**");
//        registry.addInterceptor(new SessionInterceptor()).addPathPatterns("/**")
//                .excludePathPatterns(exclude);
//        registry.addInterceptor(new VipInterceptor()).addPathPatterns("/highSearch/**");
//    }
//}
