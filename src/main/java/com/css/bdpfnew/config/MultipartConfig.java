/**
 * 
 */
package com.css.bdpfnew.config;

import java.io.File;

import javax.servlet.MultipartConfigElement;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author erDuo
 * @Date 2018年11月3日 下午3:22:59
 * @Description
 */
@Configuration
public class MultipartConfig {

	@Value("${photo.temp.location}")
	private String location;

	@Bean
	MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		File tmpFile = new File(location);
		if (!tmpFile.exists()) {
			tmpFile.mkdirs();
		}
		factory.setLocation(location);
		return factory.createMultipartConfig();
	}
}
