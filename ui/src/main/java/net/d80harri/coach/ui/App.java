package net.d80harri.coach.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.d80harri.coach.ui.exercise.ExerciseControl;

public class App extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Coach!");
        ExerciseControl exerciseControl = new ExerciseControl();
                
        primaryStage.setScene(new Scene(exerciseControl, 300, 250));
        primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
