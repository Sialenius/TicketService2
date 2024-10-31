package config;

import DAO.TicketDAO;
import DAO.UserDAO;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Configuration
@ComponentScan
public class MyApplicationContextConfiguration {

    private static File file = new File("src/main/resources/ConnectionConfiguration.txt");
    private static ConfigurationObject configurationObject = ConfigurationReader.read(file);

    @Bean
    @Scope("singleton")
    public DataSource dataSource() {
        PGSimpleDataSource pgSimpleDataSource = new PGSimpleDataSource();
        pgSimpleDataSource.setUser(configurationObject.getLogin());
        pgSimpleDataSource.setPassword(configurationObject.getPassword());
        pgSimpleDataSource.setURL(configurationObject.getUrl());
        return pgSimpleDataSource;
    }

    @Bean
    public UserDAO userDao() {
        return new UserDAO(dataSource());
    }

    @Bean
    public TicketDAO ticketDAO() {
        return new TicketDAO(dataSource());
    }


}