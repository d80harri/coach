package net.d80harri.coach.domain.repository;

import java.nio.channels.IllegalSelectorException;
import java.util.Stack;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SessionManager {
	private SessionFactory sessionFactory;
	protected ThreadLocal<Stack<SessionHolder>> sessions = new ThreadLocal<>();

	@Autowired
	public SessionManager(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

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

	public void close(SessionHolder sessionHolder) {
		SessionHolder topHolder = getSessions().pop();
		if (topHolder != sessionHolder)
			throw new IllegalSelectorException();
		
		topHolder.getSession().close();
	}

}
