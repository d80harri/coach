package net.d80harri.coach.domain.repository;

import java.io.Closeable;
import java.io.IOException;

import org.hibernate.Query;
import org.hibernate.Session;

public class SessionHolder implements Closeable {
	private Session session;
	private boolean created;
	
	public SessionHolder(Session session, boolean created) {
		this.session = session;
		this.created = created;
	}

	@Override
	public void close() {
		if (created) {
			session.close();
		}
	}

	public Query createQuery(String query) {
		return session.createQuery(query);
	}

}
