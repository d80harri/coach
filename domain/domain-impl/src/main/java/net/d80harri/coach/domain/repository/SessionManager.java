package net.d80harri.coach.domain.repository;

import java.nio.channels.IllegalSelectorException;
import java.util.Stack;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class SessionManager implements ISessionManager {
	private SessionFactory sessionFactory;
	protected ThreadLocal<Stack<SessionHolder>> sessions = new ThreadLocal<>();

	public SessionManager(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/* (non-Javadoc)
	 * @see net.d80harri.coach.domain.repository.ISessionManager#getOrCreateSession()
	 */
	@Override
	public SessionHolder getOrCreateSession() {
		SessionHolder result = null;

		Stack<SessionHolder> stack = getSessions();
		if (stack.isEmpty()) {
			Session session = sessionFactory.openSession();
			result = new SessionHolder(session, this);
			stack.push(result);
		} else {
			result = stack.peek();
			result.attach();
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see net.d80harri.coach.domain.repository.ISessionManager#createTransaction()
	 */
	@Override
	public Transaction createTransaction() {
		return getCurrentSession().beginTransaction();
	}

	/* (non-Javadoc)
	 * @see net.d80harri.coach.domain.repository.ISessionManager#getCurrentSession()
	 */
	@Override
	public ISessionHolder getCurrentSession() {
		return getSessions().peek();
	}

	private Stack<SessionHolder> getSessions() {
		Stack<SessionHolder> stack = sessions.get();
		if (stack == null) {
			stack = new Stack<>();
			sessions.set(stack);
		}
		return stack;
	}

	/* (non-Javadoc)
	 * @see net.d80harri.coach.domain.repository.ISessionManager#close(net.d80harri.coach.domain.repository.SessionHolder)
	 */
	@Override
	public void close(ISessionHolder sessionHolder) {
		ISessionHolder topHolder = getSessions().pop();
		if (topHolder != sessionHolder)
			throw new IllegalSelectorException();
		
		topHolder.getSession().close();
	}

}
