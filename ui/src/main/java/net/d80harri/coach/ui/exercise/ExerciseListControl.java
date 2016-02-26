package net.d80harri.coach.ui.exercise;

import java.io.IOException;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;

public class ExerciseListControl extends BorderPane {
	@FXML
	private TableView<AtomicExerciseModel> listExercise;
	
	private ObservableList<AtomicExerciseModel> model = FXCollections.observableArrayList();

	public ExerciseListControl() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("exercise_list.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);

		try {
			fxmlLoader.load();
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}

		TableColumn<AtomicExerciseModel, String> tc = new TableColumn<>();
		tc.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<AtomicExerciseModel,String>, ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(CellDataFeatures<AtomicExerciseModel, String> param) {
				return param.getValue().nameProperty();
			}
		});
		tc.setCellFactory(new Callback<TableColumn<AtomicExerciseModel,String>, TableCell<AtomicExerciseModel,String>>() {

			@Override
			public TableCell<AtomicExerciseModel, String> call(TableColumn<AtomicExerciseModel, String> param) {
				return new TextFieldTableCell<>();
			}
		});
		listExercise.getColumns().add(tc);
		bindModel();
	}
	
	public void setModel(ObservableList<AtomicExerciseModel> model) {
		this.model = model;
		bindModel();
	}

	public ObservableList<AtomicExerciseModel> getModel() {
		return model;
	}
	
	private void bindModel() {
		listExercise.setItems(model);
	}
}
