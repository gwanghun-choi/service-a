// src/main/java/com/didim/servicea/config/YamlPrimaryDataBase.java
package com.example.servicea.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
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
}
