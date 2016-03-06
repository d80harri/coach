package net.d80harri.coach.domain;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.flywaydb.core.Flyway;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;

import net.d80harri.coach.domain.repository.SessionManager;
import net.d80harri.coach.domain.repository.TransactionManager;

@Configuration
@ComponentScan(value = { "net.d80harri.coach.domain" })
public class DomainConfiguration {

	@Bean
	public Flyway getFlyway(DataSource datasource) {
		Flyway result = new Flyway();
		result.setDataSource(datasource);
		result.setTable("__META__");
		return result;
	}

	@Bean
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(sessionFactory);
		return txManager;
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfigIn() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Bean
	public TransactionManager getTransactionManager(SessionManager sessionManager) {
		TransactionManager result = new TransactionManager(sessionManager);
		return result;
	}

	@Bean
	public LocalSessionFactoryBean sessionFactory(DataSource datasource, HibernateProperties properties) {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(datasource);
		sessionFactory.setPackagesToScan(new String[] { "net.d80harri.coach.domain" });
		sessionFactory.setHibernateProperties(new Properties() {
			{
				setProperty("hibernate.hbm2ddl.auto", properties.getHbm2ddlAuto());
				setProperty("hibernate.dialect", properties.getDialect());
				setProperty("hibernate.globally_quoted_identifiers", ""+properties.isQuoteIdentifiers());
			}
		});

		return sessionFactory;
	}

	@Bean
	public DataSource getDataSource(DbProperties properties) {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(properties.getDriverClass());
		dataSource.setUrl(properties.getConnectionUrl());
		dataSource.setUsername(properties.getConnectionUserName());
		dataSource.setPassword(properties.getConnectionPwd());

		return dataSource;
	}

}
