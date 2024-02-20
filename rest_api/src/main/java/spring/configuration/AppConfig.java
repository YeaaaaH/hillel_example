package spring.configuration;

//import liquibase.integration.spring.SpringLiquibase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;
import java.util.Date;
import java.util.Properties;

@Configuration
@ComponentScan(value = "spring")
@EnableWebMvc
@PropertySource("classpath:db.properties")
public class AppConfig {

    private Environment environment;

    public AppConfig(Environment environment) {
        this.environment = environment;
    }
    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setHibernateProperties(hibernateProperties());
        sessionFactory.setPackagesToScan("spring");
        return sessionFactory;
    }

    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
        properties.put("hibernate.default_schema",  environment.getRequiredProperty("hibernate.default_schema"));
        properties.put("hibernate.hbm2ddl.auto",  environment.getRequiredProperty("hibernate.hbm2ddl.auto"));
        properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
        properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
//        properties.put("hibernate.allow_update_outside_transaction", environment.getRequiredProperty("hibernate.allow_update_outside_transaction"));
        return properties;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
        dataSource.setUrl(environment.getRequiredProperty("jdbc.url"));
        dataSource.setUsername(environment.getRequiredProperty("jdbc.username"));
        dataSource.setPassword(environment.getRequiredProperty("jdbc.password"));
        return dataSource;
    }

    @Bean
    public HibernateTransactionManager getTransactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());
        return transactionManager;
    }
//    @Bean
//    public SpringLiquibase liquibase()  {
//        SpringLiquibase liquibase = new SpringLiquibase();
//        Date date = new Date();
//        liquibase.setDataSource(dataSource());
//        liquibase.setDefaultSchema(environment.getRequiredProperty("hibernate.default_schema"));
//        liquibase.setChangeLog("db.changelog/changelog.xml");
//        return liquibase;
//    }
}
