package net.d80harri.coach.ui.exercise;

import java.util.UUID;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class ExerciseListViewIT extends ApplicationTest {
	private ExerciseListView target;
	
	@Override
	public void start(Stage stage) {
		target = new ExerciseListView();
		Scene scene = new Scene(target, 800, 600);
		stage.setScene(scene);
		stage.show();
	}

	@Test
	public void testAddExercise() {
		target.getModel().add(new ExerciseModel(UUID.randomUUID(), "name", "desc"));
		
		ListView<ExerciseView> txtTitle = (ListView<ExerciseView>)this.lookup("#list").queryFirst();
		Assertions.assertThat(txtTitle.getItems()).hasSize(1);
	}
}
