package com.kwb.saller.configuration;

import com.kwb.entity.Order;
import com.kwb.entity.VerificationOrder;
import com.kwb.saller.repository.OrderRepository;
import com.kwb.saller.slaverepository.VerificationOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.config.RepositoryBeanNamePrefix;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 数据库相关操作
 */
@Configuration
public class DataAcessConfiguration {

    //@ConfigurationProperties("spring.datasource.master")

    @Autowired
    private JpaProperties jpaProperties;


    /*------------------------------------DataSource配置-----------------------------------------*/
    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource.master")
    public DataSource masterDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.slave")
    public DataSource slaveDataSource() {
        return DataSourceBuilder.create().build();
    }

    /*----------------------------------EntityManager配置------------------------------------*/
    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean masterEntityManagerFactory(
            EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(masterDataSource())//DataSource
                .packages(Order.class)//实体类所在的包路径
                .persistenceUnit("Master")
                .properties(getVendorProperties(masterDataSource()))
                .build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean slaveEntityManagerFactory(
            EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(slaveDataSource())//DataSource
                .packages(VerificationOrder.class)//实体类所在的包路径
                .persistenceUnit("slave")
                .properties(getVendorProperties(slaveDataSource()))
                .build();
    }

    /*------------------------------------JpaTransaction配置(Jpa事务)---------------------------------------*/
    @Bean
    @Primary
    public PlatformTransactionManager masterTransactionManager(@Qualifier("masterEntityManagerFactory") LocalContainerEntityManagerFactoryBean masterEntityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager(masterEntityManagerFactory.getObject());
        return transactionManager;
    }

    @Bean
    public PlatformTransactionManager slaveTransactionManager(@Qualifier("slaveEntityManagerFactory") LocalContainerEntityManagerFactoryBean slaveEntityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager(slaveEntityManagerFactory.getObject());
        return transactionManager;
    }

    /*----------------------------------------------JpaRepository配置--------------------------------------------*/
    //扫描的时候并不确定那个先扫描,同样也不确定哪个生效
    //在这里决定那个repository使用那个datasource 使用basepackageclasser属性，为repository配置事务管理和实体类管理
    @EnableJpaRepositories(basePackageClasses = OrderRepository.class,
            entityManagerFactoryRef = "masterEntityManagerFactory",transactionManagerRef = "masterTransactionManager")
    @Primary
    public class MasterConfiguration {
    }

    @EnableJpaRepositories(basePackageClasses = OrderRepository.class,
            entityManagerFactoryRef = "slaveEntityManagerFactory",transactionManagerRef = "slaveTransactionManager")
    @RepositoryBeanNamePrefix(value = "read")
    public class ReadConfiguration {
    }

    //在这里决定那个repository使用那个datasource
    @EnableJpaRepositories(basePackageClasses = VerificationOrderRepository.class,
            entityManagerFactoryRef = "slaveEntityManagerFactory",transactionManagerRef = "slaveTransactionManager")
    public class SlaveConfiguration {
    }

    protected Map<String, Object> getVendorProperties(DataSource dataSource) {
        Map<String, Object> vendorProperties = new LinkedHashMap<String, Object>();
        vendorProperties.putAll(jpaProperties.getHibernateProperties(dataSource));
        return vendorProperties;
    }

}
