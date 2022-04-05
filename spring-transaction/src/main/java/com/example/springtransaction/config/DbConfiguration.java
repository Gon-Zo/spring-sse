package com.example.springtransaction.config;

// import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
// import org.springframework.jdbc.datasource.DataSourceTransactionManager;
// import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

// import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
public class DbConfiguration {

  //  @Bean(name = "transactionManager")
  //  public PlatformTransactionManager dataSourceTransactionManager(DataSource dataSource) {
  //    DataSourceTransactionManager dataSourceTransactionManager = new
  // DataSourceTransactionManager();
  //    dataSourceTransactionManager.setDataSource(dataSource);
  //    return dataSourceTransactionManager;
  //  }
}
