package net.d80harri.coach.domain;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.jdbc.Work;

import net.d80harri.coach.domain.repository.ISessionHolder;
import net.d80harri.coach.domain.repository.ISessionManager;

public class SessionHolder implements ISessionHolder {
	private Session session;
	private ISessionManager sessionManager;
	private int references = 1;
	
	public SessionHolder(Session session, ISessionManager sessionManager) {
		this.session = session;
		this.sessionManager = sessionManager;
	}

	/* (non-Javadoc)
	 * @see net.d80harri.coach.domain.repository.ISessionHolder#close()
	 */
	@Override
	public void close() {
		references--;
		if (references == 0) {
			sessionManager.close(this);
		}
	}
	
	/* (non-Javadoc)
	 * @see net.d80harri.coach.domain.repository.ISessionHolder#attach()
	 */
	@Override
	public void attach() {
		references++;
	}

	/* (non-Javadoc)
	 * @see net.d80harri.coach.domain.repository.ISessionHolder#createQuery(java.lang.String)
	 */
	@Override
	public Query createQuery(String query) {
		return session.createQuery(query);
	}

	/* (non-Javadoc)
	 * @see net.d80harri.coach.domain.repository.ISessionHolder#saveOrUpdate(java.lang.Object)
	 */
	@Override
	public void saveOrUpdate(Object entity) {
		session.saveOrUpdate(entity);
	}

	/* (non-Javadoc)
	 * @see net.d80harri.coach.domain.repository.ISessionHolder#getByID(java.lang.Class, java.lang.String)
	 */
	@Override
	public <T> T getByID(Class<T> type, String id) {
		return (T)session.get(type, id);
	}
	
	/* (non-Javadoc)
	 * @see net.d80harri.coach.domain.repository.ISessionHolder#doWork(org.hibernate.jdbc.Work)
	 */
	@Override
	public void doWork(Work work) {
		session.doWork(work);
	}

	/* (non-Javadoc)
	 * @see net.d80harri.coach.domain.repository.ISessionHolder#getAttachedCount()
	 */
	@Override
	public int getAttachedCount() {
		return references;
	}

	/* (non-Javadoc)
	 * @see net.d80harri.coach.domain.repository.ISessionHolder#beginTransaction()
	 */
	@Override
	public Transaction beginTransaction() {
		return this.session.beginTransaction();
	}
	
	/* (non-Javadoc)
	 * @see net.d80harri.coach.domain.repository.ISessionHolder#getSession()
	 */
	@Override
	public Session getSession() {
		return this.session;
	}

}
