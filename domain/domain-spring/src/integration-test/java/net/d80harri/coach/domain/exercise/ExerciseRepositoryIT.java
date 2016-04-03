package net.d80harri.coach.domain.exercise;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.assertj.core.groups.Tuple;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import net.d80harri.coach.domain.DomainAssertions;
import net.d80harri.domain.core.DatabaseTestRule;
import net.d80harri.domain.core.DatabaseTestRule.DBStateLogging;
import net.d80harri.domain.core.DatabaseTestRule.DatabaseSetup;
import net.d80harri.domain.core.DatabaseTestRule.ExpectedDatabase;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:domain.it.application-ctx.xml"})
public class ExerciseRepositoryIT {
	@Autowired
	private IExerciseRepository target; 
 
	@Rule
	@Autowired
	public DatabaseTestRule dbRule;	

	@Test
	@DatabaseSetup(value="empty.xml") 
	@DBStateLogging(value="EXERCISE")
	@ExpectedDatabase(table = "EXERCISE", value = "firstAndSecond.xml")
	public void testSaveOrUpdate() {
		target.saveOrUpdate(new AtomicExercise("00000000-0000-0000-0000-000000000001", "First Ex", "First Ex desc"));
		target.saveOrUpdate(new AtomicExercise("00000000-0000-0000-0000-000000000002", "Second Ex", "Second Ex desc"));
	}

	@Test
	@DatabaseSetup(value="firstAndSecond.xml")
	public void testGetAll() {
		List<Exercise> result = target.getAll(Exercise.class);
		assertThat(result).hasSize(2).extracting(e -> e.getId(), e -> e.getName(), e -> e.getDescription())
				.containsExactly(new Tuple("00000000-0000-0000-0000-000000000001", "First Ex", "First Ex desc"),
						new Tuple("00000000-0000-0000-0000-000000000002", "Second Ex", "Second Ex desc"));
	}

	@Test
	@DatabaseSetup("firstAndSecond.xml")
	public void testGetById() {
		Exercise result = target.getByID(Exercise.class, "00000000-0000-0000-0000-000000000001");
		DomainAssertions.assertThat(result).isNotNull().hasId("00000000-0000-0000-0000-000000000001")
				.hasName("First Ex").hasDescription("First Ex desc");

		assertThat(target.getByID(Exercise.class, "nonExistingId")).isNull();
	}
}
