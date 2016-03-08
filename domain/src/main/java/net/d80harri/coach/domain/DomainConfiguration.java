package net.d80harri.coach.domain;

// TODO: Add unit tests for all classes possible
// TODO: Some deps should be in scope integrationtTestCompile instead of testCompile. Does not work right now because eclipse does know nothing about integrationTestCompile
import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;

import net.d80harri.coach.domain.config.db.DBConfiguration;
import net.d80harri.coach.domain.config.db.FlywayConfiguration;
import net.d80harri.coach.domain.repository.SessionManager;
import net.d80harri.coach.domain.repository.TransactionManager;

@Configuration
@ComponentScan(value = { "net.d80harri.coach.domain" })
@PropertySource(ignoreResourceNotFound = true, value = "classpath:domain.properties")
@Import({ DBConfiguration.class, FlywayConfiguration.class })
public class DomainConfiguration {

	@Bean
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(sessionFactory);

		return txManager;
	}

	@Bean
	public TransactionManager getTransactionManager(SessionManager sessionManager) {
		TransactionManager result = new TransactionManager(sessionManager);
		return result;
	}

	@Bean
	@DependsOn("flyway")
	public LocalSessionFactoryBean sessionFactory(DataSource datasource, HibernateProperties properties) {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(datasource);
		sessionFactory.setPackagesToScan(new String[] { "net.d80harri.coach.domain.exercise" });
		sessionFactory.setHibernateProperties(new Properties() {
			{
				setProperty("hibernate.hbm2ddl.auto", properties.getHbm2ddlAuto());
				setProperty("hibernate.dialect", properties.getDialect());
				setProperty("hibernate.globally_quoted_identifiers", "" + properties.isQuoteIdentifiers());
			}
		});

		return sessionFactory;
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		PropertySourcesPlaceholderConfigurer p = new PropertySourcesPlaceholderConfigurer();
		p.setIgnoreResourceNotFound(true);

		return p;
	}

}
