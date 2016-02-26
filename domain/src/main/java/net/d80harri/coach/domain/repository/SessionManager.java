package net.d80harri.coach.domain.repository;

import java.util.Stack;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class SessionManager {
	private SessionFactory sessionFactory;
	private ThreadLocal<Stack<Session>> sessions;
	
	public SessionManager(SessionFactory sessionFactory, ThreadLocal<Stack<Session>> sessions) {
		this.sessionFactory = sessionFactory;
		this.sessions = sessions;
	}
	
	public SessionHolder getOrCreateSession() {
		SessionHolder result = null;
		
		Stack<Session> stack = sessions.get();
		if (stack == null) {
			stack = new Stack<>();
			sessions.set(stack);
		}
		if (stack.isEmpty()) {
			Session session = sessionFactory.openSession();
			result = new SessionHolder(session, true);
			stack.push(session);
		} else {
			result = new SessionHolder(stack.pop(), false);
		}
		return result;
	}

}
