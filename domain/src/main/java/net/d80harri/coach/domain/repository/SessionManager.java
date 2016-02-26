package net.d80harri.coach.domain.repository;

import java.util.Stack;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class SessionManager {
	private SessionFactory sessionFactory;
	protected ThreadLocal<Stack<SessionHolder>> sessions = new ThreadLocal<>();

	public SessionManager(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public SessionHolder getOrCreateSession() {
		SessionHolder result = null;

		Stack<SessionHolder> stack = getSessions();
		if (stack.isEmpty()) {
			Session session = sessionFactory.openSession();
			result = new SessionHolder(session, 1);
			stack.push(result);
		} else {
			result = stack.peek();
			result.attach();
		}
		return result;
	}

	public Transaction createTransaction() {
		return getCurrentSession().beginTransaction();
	}

	public SessionHolder getCurrentSession() {
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

}
