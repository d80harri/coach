package net.d80harri.coach.ui;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;

import net.d80harri.coach.ui.conf.ConfigurationViewModel;
import net.d80harri.coach.ui.exercise.ExerciseListViewModel;
import net.d80harri.coach.ui.exercise.ExerciseViewModel;
import net.d80harri.coach.ui.testutils.JfxRule;

public class MainModelTest {
	@ClassRule
	public static JfxRule jfxRule = new JfxRule();
	
	private MainModel target;
	private ExerciseListViewModel exerciseListViewModel;
	private ExerciseViewModel selectedExercise;
	private ConfigurationViewModel config;
	
	@Before
	public void initTarget() {
		exerciseListViewModel = new ExerciseListViewModel();
		selectedExercise = new ExerciseViewModel();
		config = new ConfigurationViewModel(); 
		
		target = new MainModel(exerciseListViewModel, selectedExercise, config);
	}
	
	@Test
	public void testBindingDebugConfig() {
		target.getConfig().setDebug(true);
		assertThat(target.getSelectedExercise().isIdVisible()).isTrue();
		assertThat(target.getExerciseList().isDebug()).isTrue();
		
		target.getConfig().setDebug(false);
		assertThat(target.getSelectedExercise().isIdVisible()).isFalse();
		assertThat(target.getExerciseList().isDebug()).isFalse();
	}
}
