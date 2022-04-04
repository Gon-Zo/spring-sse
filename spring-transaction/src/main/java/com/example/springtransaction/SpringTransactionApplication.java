package com.example.springtransaction;

import com.example.common.config.db.LocalDBConstants;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@ComponentScan(
    basePackages = {"com.example.springtransaction", LocalDBConstants.LOCAL_DB_SCAN_PATH})
public class SpringTransactionApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringTransactionApplication.class, args);
  }
}
