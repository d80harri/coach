package net.d80harri.coach.domain.exercise;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.assertj.core.groups.Tuple;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import net.d80harri.coach.domain.DBInitializationRule;
import net.d80harri.coach.domain.DomainAssertions;
import net.d80harri.coach.domain.DomainContext;
import net.d80harri.coach.domain.DomainContext.DomainConfiguration;
import net.d80harri.coach.domain.FlatDatasetExport;

public class ExerciseRepositoryIT {
	private ExerciseRepository target; // TODO: initialize
	private FlatDatasetExport export;

	@Rule
	public DBInitializationRule dbRule;
	
	@Before
	public void init() {
		DomainConfiguration domainConfig = new DomainConfiguration().setConnectionUrl("jdbc:h2:~/coach.domain.it;AUTO_SERVER=TRUE");
		DomainContext context = new DomainContext(domainConfig);
		dbRule = new DBInitializationRule(context);
		target = context.getExerciseRepository();
	}
	
	@Test
//	@DatabaseSetup(value = "empty.xml", type = DatabaseOperation.DELETE_ALL)
//	@ExpectedDatabase(table = "EXERCISE", value = "firstAndSecond.xml")
	public void testSaveOrUpdate() {
		target.saveOrUpdate(new Exercise("b9084b88-2267-47ee-9319-610edd22ba97", "First Ex", "First Ex desc"));
		target.saveOrUpdate(new Exercise("8bdbfa8b-07c0-4798-8ff7-6b69cb222b81", "Second Ex", "Second Ex desc"));
		System.out.println(export.exportToString());
	}

	@Test
//	@DatabaseSetup("firstAndSecond.xml")
	public void testGetAll() {
		List<Exercise> result = target.getAll();
		assertThat(result).hasSize(2).extracting(e -> e.getId(), e -> e.getName(), e -> e.getDescription())
				.containsExactly(new Tuple("b9084b88-2267-47ee-9319-610edd22ba97", "First Ex", "First Ex desc"),
						new Tuple("8bdbfa8b-07c0-4798-8ff7-6b69cb222b81", "Second Ex", "Second Ex desc"));
	}

	@Test
//	@DatabaseSetup("firstAndSecond.xml")
	public void testGetById() {
		Exercise result = target.getByID("b9084b88-2267-47ee-9319-610edd22ba97");
		DomainAssertions.assertThat(result).isNotNull().hasId("b9084b88-2267-47ee-9319-610edd22ba97")
				.hasName("First Ex").hasDescription("First Ex desc");
		
		assertThat(target.getByID("nonExistingId")).isNull();
	}
}
