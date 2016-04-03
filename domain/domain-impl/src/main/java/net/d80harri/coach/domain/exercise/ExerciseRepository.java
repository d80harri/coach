package net.d80harri.coach.domain.exercise;

import net.d80harri.coach.domain.Repository;
import net.d80harri.coach.domain.repository.ISessionManager;
import net.d80harri.coach.domain.repository.ITransactionManager;

public class ExerciseRepository extends Repository<Exercise> implements IExerciseRepository {

	public ExerciseRepository(ISessionManager sessionManager, ITransactionManager transactionManager) {
		super(sessionManager, transactionManager);
	}


}
