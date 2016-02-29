package net.d80harri.coach.ui;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;

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
	public void testChangeDebug() {
		target.getModel().getConfig().setDebug(false);
		assertThat(target.exerciseListView.getModel().isDebug()).isFalse();
		
		target.getModel().getConfig().setDebug(true);
		assertThat(target.exerciseListView.getModel().isDebug()).isTrue();
	}
}
