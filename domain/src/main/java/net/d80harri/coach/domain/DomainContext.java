package net.d80harri.coach.domain;

import java.util.Properties;

import javax.sql.DataSource;

import org.flywaydb.core.Flyway;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import net.d80harri.coach.domain.exercise.ExerciseRepository;
import net.d80harri.coach.domain.repository.SessionManager;
import net.d80harri.coach.domain.repository.TransactionManager;

public class DomainContext {

	private Flyway flyway = null;
	private DomainConfiguration configuration;
	private DBInitializer dbInitializer;
	private TransactionManager transactionManager;
	private SessionManager sessionManager;
	private SessionFactory sessionFactory;
	
	private ExerciseRepository exerciseRepository;

	public DomainContext(DomainConfiguration configuration) {
		this.configuration = configuration;
	}
	
	public Flyway getFlyway() {
		if (flyway == null) {
			initFlyway();
		}
		return flyway;
	}

	private DomainConfiguration getDomainConfiguration() {
		return configuration;
	}
	

	public DBInitializer getDbInitializer() {
		if (this.dbInitializer == null) {
			initDbInitializer();
		}
		return this.dbInitializer;
	}
	
	public ExerciseRepository getExerciseRepository() {
		if (this.exerciseRepository == null) {
			initExerciseRepository();
		}
		return this.exerciseRepository;
	}

	public TransactionManager getTransactionManager() {
		if (this.transactionManager == null) {
			initTransactionManager();
		}
		return this.transactionManager;
	}

	public SessionManager getSessionManager() {
		if (this.sessionManager == null) {
			initSessionManager();
		}
		return this.sessionManager;
	}
	
	public SessionFactory getSessionFactory() {
		if (this.sessionFactory == null) {
			initSessionFactory();
		}
		return this.sessionFactory;
	}
	
	private void initSessionFactory() {
		DomainConfiguration config = getDomainConfiguration();
		
		Configuration configuration = new Configuration().configure();
		configuration.setProperties(new Properties() {
			{
				setProperty("hibernate.hbm2ddl.auto", "false");
				setProperty("hibernate.dialect", config.getHibernateDialect());
				setProperty("hibernate.globally_quoted_identifiers", "false");
				setProperty("hibernate.connection.url", config.getConnectionUrl());
				setProperty("hibernate.connection.username", config.getConnectionUserName());
				setProperty("hibernate.connection.password", config.getConnectionPwd());
				setProperty("hibernate.archive.autodetection", "class, hbm");
			}
		});
		
//		configuration.setProperty(Environment."packagesToScan", "net.d80harri.coach.domain.exercise");
		this.sessionFactory = configuration.buildSessionFactory();
	}

	private void initSessionManager() {
		this.sessionManager = new SessionManager(getSessionFactory());
	}

	private void initTransactionManager() {
		this.transactionManager = new TransactionManager(getSessionManager());
	}
	
	private void initExerciseRepository() {
		this.exerciseRepository = new ExerciseRepository(getSessionManager(), getTransactionManager());
	}

	private void initDbInitializer() {
		this.dbInitializer = new DBInitializer(getFlyway());
	}

	private void initFlyway() {
		DomainConfiguration config = getDomainConfiguration();
		flyway = new Flyway();
		flyway.setDataSource(config.getConnectionUrl(), config.getConnectionUserName(), config.getConnectionPwd());
		flyway.setTable("__META__");
	}

	public static class DomainConfiguration {
		private String driverClass = "org.h2.Driver";
		private String connectionUrl = "jdbc:h2:~/coach;AUTO_SERVER=TRUE";
		private String connectionUserName = "root";
		private String connectionPwd = "passme";
		private String hibernateDialect = "org.hibernate.dialect.H2Dialect";
		
		public String getDriverClass() {
			return driverClass;
		}

		public DomainConfiguration setDriverClass(String driverClass) {
			this.driverClass = driverClass;
			return this;
		}
		
		public String getHibernateDialect() {
			return hibernateDialect;
		}
		
		public DomainConfiguration setHibernateDialect(String hibernateDialect) {
			this.hibernateDialect = hibernateDialect;
			return this;
		}

		public String getConnectionUrl() {
			return connectionUrl;
		}

		public DomainConfiguration setConnectionUrl(String connectionUrl) {
			this.connectionUrl = connectionUrl;
			return this;
		}

		public String getConnectionUserName() {
			return connectionUserName;
		}

		public DomainConfiguration setConnectionUserName(String connectionUserName) {
			this.connectionUserName = connectionUserName;
			return this;
		}

		public String getConnectionPwd() {
			return connectionPwd;
		}

		public DomainConfiguration setConnectionPwd(String connectionPwd) {
			this.connectionPwd = connectionPwd;
			return this;
		}

	}

}
