package com.css.bdpfnew.config;

import com.css.bdpfnew.Filter.ServletRequestFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Auther: GodNan
 * @Date: 2018/6/21 11:36
 * @Version: 1.0
 * @Description:
 */
@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean filterRegist() {
        FilterRegistrationBean frBean = new FilterRegistrationBean();
        frBean.setFilter(new ServletRequestFilter());
        frBean.setName("servletRequestFilter");
        frBean.addUrlPatterns("/*");
        return frBean;
    }
}
