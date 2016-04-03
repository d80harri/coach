package net.d80harri.coach.domain;

import java.util.List;

import net.d80harri.coach.domain.repository.Entity;
import net.d80harri.coach.domain.repository.IRepository;
import net.d80harri.coach.domain.repository.ISessionHolder;
import net.d80harri.coach.domain.repository.ISessionManager;
import net.d80harri.coach.domain.repository.ITransactionHolder;
import net.d80harri.coach.domain.repository.ITransactionManager;

public class Repository<T extends Entity> implements IRepository<T> {

	private ISessionManager sessionManager;
	private ITransactionManager transactionManager;

	public Repository(ISessionManager sessionManager, ITransactionManager transactionManager) {
		this.sessionManager = sessionManager;
		this.transactionManager = transactionManager;
	}
	
	@Override
	public <R extends T> List<R> getAll(Class<R> type) {
		try (ISessionHolder session = sessionManager.getOrCreateSession()) {
			return (List<R>) (List<?>) session.createQuery("FROM " + type.getName()).list();
		}
	}

	@Override
	public <R extends T> R getByID(Class<R> type, String id) {
		try(ISessionHolder session = sessionManager.getOrCreateSession()) {
			return session.getByID(type, id);
		}
	}

	@Override
	public <R extends T> void saveOrUpdate(R exercise) {
		try (ISessionHolder session = sessionManager.getOrCreateSession()) {
			try (ITransactionHolder tx = transactionManager.beginOrGet()) {
				session.saveOrUpdate(exercise);
			}
		}
	}

}
