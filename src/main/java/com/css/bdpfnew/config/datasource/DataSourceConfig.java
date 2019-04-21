package com.css.bdpfnew.config.datasource;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @Author lvmn
 * @Date 2018/12/16 13:55
 * @Description
 */
@Configuration
public class DataSourceConfig {
    @Bean(name = "primaryDS")
    @Qualifier("primaryDS")
    @Primary
    @ConfigurationProperties(prefix="spring.primary.datasource")
    public DataSource primaryDataSource(){
        System.out.println("------------primaryDataSource--------------");
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "secondaryDS")
    @Qualifier("secondaryDS")
    @ConfigurationProperties(prefix="spring.secondary.datasource")
    public DataSource secondaryDataSource() {
        System.out.println("------------secondaryDataSource--------------");
        return DataSourceBuilder.create().build();
    }
}
