package net.d80harri.coach.domain.repository;

import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;

import net.d80harri.coach.domain.exercise.Exercise;

@Component
public class ConfigurationBuilder {
	private String hbm2ddlAuto = "create-drop";
	public static final String SHOW_SQL = "true";
	public static final String CACHE_PROVIDER = "org.hibernate.cache.internal.NoCacheProvider";
	public static final String DIALECT_H2_DIALECT = "org.hibernate.dialect.H2Dialect";
	public static final String CONNECTION_POOL_SIZE = "1";
	public static final String DEFAULT_SCHEMA = "PUBLIC";
	public static final String CONNECTION_URL = "jdbc:h2:~/test";
	public static final String CONNECTION_USER_NAME = "";
	public static final String CONNECTION_PWD = "";
	public static final String DRIVER_CLASS = "org.h2.Driver";

	public Configuration build() {
		Configuration configuration = new Configuration();
		return configuration
				.addAnnotatedClass(Exercise.class)
			    .setProperty("hibernate.connection.driver_class", DRIVER_CLASS)
			    .setProperty("hibernate.connection.url", CONNECTION_URL)
			    .setProperty("hibernate.default_schema", DEFAULT_SCHEMA)
			    .setProperty("connection.pool_size", CONNECTION_POOL_SIZE)
			    .setProperty("dialect", DIALECT_H2_DIALECT)
			    .setProperty("cache.provider_class", CACHE_PROVIDER)
			    .setProperty("show_sql", SHOW_SQL)
			    .setProperty("hibernate.hbm2ddl.auto", hbm2ddlAuto)
			    .setProperty("hibernate.connection.username", CONNECTION_USER_NAME)
			    .setProperty("hibernate.connection.password", CONNECTION_PWD);
	}
	
	public ConfigurationBuilder setHbm2DllAuto(String hbml2dll) {
		this.hbm2ddlAuto = hbml2dll;
		return this;
	}
}
