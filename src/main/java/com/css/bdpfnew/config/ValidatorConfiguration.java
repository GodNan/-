package com.css.bdpfnew.config;

import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;


/**
 * hibernate-validator配置文件
 * @Auther: GodNan
 * @Date: 2018/5/29 10:45
 * @Version: 1.0
 * @Description:
 */
@Configuration
public class ValidatorConfiguration {

    @Bean
    public Validator validator(){
        ValidatorFactory validatorFactory = Validation.byProvider( HibernateValidator.class )
                .configure()
                .addProperty( "hibernate.validator.fail_fast", "true" )
                .buildValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        return validator;
    }

    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() {
      /**默认是普通模式，会返回所有的验证不通过信息集合*/
        return new MethodValidationPostProcessor();
    }

//    @Bean
//    public MethodValidationPostProcessor methodValidationPostProcessor() {
//        MethodValidationPostProcessor postProcessor = new MethodValidationPostProcessor();
//        /**设置validator模式为快速失败返回*/
//        postProcessor.setValidator(validator());
//        return postProcessor;
//    }
}
