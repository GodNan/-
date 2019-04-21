package com.css.bdpfnew.constant;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author lvmn
 * @Date 2018/12/18 9:30
 * @Description
 *  读取配置文件参数，在系统中直接使用
 */
@Component
public class CommonConfig {

    @Value("${system.model.dev}")
    public Boolean isDev;
    @Value("${system.model.basePath}")
    public String basePath;

}
