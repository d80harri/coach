package net.d80harri.coach.ui;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;

import net.d80harri.coach.ui.conf.ConfigurationViewModel;
import net.d80harri.coach.ui.testutils.JfxRule;

public class MainViewTest {
	@ClassRule
	public static JfxRule jfxRule = new JfxRule();
	
	private MainView target;
	
	@Before
	public void init() {
		target = new MainView();
	}
	
	@Test
	public void testNewlyCreatedMainView() {
		assertThat(target.configView.getModel()).isSameAs(target.getModel().getConfig());
		assertThat(target.exerciseListView.getModel()).isSameAs(target.getModel().getExerciseList());
		assertThat(target.exerciseView.getModel()).isSameAs(target.getModel().getSelectedExercise());
	}
	
	@Test
	public void testSetModelToNull() {
		assertThat(target.configView.getModel()).isNotNull();
		assertThat(target.exerciseListView.getModel()).isNotNull();
		assertThat(target.exerciseView.getModel()).isNotNull();
		
		target.setModel(null);
		
		assertThat(target.configView.getModel()).isNull();
		assertThat(target.exerciseListView.getModel()).isNull();
		assertThat(target.exerciseView.getModel()).isNull();
	}
	
	@Test
	public void testChangeDebug() {
		target.getModel().getConfig().setDebug(false);
		assertThat(target.exerciseListView.getModel().isDebug()).isFalse();
		
		target.getModel().getConfig().setDebug(true);
		assertThat(target.exerciseListView.getModel().isDebug()).isTrue();
	}
	
	@Test
	public void testChangeConfig() {
		target.getModel().getConfig().setDebug(false);
		assertThat(target.exerciseListView.getModel().isDebug()).isFalse();
		
		target.getModel().setConfig(new ConfigurationViewModel(true));
		assertThat(target.exerciseListView.getModel().isDebug()).isTrue();
	}
}
