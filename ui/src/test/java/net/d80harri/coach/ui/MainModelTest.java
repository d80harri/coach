package net.d80harri.coach.ui;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;

import net.d80harri.coach.ui.testutils.JfxRule;

public class MainModelTest {
	@ClassRule
	public static JfxRule jfxRule = new JfxRule();
	
	private MainModel target;
	
	@Before
	public void initTarget() {
		target = new MainModel();
	}
	
	@Test
	public void testBindingDebugConfig() {
		target.getConfig().setDebug(true);
		assertThat(target.getSelectedExercise().isIdVisible()).isTrue();
	}
}
