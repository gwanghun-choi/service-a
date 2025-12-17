// src/main/java/com/didim/servicea/config/YamlPrimaryDataBase.java
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
@MapperScan(value = {"com.example.servicea.mapper.primary"}, sqlSessionFactoryRef = "primarySqlSessionFactory")
public class YamlPrimaryDataBase {

    @Bean
    public DataSource primaryDataSource(
            @Value("${datasource.primary.driver-class-name}") String driverClassName,
            @Value("${datasource.primary.jdbc-url}") String jdbcUrl,
            @Value("${datasource.primary.username}") String username,
            @Value("${datasource.primary.password}") String password,
            @Value("${datasource.primary.maxpoolsize}") int maxPoolSize
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
            @Qualifier("primaryDataSource") DataSource dataSource,
            @Value("${datasource.primary.db-type}") String dbType
    ) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);

        // VO 패키지
        sqlSessionFactoryBean.setTypeAliasesPackage("com.example.servicea.vo");

        // mapper/primary/{dbType}/**/*.xml
        sqlSessionFactoryBean.setMapperLocations(
                new PathMatchingResourcePatternResolver()
                        .getResources(String.format("classpath*:mapper/primary/%s/**/*.xml", dbType))
        );
        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate primarySqlSession(
            @Qualifier("primarySqlSessionFactory") SqlSessionFactory sqlSessionFactory
    ) {
        sqlSessionFactory.getConfiguration().setMapUnderscoreToCamelCase(true);
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}