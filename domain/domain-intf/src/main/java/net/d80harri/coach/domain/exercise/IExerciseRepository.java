package net.d80harri.coach.domain.exercise;

import java.util.List;

public interface IExerciseRepository {

	List<Exercise> getAll();

	Exercise getByID(String id);

	void saveOrUpdate(Exercise exercise);

}