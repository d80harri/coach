package net.d80harri.coach.domain.exercise;

import java.util.List;

import net.d80harri.coach.domain.repository.SessionHolder;
import net.d80harri.coach.domain.repository.SessionManager;
import net.d80harri.coach.domain.repository.TransactionHolder;
import net.d80harri.coach.domain.repository.TransactionManager;

public class ExerciseRepository {
	private SessionManager sessionManager;
	private TransactionManager transactionManager;

	public ExerciseRepository(SessionManager sessionManager, TransactionManager transactionManager) {
		this.sessionManager = sessionManager;
		this.transactionManager = transactionManager;
	}

	public List<Exercise> getAll() {
		try (SessionHolder session = sessionManager.getOrCreateSession()) {
			return (List<Exercise>) session.createQuery("FROM Exercise").list();
		}
	}

	public Exercise getByID(String id) {
		try(SessionHolder session = sessionManager.getOrCreateSession()) {
			return session.getByID(Exercise.class, id);
		}
	}

	public void saveOrUpdate(Exercise exercise) {
		try (SessionHolder session = sessionManager.getOrCreateSession()) {
			try (TransactionHolder tx = transactionManager.beginOrGet()) {
				session.saveOrUpdate(exercise);
			}
		}
	}

}
