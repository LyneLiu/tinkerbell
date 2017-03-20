package org.mauritius.tinkerbell_security.config;

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
 * 1、entityManager用来管理dao层的entity;
 * 2、transactionManager用来管理jdbc业务层的处理，如果不定义默认的transaction，可以关闭：enableDefaultTransactions=false.
 *
 * Created by nn_liu on 2017/2/27.
 */

@Configuration
@EnableTransactionManagement
@EntityScan(basePackages = "org.mauritius.tinkerbell_security.entity.po")
@EnableJpaRepositories(
        entityManagerFactoryRef = "entityManagerFactorySecurity",
        transactionManagerRef = "transactionManagerSecurity",
        basePackages = {"org.mauritius.tinkerbell_security.repository"}) //设置Repository所在位置
public class SecurityJPAManager {
    
    @Autowired
    private JpaProperties jpaProperties;

    @Autowired
    @Qualifier("tinkerbellDataSource")
    private DataSource securityDataSource;

    @Bean(name = "entityManagerSecurity")
    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
        return entityManagerFactorySecurity(builder).getObject().createEntityManager();
    }

    @Bean(name = "entityManagerFactorySecurity")
    public LocalContainerEntityManagerFactoryBean entityManagerFactorySecurity(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(securityDataSource)
                .properties(getVendorProperties(securityDataSource))
                .packages("org.mauritius.tinkerbell_security.entity.po") //设置实体类所在位置
                .persistenceUnit("securityPersistenceUnit")
                .build();
    }

    private Map<String, String> getVendorProperties(DataSource dataSource) {
        return jpaProperties.getHibernateProperties(dataSource);
    }

    @Bean(name = "transactionManagerSecurity")
    public PlatformTransactionManager transactionManagerPrimary(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(entityManagerFactorySecurity(builder).getObject());
    }

}
