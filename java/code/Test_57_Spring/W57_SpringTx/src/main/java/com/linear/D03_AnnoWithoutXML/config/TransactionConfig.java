package com.linear.D03_AnnoWithoutXML.config;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/***
 * 57.9、纯注解方式 - 事务管理器 配置
 */
public class TransactionConfig {

    @Bean("wtransactionManager")
    public PlatformTransactionManager createTransactionManager(DataSource wdataSource){
        return new DataSourceTransactionManager(wdataSource);
    }
}
