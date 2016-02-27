package net.d80harri.coach.domain.program;

import java.util.List;
import java.util.UUID;

import org.assertj.core.api.Assertions;
import org.assertj.core.groups.Tuple;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.Before;
import org.junit.Test;

import net.d80harri.coach.domain.exercise.Exercise;
import net.d80harri.coach.domain.exercise.ExerciseRepository;
import net.d80harri.coach.domain.repository.ConfigurationBuilder;
import net.d80harri.coach.domain.repository.SessionManager;
import net.d80harri.coach.domain.repository.TransactionManager;

public class ExerciseRepositoryIT {
	private Configuration configuration = new ConfigurationBuilder().build();
	private ExerciseRepository target;
	
	@Before
	public void init() {
		SessionFactory sessionFactory = createSessionFactory();
		SessionManager sessionManager = new SessionManager(sessionFactory);
		TransactionManager transactionManager = new TransactionManager(sessionManager);
		target = new ExerciseRepository(sessionManager, transactionManager);
	}
	
	private SessionFactory createSessionFactory() {
		return configuration.buildSessionFactory(new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry());
	}
	
	@Test
	public void testReadAll() {
		List<Exercise> result = target.getAll();
		Assertions.assertThat(result).hasSize(0);
	}
	
	@Test
	public void testSaveOrUpdate() {
		Exercise exercise = new Exercise(UUID.randomUUID(), "MyName", "MyDescription");
		target.saveOrUpdate(exercise);
		
		Exercise read = target.getByID(exercise.getId());
		Assertions.assertThat(read).isNotNull();
		Assertions.assertThat(read.getId()).isEqualTo(exercise.getId());
		Assertions.assertThat(read.getName()).isEqualTo(exercise.getName());
		Assertions.assertThat(read.getDescription()).isEqualTo(exercise.getDescription());
	}
	
	@Test
	public void test() {
		target.saveOrUpdate(new Exercise(UUID.randomUUID(), "First Ex", "First Ex desc"));
		target.saveOrUpdate(new Exercise(UUID.randomUUID(), "Second Ex", "Second Ex desc"));
		
		List<Exercise> allResult = target.getAll();
		Assertions.assertThat(allResult).hasSize(2).extracting("name", "description").containsExactly(new Tuple("First Ex", "First Ex desc"), new Tuple("Second Ex", "Second Ex desc"));
	}
}
