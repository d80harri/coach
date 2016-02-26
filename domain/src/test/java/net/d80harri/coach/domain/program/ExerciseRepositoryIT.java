package net.d80harri.coach.domain.program;

import java.util.List;
import java.util.Stack;

import org.assertj.core.api.Assertions;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.Before;
import org.junit.Test;

import net.d80harri.coach.domain.exercise.Exercise;
import net.d80harri.coach.domain.exercise.ExerciseRepository;
import net.d80harri.coach.domain.repository.SessionManager;

public class ExerciseRepositoryIT {
	private ExerciseRepository repository;
	
	@Before
	public void init() {
		ThreadLocal<Stack<Session>> sessions = new ThreadLocal<>();
		SessionFactory sessionFactory = createSessionFactory();
		SessionManager sessionManager = new SessionManager(sessionFactory, sessions);
		repository = new ExerciseRepository(sessionManager);
	}
	
	
	
	private Configuration createConfig() {
		Configuration configuration = new Configuration();
		return configuration
				.addAnnotatedClass(Exercise.class)
			    .setProperty("hibernate.connection.driver_class", "org.h2.Driver")
			    .setProperty("hibernate.connection.url", "jdbc:h2:~/test")
			    .setProperty("hibernate.default_schema", "PUBLIC")
			    .setProperty("connection.pool_size", "1")
			    .setProperty("dialect", "org.hibernate.dialect.H2Dialect")
			    .setProperty("cache.provider_class", "org.hibernate.cache.internal.NoCacheProvider")
			    .setProperty("show_sql", "true")
			    .setProperty("hibernate.hbm2ddl.auto", "create-drop");
	}
	
	private SessionFactory createSessionFactory() {
		Configuration config = createConfig();
		return config.buildSessionFactory(new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry());
	}
	
	@Test
	public void readAll() {
		List<Exercise> result = repository.getAll();
		Assertions.assertThat(result).hasSize(0);
	}
}
