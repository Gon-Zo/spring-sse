package com.example.springweb.config.db;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

import static com.example.springweb.config.db.DefaultValue.DB1_TRANSACTION_MANAGER;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.example.springweb.repository.db1",
        entityManagerFactoryRef = "db1EntityManagerFactory",
        transactionManagerRef = "db1TransactionManager"
)
@EntityScan(basePackages = {"com.example.springweb.domain.db1"})
public class Db1Configuration {

    @Primary
    @Bean(name = "db1DataSource")
    @ConfigurationProperties("datasource.db1")
    public DataSource db1DataSource(){
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "db1EntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean db1EntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("db1DataSource")
            DataSource db1DataSource,
            JpaProperties jpaProperties
    ){
        return builder
                .dataSource(db1DataSource)
                .properties(jpaProperties.getProperties())
                .packages("com.example.springweb.domain.db1")
                .persistenceUnit("default")
                .build();
    }

    @Primary
    @Bean(name = DB1_TRANSACTION_MANAGER)
    public PlatformTransactionManager db1TransactionManager(
            @Qualifier("db1EntityManagerFactory") LocalContainerEntityManagerFactoryBean entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager(entityManagerFactory.getObject());
        transactionManager.setNestedTransactionAllowed(true);
        return transactionManager;
    }

}
