package com.project.jfb.config;

import com.project.jfb.DAO.TicketDAO;
import com.project.jfb.DAO.UserDAO;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import com.project.jfb.service.ApplicationService;

import java.io.File;

@Configuration
@ComponentScan("com.project.jfb")
@PropertySource("classpath:application.properties")
public class MyApplicationContextConfiguration {

    @Value("${url}")
    private String url;

    @Value("${login}")
    private String login;

    @Value("${password}")
    private String password;

    private static File file = new File("src/main/resources/ConnectionConfiguration.txt");

    @Bean
    public PlatformTransactionManager txManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean
    @Scope("singleton")
    public DataSource dataSource() {
        PGSimpleDataSource pgSimpleDataSource = new PGSimpleDataSource();
        pgSimpleDataSource.setUser(login);
        pgSimpleDataSource.setPassword(password);
        pgSimpleDataSource.setURL(url);
        return pgSimpleDataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }

    @Bean
    public UserDAO userDAO() {
        return new UserDAO(dataSource(), jdbcTemplate());
    }

    @Bean
    public TicketDAO ticketDAO() {
        return new TicketDAO(dataSource(), jdbcTemplate());
    }
    
}