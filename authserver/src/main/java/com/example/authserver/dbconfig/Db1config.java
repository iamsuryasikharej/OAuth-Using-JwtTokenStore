package com.example.authserver.dbconfig;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@EnableTransactionManagement
@EntityScan("com.example.authserver.entities")
@EnableJpaRepositories(entityManagerFactoryRef = "eMRef",basePackages = {"com.example.authserver.repository"})
public class Db1config {
    @Bean(name = "clientDataSource")
    public DataSource getDataSource()
    {
        MysqlDataSource dataSource=new MysqlDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/clientdetails");

        dataSource.setUser("root");
        dataSource.setPassword("durga");
        return dataSource;
    }
    @Bean(name="eMRef")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(EntityManagerFactoryBuilder entityManagerFactoryBuilder
                                                                           ,@Qualifier("clientDataSource") DataSource dataSource
                                                                           )
    {
        HashMap<String,Object> prop=new HashMap<>();
        prop.put("hibernate.hbm2ddl.auto","update");
        return entityManagerFactoryBuilder
                .dataSource(dataSource)
                .properties(prop)
                .packages("com.example.authserver.entities")
                .build();
    }
    @Bean(name="transactionManager")
    public PlatformTransactionManager platformTransactionManager(@Qualifier("eMRef")EntityManagerFactory entityManagerFactory)
    {
        return  new JpaTransactionManager(entityManagerFactory);
    }


}
