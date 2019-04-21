package com.css.bdpfnew;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.css.bdpfnew.interceptor.SecurityInterceptor;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableJpaAuditing
public class BdpfnewApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(BdpfnewApplication.class);
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(BdpfnewApplication.class, args);
	}

	/**
	 * 注册拦截器 createBy GodNan 2018-06-21 09:56:07
	 */
	@Configuration
	public class WebAppConfig extends WebMvcConfigurerAdapter {

		@Override
		public void addInterceptors(InterceptorRegistry registry) {
			// 注册自定义拦截器，添加拦截路径和排除拦截路径
			// .excludePathPatterns("api/path/login") 排除拦截
			registry.addInterceptor(new SecurityInterceptor()).addPathPatterns("/**").excludePathPatterns("/login/auth",
					"/swagger-ui.html", "/swagger-resources", "/v2/api-docs", "/webjars/springfox-swagger-ui/**");
		}
	}

}
