package net.d80harri.coach.domain.exercise;

import java.util.List;

import org.hibernate.Session;

import net.d80harri.coach.domain.repository.SessionHolder;
import net.d80harri.coach.domain.repository.SessionManager;

public class ExerciseRepository {
	private SessionManager sessionManager;

	public ExerciseRepository(SessionManager sessionManager) {
		this.sessionManager = sessionManager;
	}

	public List<Exercise> getAll() {
		try (SessionHolder session = sessionManager.getOrCreateSession()) {
			return (List<Exercise>) session.createQuery("FROM Exercise").list();
		}
	}

}
