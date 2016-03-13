package net.d80harri.coach.domain.exercise;

import java.util.List;

import net.d80harri.coach.domain.repository.ISessionHolder;
import net.d80harri.coach.domain.repository.ISessionManager;
import net.d80harri.coach.domain.repository.ITransactionHolder;
import net.d80harri.coach.domain.repository.ITransactionManager;
import net.d80harri.coach.domain.repository.SessionHolder;

public class ExerciseRepository implements IExerciseRepository {
	private ISessionManager sessionManager;
	private ITransactionManager transactionManager;

	public ExerciseRepository(ISessionManager sessionManager, ITransactionManager transactionManager) {
		this.sessionManager = sessionManager;
		this.transactionManager = transactionManager;
	}

	/* (non-Javadoc)
	 * @see net.d80harri.coach.domain.exercise.IExerciseRepository#getAll()
	 */
	@Override
	public List<Exercise> getAll() {
		try (ISessionHolder session = sessionManager.getOrCreateSession()) {
			return (List<Exercise>) session.createQuery("FROM Exercise").list();
		}
	}

	/* (non-Javadoc)
	 * @see net.d80harri.coach.domain.exercise.IExerciseRepository#getByID(java.lang.String)
	 */
	@Override
	public Exercise getByID(String id) {
		try(ISessionHolder session = sessionManager.getOrCreateSession()) {
			return session.getByID(Exercise.class, id);
		}
	}

	/* (non-Javadoc)
	 * @see net.d80harri.coach.domain.exercise.IExerciseRepository#saveOrUpdate(net.d80harri.coach.domain.exercise.Exercise)
	 */
	@Override
	public void saveOrUpdate(Exercise exercise) {
		try (ISessionHolder session = sessionManager.getOrCreateSession()) {
			try (ITransactionHolder tx = transactionManager.beginOrGet()) {
				session.saveOrUpdate(exercise);
			}
		}
	}

}
