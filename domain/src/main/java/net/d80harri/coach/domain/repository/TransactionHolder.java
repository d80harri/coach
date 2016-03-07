package net.d80harri.coach.domain.repository;

import org.hibernate.Transaction;

public class TransactionHolder implements AutoCloseable {
	private Transaction transaction;
	private TransactionManager manager;
	private int references = 1;

	public TransactionHolder(Transaction transaction, TransactionManager manager) {
		this.transaction = transaction;
		this.manager = manager;
	}

	public Transaction getTransaction() {
		return transaction;
	}
	
	public void begin() {
		if (references == 0) {
			transaction.begin();
		}
		references++;
	}

	@Override
	public void close() {
		references--;
		if (references == 0) {
			manager.commitTransaction(this);
		}
	}

}
