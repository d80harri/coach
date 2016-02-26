package net.d80harri.coach.domain.repository;

import org.hibernate.cfg.Configuration;

import net.d80harri.coach.domain.exercise.Exercise;

public class ConfigurationBuilder {
	private static final String HBM2DDL_AUTO = "create-drop";
	private static final String SHOW_SQL = "true";
	private static final String CACHE_PROVIDER = "org.hibernate.cache.internal.NoCacheProvider";
	private static final String DIALECT_H2_DIALECT = "org.hibernate.dialect.H2Dialect";
	private static final String CONNECTION_POOL_SIZE = "1";
	private static final String DEFAULT_SCHEMA = "PUBLIC";
	private static final String CONNECTION_URL = "jdbc:h2:~/test";
	private static final String DRIVER_CLASS = "org.h2.Driver";

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
			    .setProperty("hibernate.hbm2ddl.auto", HBM2DDL_AUTO);
	}
}
