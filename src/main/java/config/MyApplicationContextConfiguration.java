package config;

import DAO.TicketDAO;
import DAO.UserDAO;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import service.TicketService;
import service.UserService;

import java.io.File;

@Configuration
@ComponentScan
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
    public UserService userService() {
        return new UserService();
    }

    @Bean
    public UserDAO userDAO() {
        return new UserDAO(dataSource(), jdbcTemplate());
    }

    @Bean
    public TicketService ticketService() {
        return new TicketService();
    }

    @Bean
    public TicketDAO ticketDAO() {
        return new TicketDAO(dataSource(), jdbcTemplate());
    }



}