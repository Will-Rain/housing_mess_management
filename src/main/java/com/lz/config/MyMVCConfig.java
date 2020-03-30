package com.lz.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/***
 * @author will
 * @date 2020/02/24 16:14
 */

@Configuration
public class MyMVCConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/").setViewName("login");
//        registry.addViewController("/login.html").setViewName("login");
//        registry.addViewController("/admin.html").setViewName("admin");
//        registry.addViewController("/content.html").setViewName("content");
//        registry.addViewController("/test.html").setViewName("test");

        registry.addViewController("index.html").setViewName("index");
        registry.addViewController("index").setViewName("index");
        registry.addViewController("/").setViewName("index");
        registry.addViewController("pages/unauthorized.html").setViewName("pages/unauthorized");

        registry.addViewController("pages/console.html").setViewName("pages/console");
        registry.addViewController("pages/console1.html").setViewName("pages/console1");
        registry.addViewController("pages/weather.html").setViewName("pages/weather");

        registry.addViewController("pages/admin/admin-pwd.html").setViewName("pages/admin/admin-pwd");
        registry.addViewController("pages/admin/admin-list.html").setViewName("pages/admin/admin-list");
        registry.addViewController("pages/admin/admin-edit.html").setViewName("pages/admin/admin-edit");
        registry.addViewController("pages/admin/admin-add.html").setViewName("pages/admin/admin-add");
        registry.addViewController("pages/chart/chart1.html").setViewName("pages/chart/chart1");

        registry.addViewController("pages/building/building-list.html").setViewName("pages/building/building-list");
        registry.addViewController("pages/building/building-edit.html").setViewName("pages/building/building-edit");
        registry.addViewController("pages/building/building-add.html").setViewName("pages/building/building-add");

        registry.addViewController("pages/unit/unit-list.html").setViewName("pages/unit/unit-list");
        registry.addViewController("pages/unit/unit-edit.html").setViewName("pages/unit/unit-edit");
        registry.addViewController("pages/unit/unit-add.html").setViewName("pages/unit/unit-add");

        registry.addViewController("pages/house/house-list.html").setViewName("pages/house/house-list");
        registry.addViewController("pages/house/house-edit.html").setViewName("pages/house/house-edit");
        registry.addViewController("pages/house/house-add.html").setViewName("pages/house/house-add");

        registry.addViewController("pages/resident/resident-list.html").setViewName("pages/resident/resident-list");
        registry.addViewController("pages/resident/resident-edit.html").setViewName("pages/resident/resident-edit");
        registry.addViewController("pages/resident/resident-add.html").setViewName("pages/resident/resident-add");

        registry.addViewController("error/404.html").setViewName("error/404");
        registry.addViewController("error/403.html").setViewName("error/403");
        registry.addViewController("error/500.html").setViewName("error/500");

        registry.addViewController("pages/system/alertSkin.html").setViewName("pages/system/alertSkin");


        registry.addViewController("pages/chart/chart-area.html").setViewName("pages/chart/chart-area");
        registry.addViewController("pages/chart/chart-china.html").setViewName("pages/chart/chart-china");
        registry.addViewController("pages/chart/chart-house.html").setViewName("pages/chart/chart-house");
        registry.addViewController("pages/chart/chart-resident.html").setViewName("pages/chart/chart-resident");
    }

    //注册拦截器
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        //注册拦截器，及拦截请求和要剔除哪些请求!
//        //我们还需要过滤静态资源文件，否则样式显示不出来
//        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**")
//                .excludePathPatterns("/pages/login.html","/","/login","/index.html","/css/**","/images/**","/js/**","/data/**","/lib/**","/loading/**");
//    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String os = System.getProperty("os.name");
        if (os.toLowerCase().startsWith("win")) {  //如果是Windows系统
            registry.addResourceHandler("/residentPhoto/**").
                    addResourceLocations("file:E:/IDEAcodes/residentPhoto/");
        }else{//linux和mac系统
            registry.addResourceHandler("/residentPhoto/**").
                    addResourceLocations("file:/usr/IDEAcodes/residentPhoto/");
        }
    }


}