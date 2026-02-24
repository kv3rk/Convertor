package ru.translator.eng_rus.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Slf4j
@Component
public class GetConnection {
    //@Value("spring.datasource.url")
    private final static String url = "jdbc:postgresql://localhost:5432/translate";
    //@Value("spring.datasource.username")
    private final static String username = "postgres";
    //@Value("spring.datasource.password")
    private final static String password = "java2905";

    public Connection getConnection() throws SQLException {
        log.info("Open connection to database");
        return DriverManager.getConnection(url, username, password);
    }
}