package com.example.sse.config;

import com.zaxxer.hikari.HikariDataSource;
import org.h2.tools.Server;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.SQLException;

@Configuration
public class H2Configuration {

    @Bean
    @ConfigurationProperties("spring.datasource")
    public HikariDataSource dataSource() throws SQLException {
        Server server = defaultRun();
        return new HikariDataSource();
    }

    private Server defaultRun() throws SQLException {
        return Server.createTcpServer(
                "-tcp",
                "-tcpAllowOthers",
                "-ifNotExists",
                "-tcpPort", 9092 + "").start();
    }

}
