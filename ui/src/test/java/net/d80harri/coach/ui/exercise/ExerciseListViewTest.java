package net.d80harri.coach.ui.exercise;

import java.util.UUID;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;

import net.d80harri.coach.ui.testutils.JfxRule;

public class ExerciseListViewTest {

	@ClassRule
	public static JfxRule jfxRule = new JfxRule();
	
	private ExerciseListView target;
	
	@Before
	public void init()  {
		target = new ExerciseListView();
	}
	
	@Test
	public void testAddExercise() {
		target.getModel().add(new ExerciseModel(UUID.randomUUID(), "name", "desc"));
		
		Assertions.assertThat(target.listExercise.getItems()).hasSize(1);
	}
}
