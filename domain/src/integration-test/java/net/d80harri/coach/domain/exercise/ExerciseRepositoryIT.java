package net.d80harri.coach.domain.exercise;

import java.util.List;
import java.util.UUID;

import javax.sql.DataSource;

import org.assertj.core.api.Assertions;
import org.assertj.core.groups.Tuple;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import net.d80harri.coach.domain.DomainConfiguration;
import net.d80harri.coach.domain.FlywayRule;
import net.d80harri.coach.domain.config.db.DbProperties;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DomainConfiguration.class)
public class ExerciseRepositoryIT {
	@Autowired
	private ExerciseRepository target;

	@Autowired
	DataSource connection;

	@Autowired
	DbProperties props;
	
	@Autowired
	@Rule
	public FlywayRule flywayRule;

	@Test
	public void testReadAll() {
		List<Exercise> result = target.getAll();
		Assertions.assertThat(result).hasSize(0);
	}

	@Test
	public void testSaveOrUpdate() {
		Exercise exercise = new Exercise(UUID.randomUUID().toString(), "MyName", "MyDescription");
		target.saveOrUpdate(exercise);

		Exercise read = target.getByID(exercise.getId());
		Assertions.assertThat(read).isNotNull();
		Assertions.assertThat(read.getId()).isEqualTo(exercise.getId());
		Assertions.assertThat(read.getName()).isEqualTo(exercise.getName());
		Assertions.assertThat(read.getDescription()).isEqualTo(exercise.getDescription());
	}

	@Test
	public void test() {
		target.saveOrUpdate(new Exercise(UUID.randomUUID().toString(), "First Ex", "First Ex desc"));
		target.saveOrUpdate(new Exercise(UUID.randomUUID().toString(), "Second Ex", "Second Ex desc"));

		List<Exercise> allResult = target.getAll();
		Assertions.assertThat(allResult).hasSize(2).extracting("name", "description")
				.containsExactly(new Tuple("First Ex", "First Ex desc"), new Tuple("Second Ex", "Second Ex desc"));
	}
}
