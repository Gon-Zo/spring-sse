package com.example.common.config.db;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.h2.tools.Server;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.SQLException;

@Slf4j
@Configuration
public class LocalDBConfiguration {

  @Bean
  @ConfigurationProperties(LocalDBConstants.DATA_SOURCE_PATH)
  public HikariDataSource dataSource() {
    try {

      log.info("========== START H2 DATABASE ==========");

      Server.createTcpServer("-tcp", "-tcpAllowOthers", "-ifNotExists", "-tcpPort", 9095 + "")
          .start();

      return new HikariDataSource();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }
}
