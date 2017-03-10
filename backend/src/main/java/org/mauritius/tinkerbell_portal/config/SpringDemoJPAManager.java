package org.mauritius.tinkerbell_portal.config;

import org.mauritius.tinkerbell_portal.entity.po.springdemo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
@EntityScan(basePackageClasses = User.class)
@EnableJpaRepositories(basePackages = "org.mauritius.tinkerbell_portal.repository.springdemo",
        entityManagerFactoryRef = "entityManagerFactorySpringDemo",
        transactionManagerRef = "transactionManagerSpringDemo")
public class SpringDemoJPAManager {

    @Autowired
    private JpaProperties jpaProperties;

    @Autowired
    @Qualifier("springdemoDataSource")
    private DataSource springDemoDataSource;

    @Bean(name = "entityManagerSecondary")
    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
        return entityManagerFactorySecondary(builder).getObject().createEntityManager();
    }

    @Bean(name = "entityManagerFactorySpringDemo")
    public LocalContainerEntityManagerFactoryBean entityManagerFactorySecondary(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(springDemoDataSource)
                .properties(getVendorProperties(springDemoDataSource))
                .packages("org.mauritius.tinkerbell_portal.entity.po.springdemo") //设置实体类所在位置
                .persistenceUnit("springDemoPersistenceUnit")
                .build();
    }

    private Map<String, String> getVendorProperties(DataSource dataSource) {
        return jpaProperties.getHibernateProperties(dataSource);
    }

    @Bean(name = "transactionManagerSpringDemo")
    public PlatformTransactionManager transactionManagerSecondary(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(entityManagerFactorySecondary(builder).getObject());
    }


}
