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
		target.saveOrUpdate(new Exercise("b9084b88-2267-47ee-9319-610edd22ba97", "First Ex", "First Ex desc"));
		target.saveOrUpdate(new Exercise("8bdbfa8b-07c0-4798-8ff7-6b69cb222b81", "Second Ex", "Second Ex desc"));
	}

	@Test
	@DatabaseSetup(value="firstAndSecond.xml")
	public void testGetAll() {
		List<Exercise> result = target.getAll();
		assertThat(result).hasSize(2).extracting(e -> e.getId(), e -> e.getName(), e -> e.getDescription())
				.containsExactly(new Tuple("b9084b88-2267-47ee-9319-610edd22ba97", "First Ex", "First Ex desc"),
						new Tuple("8bdbfa8b-07c0-4798-8ff7-6b69cb222b81", "Second Ex", "Second Ex desc"));
	}

	@Test
	@DatabaseSetup("firstAndSecond.xml")
	public void testGetById() {
		Exercise result = target.getByID("b9084b88-2267-47ee-9319-610edd22ba97");
		DomainAssertions.assertThat(result).isNotNull().hasId("b9084b88-2267-47ee-9319-610edd22ba97")
				.hasName("First Ex").hasDescription("First Ex desc");

		assertThat(target.getByID("nonExistingId")).isNull();
	}
}
