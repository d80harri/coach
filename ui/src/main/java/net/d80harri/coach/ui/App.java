package net.d80harri.coach.ui;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.d80harri.coach.domain.exercise.ExerciseRepository;
import net.d80harri.coach.ui.exercise.AtomicExerciseModel;
import net.d80harri.coach.ui.exercise.ExerciseListControl;

public class App extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		ExerciseRepository exerciseRepository = createExerciseRepository();
		
		primaryStage.setTitle("Coach!");                
        ExerciseListControl exerciseListControl = new ExerciseListControl();
		
        ObservableList<AtomicExerciseModel> model = FXCollections.observableArrayList();
		AtomicExerciseModel ex = new AtomicExerciseModel();
		ex.setDescripton("Desc");
		ex.setName("Desc");
		model.add(ex);
		
		exerciseListControl.setModel(model);
		
		primaryStage.setScene(new Scene(exerciseListControl, 300, 250));
        primaryStage.show();
	}
	
	public static ExerciseRepository createExerciseRepository() {
		return new ExerciseRepository();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
