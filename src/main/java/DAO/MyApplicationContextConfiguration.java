package config;

import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import java.io.File;

@Configuration
@ComponentScan
public class MyApplicationContextConfiguration {

    private static File file = new File("src/main/resources/ConnectionConfiguration.txt");
    private static ConfigurationObject configurationObject = ConfigurationReader.read(file);

    @Bean
    public PlatformTransactionManager txManager() {
        return new DataSourceTransactionManager(dataSource());
    }

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
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }


}