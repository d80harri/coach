package net.d80harri.coach.domain.repository;

import org.hibernate.Transaction;

public interface ITransactionHolder extends AutoCloseable {

	Transaction getTransaction();

	void begin();

	void close();

}