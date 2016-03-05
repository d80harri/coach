package net.d80harri.coach.domain;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.flywaydb.core.Flyway;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;

import net.d80harri.coach.domain.repository.ConfigurationBuilder;
import net.d80harri.coach.domain.repository.SessionManager;
import net.d80harri.coach.domain.repository.TransactionManager;

@Configuration
@ComponentScan(value={"net.d80harri.coach.domain"})
public class DomainConfiguration implements InitializingBean {
	private String hbm2ddlAuto = "create-drop";
	private String showSql = "true";
	private String cacheProvider = "org.hibernate.cache.internal.NoCacheProvider";
	private String dialect = "org.hibernate.dialect.H2Dialect";
	private String connectionPoolSize = "1";
	private String defaultSchema = "PUBLIC";
	private String connectionUrl = "jdbc:h2:~/test";
	private String connectionUserName = "";
	private String connectionPwd = "";
	private String driverClass = "org.h2.Driver";

	@Autowired
	private Flyway flyway;

	public DomainConfiguration() {
		System.out.println();
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		this.flyway.migrate();
	}

	@Bean
	public Flyway getFlyway(DataSource datasource) {
		Flyway result = new Flyway();
		result.setDataSource(datasource);
		return result;
	}

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
	public LocalSessionFactoryBean sessionFactory(DataSource datasource) {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(datasource);
		sessionFactory.setPackagesToScan(new String[] { "org.baeldung.spring.persistence.model" });
		sessionFactory.setHibernateProperties(hibernateProperties());

		return sessionFactory;
	}

	@Bean
	public org.hibernate.cfg.Configuration getConfiguration(ConfigurationBuilder builder) {
		return builder.setHbm2DllAuto("validate").build();
	}

	@Bean
	public DataSource getDataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(driverClass);
		dataSource.setUrl(connectionUrl);
		dataSource.setUsername(connectionUserName);
		dataSource.setPassword(connectionPwd);

		return dataSource;
	}

	Properties hibernateProperties() {
		return new Properties() {
			{
				setProperty("hibernate.hbm2ddl.auto", hbm2ddlAuto);
				setProperty("hibernate.dialect", dialect);
				setProperty("hibernate.globally_quoted_identifiers", "true");
			}
		};
	}
}
