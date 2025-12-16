package com.example.servicea.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
@MapperScan(
        basePackages = "com.example.servicea.mapper",
        sqlSessionFactoryRef = "primarySqlSessionFactory"
)
public class PrimaryDbConfig {

    @Bean
    public DataSource primaryDataSource(
            @Value("${spring.datasource.primary.driver-class-name}") String driverClassName,
            @Value("${spring.datasource.primary.jdbc-url}") String jdbcUrl,
            @Value("${spring.datasource.primary.username}") String username,
            @Value("${spring.datasource.primary.password}") String password,
            @Value("${spring.datasource.primary.max-pool-size:5}") int maxPoolSize
    ) {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName(driverClassName);
        hikariConfig.setJdbcUrl(jdbcUrl);
        hikariConfig.setUsername(username);
        hikariConfig.setPassword(password);
        hikariConfig.setMaximumPoolSize(maxPoolSize);

        return new HikariDataSource(hikariConfig);
    }

    @Bean
    public SqlSessionFactory primarySqlSessionFactory(
            @Qualifier("primaryDataSource") DataSource dataSource
    ) throws Exception {

        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);

        factoryBean.setTypeAliasesPackage("com.example.servicea.vo");

        factoryBean.setMapperLocations(
                new PathMatchingResourcePatternResolver()
                        .getResources("classpath*:mapper/**/*.xml")
        );

        SqlSessionFactory sqlSessionFactory = factoryBean.getObject();
        if (sqlSessionFactory != null) {
            sqlSessionFactory.getConfiguration().setMapUnderscoreToCamelCase(true);
        }

        return sqlSessionFactory;
    }

    @Bean
    public SqlSessionTemplate primarySqlSessionTemplate(
            @Qualifier("primarySqlSessionFactory") SqlSessionFactory sqlSessionFactory
    ) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
