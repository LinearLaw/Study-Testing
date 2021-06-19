package com.linear.D02_CRUD.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

/**
 * 57.4、纯注解方式做配置
 *      要配置的东西，其实就是几个
 *      - 设置扫描的位置 -> component-scan
 *      - 设置query runner
 *      - 设置数据库连接池信息
 *
 *  关联： D04_PureAnnotation.properties
 *
 *
 */
@PropertySource("classpath:D04_PureAnnotation.properties")  // 导入某一个配置文件
@ComponentScan("com.linear.D02_CRUD")
@Import(D04_JdbcConfig.class)
public class D04_SpringConfiguration {

}
