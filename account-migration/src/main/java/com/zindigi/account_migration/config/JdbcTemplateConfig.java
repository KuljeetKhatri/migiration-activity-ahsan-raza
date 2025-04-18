/*Author Name:muhammad.anas
Project Name: Lending
Package Name:com.mfs.lending.config
Class Name: JdbcTemplateConfig
Date and Time:1/30/2024 10:56 AM
Version:1.0*/
package com.zindigi.account_migration.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class JdbcTemplateConfig {

    private final DataSource dataSource;

    public JdbcTemplateConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource);
    }
}
