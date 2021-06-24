package com.linear.D03_AnnoWithoutXML.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/***
 * 57.9、纯注解方式 - 事务管理 - 总配置
 */
@Configuration
@PropertySource("D03_Jdbc.properties")
@EnableTransactionManagement
@ComponentScan("com.linear.D03_AnnoWithoutXML")
@Import({ JdbcConfig.class, TransactionConfig.class })
public class MainSpringConfiguration {

}
