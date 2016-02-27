package net.d80harri.coach.ui;

import java.util.UUID;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistryBuilder;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import net.d80harri.coach.domain.exercise.Exercise;
import net.d80harri.coach.domain.exercise.ExerciseRepository;
import net.d80harri.coach.domain.repository.ConfigurationBuilder;
import net.d80harri.coach.domain.repository.SessionManager;
import net.d80harri.coach.domain.repository.TransactionManager;
import net.d80harri.coach.ui.conf.ConfigurationModel;
import net.d80harri.coach.ui.conf.ConfigurationView;
import net.d80harri.coach.ui.exercise.ExerciseListView;
import net.d80harri.coach.ui.exercise.ExerciseModel;

public class App extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		ExerciseRepository exerciseRepository = createExerciseRepository();
		exerciseRepository.saveOrUpdate(new Exercise(UUID.randomUUID(), "First Ex", "First Ex desc"));
		exerciseRepository.saveOrUpdate(new Exercise(UUID.randomUUID(), "Second Ex", "Second Ex desc"));
		
		primaryStage.setTitle("Coach!");   
		
		ConfigurationModel configModel = new ConfigurationModel();
		
        ExerciseListView exerciseListControl = new ExerciseListView(configModel);
		ConfigurationView configView = new ConfigurationView(configModel);
        
        ObservableList<ExerciseModel> model = FXCollections.observableArrayList(createUiMapper().mapAsList(exerciseRepository.getAll(), ExerciseModel.class));
		
        HBox hbox = new HBox();
        hbox.getChildren().add(exerciseListControl);
        hbox.getChildren().add(configView);
        
        
		exerciseListControl.setModel(model);
		
		primaryStage.setScene(new Scene(hbox, 300, 250));
        primaryStage.show();
	}
	
	private static UiMapper uiMapper = null;
	public static UiMapper createUiMapper() {
		if (uiMapper == null) {
			uiMapper = new UiMapper();
		}
		return uiMapper;
	}
	
	public static ExerciseRepository createExerciseRepository() {
		return new ExerciseRepository(createSessionManager(), createTransactionManager());
	}
	
	private static SessionManager sessionManager = null;
	public static SessionManager createSessionManager() {
		if (sessionManager == null) {
			sessionManager = new SessionManager(createSessionFactory());
		}
		return sessionManager;
	}
	
	private static TransactionManager transactionManager = null;
	public static TransactionManager createTransactionManager() {
		if (transactionManager == null){
			transactionManager = new TransactionManager(createSessionManager());
		}
		return transactionManager;
	}
	
	private static SessionFactory sessionFactory = null;
	private static SessionFactory createSessionFactory() {
		if (sessionFactory == null) {
			Configuration configuration = createConfiguration();
			sessionFactory = configuration.buildSessionFactory(new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry());
		}
		return sessionFactory;
	}

	private static Configuration configuration = null;
	private static Configuration createConfiguration() {
		if (configuration == null) {
			configuration = new ConfigurationBuilder().build();
		}
		return configuration;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
