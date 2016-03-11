package net.d80harri.coach.domain.exercise;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.assertj.core.groups.Tuple;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;

import net.d80harri.coach.domain.DomainAssertions;
import net.d80harri.coach.domain.DomainTestConfiguration;
import net.d80harri.coach.domain.FlatDatasetExport;
// TODO: get rid of spring dependency in domain
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DomainTestConfiguration.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class })
public class ExerciseRepositoryIT {
	@Autowired
	private ExerciseRepository target;

	@Autowired
	private FlatDatasetExport export;

	@Test
	@DatabaseSetup(value = "empty.xml", type = DatabaseOperation.DELETE_ALL)
	@ExpectedDatabase(table = "EXERCISE", value = "firstAndSecond.xml")
	public void testSaveOrUpdate() {
		target.saveOrUpdate(new Exercise("b9084b88-2267-47ee-9319-610edd22ba97", "First Ex", "First Ex desc"));
		target.saveOrUpdate(new Exercise("8bdbfa8b-07c0-4798-8ff7-6b69cb222b81", "Second Ex", "Second Ex desc"));
		System.out.println(export.exportToString());
	}

	@Test
	@DatabaseSetup("firstAndSecond.xml")
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
