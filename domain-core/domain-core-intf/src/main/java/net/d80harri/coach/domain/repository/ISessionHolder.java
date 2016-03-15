package net.d80harri.coach.domain.repository;

import java.io.Closeable;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.jdbc.Work;

public interface ISessionHolder extends Closeable {

	void close();

	void attach();

	Query createQuery(String query);

	void saveOrUpdate(Object entity);

	<T> T getByID(Class<T> type, String id);

	void doWork(Work work);

	int getAttachedCount();

	Transaction beginTransaction();

	Session getSession();

}