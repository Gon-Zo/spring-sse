package com.example.springweb.config.db;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

import static com.example.springweb.config.db.DefaultValue.DB2_TRANSACTION_MANAGER;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.example.springweb.repository.db2",
        entityManagerFactoryRef = "db2EntityManagerFactory",
        transactionManagerRef = "db2TransactionManager"
)
@EntityScan(basePackages = {"com.example.springweb.domain.db2"})
public class Db2Configuration {

    @Bean(name = "db2DataSource")
    @ConfigurationProperties("datasource.db2")
    public DataSource db2DataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "db2EntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean db2EntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("db2DataSource")
            DataSource db1DataSource,
            JpaProperties jpaProperties
    ){
        return builder
                .dataSource(db1DataSource)
                .properties(jpaProperties.getProperties())
                .packages("com.example.springweb.domain.db2")
                .persistenceUnit("default")
                .build();
    }

    @Bean(name = DB2_TRANSACTION_MANAGER)
    public PlatformTransactionManager db1TransactionManager(
            @Qualifier("db2EntityManagerFactory") LocalContainerEntityManagerFactoryBean entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager(entityManagerFactory.getObject());
        transactionManager.setNestedTransactionAllowed(true);
        return transactionManager;
    }

}
