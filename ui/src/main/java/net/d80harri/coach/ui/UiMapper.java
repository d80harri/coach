package net.d80harri.coach.ui;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import net.d80harri.coach.domain.exercise.Exercise;
import net.d80harri.coach.ui.exercise.AtomicExerciseModel;

public class UiMapper extends ConfigurableMapper {
	@Override
	protected void configure(MapperFactory factory) {
		configureExercise2ExerciseModel(factory);
	}

	private void configureExercise2ExerciseModel(MapperFactory factory) {
		factory.classMap(Exercise.class, AtomicExerciseModel.class)
		 .byDefault().register();
	}
}
