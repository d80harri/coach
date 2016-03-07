package net.d80harri.coach.domain.repository;

import java.io.Closeable;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class SessionHolder implements Closeable {
	private Session session;
	private SessionManager sessionManager;
	private int references = 1;
	
	public SessionHolder(Session session, SessionManager sessionManager) {
		this.session = session;
		this.sessionManager = sessionManager;
	}

	@Override
	public void close() {
		references--;
		if (references == 0) {
			sessionManager.close(this);
		}
	}
	
	public void attach() {
		references++;
	}

	public Query createQuery(String query) {
		return session.createQuery(query);
	}

	public void saveOrUpdate(Object entity) {
		session.saveOrUpdate(entity);
	}

	public <T> T getByID(Class<T> type, String id) {
		return (T)session.get(type, id);
	}

	public int getAttachedCount() {
		return references;
	}

	public Transaction beginTransaction() {
		return this.session.beginTransaction();
	}

	public Session getSession() {
		return this.session;
	}

}
