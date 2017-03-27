package org.tinkerbell.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.Map;

/**
 * Created by nn_liu on 2017/2/27.
 */

@Configuration
@EnableTransactionManagement
@EntityScan(basePackages = "org.tinkerbell.entity.po.tinkerbell")
@EnableJpaRepositories(
        entityManagerFactoryRef = "entityManagerFactoryTinkerbell",
        transactionManagerRef = "transactionManagerTinkerbell",
        basePackages = {"org.tinkerbell.repository.tinkerbell"}) //设置Repository所在位置
public class TinkerbellJPAManager {


    @Autowired
    private JpaProperties jpaProperties;

    @Autowired
    @Qualifier("tinkerbellDataSource")
    private DataSource tinkerbellDataSource;

    @Bean(name = "entityManagerPrimary")
    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
        return entityManagerFactoryPrimary(builder).getObject().createEntityManager();
    }

    @Primary
    @Bean(name = "entityManagerFactoryTinkerbell")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryPrimary(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(tinkerbellDataSource)
                .properties(getVendorProperties(tinkerbellDataSource))
                .packages("org.tinkerbell.entity.po.tinkerbell") //设置实体类所在位置
                .persistenceUnit("tinkerbellPersistenceUnit")
                .build();
    }

    private Map<String, String> getVendorProperties(DataSource dataSource) {
        return jpaProperties.getHibernateProperties(dataSource);
    }

    @Primary
    @Bean(name = "transactionManagerTinkerbell")
    PlatformTransactionManager transactionManagerPrimary(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(entityManagerFactoryPrimary(builder).getObject());
    }

}
