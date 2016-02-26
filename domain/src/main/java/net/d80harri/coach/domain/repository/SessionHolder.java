package net.d80harri.coach.domain.repository;

import java.io.Closeable;
import java.util.UUID;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class SessionHolder implements Closeable {
	private Session session;
	private int references = 0;
	
	public SessionHolder(Session session, int references) {
		this.session = session;
		this.references = references;
	}

	@Override
	public void close() {
		if (references == 0) {
			session.close();
		}
		references--;
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

	public <T> T getByID(Class<T> type, UUID id) {
		return (T)session.get(type, id);
	}

	public int getAttachedCount() {
		return references;
	}

	public Transaction beginTransaction() {
		return this.session.beginTransaction();
	}

}
