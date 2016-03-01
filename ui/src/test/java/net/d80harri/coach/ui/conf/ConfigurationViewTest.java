package net.d80harri.coach.ui.conf;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.ClassRule;
import org.junit.Test;

import net.d80harri.coach.ui.testutils.JfxRule;

public class ConfigurationViewTest {

	@ClassRule
	public static JfxRule jfxRule = new JfxRule();
	
	private ConfigurationView target = new ConfigurationView();
	
	@Test
	public void testClickDebug() {
		assertThat(target.getModel().isDebug()).isFalse();
		target.cbDebug.setSelected(true);
		
		assertThat(target.getModel().isDebug()).isTrue();
	}
}
