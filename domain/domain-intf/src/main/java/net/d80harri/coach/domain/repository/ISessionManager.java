package net.d80harri.coach.domain.repository;

import org.hibernate.Transaction;

public interface ISessionManager {

	ISessionHolder getOrCreateSession();

	Transaction createTransaction();

	ISessionHolder getCurrentSession();

	void close(ISessionHolder sessionHolder);

}