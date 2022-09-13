package com.example.springweb.config.db;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.transaction.ChainedTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import static com.example.springweb.config.db.DefaultValue.*;

@Configuration
public class TransactionConfiguration {

    @Bean(name = CHAINED_TRANSACTION_MANAGER)
    public ChainedTransactionManager transactionManager(@Qualifier(DB1_TRANSACTION_MANAGER) PlatformTransactionManager ds1,
                                                        @Qualifier(DB2_TRANSACTION_MANAGER) PlatformTransactionManager ds2) {
        return new ChainedTransactionManager(ds1, ds2);
    }
}
