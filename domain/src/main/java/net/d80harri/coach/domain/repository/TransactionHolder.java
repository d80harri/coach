package net.d80harri.coach.domain.repository;

import org.hibernate.Transaction;

public class TransactionHolder implements AutoCloseable {
	private Transaction transaction;
	private int references = 0;

	public TransactionHolder(Transaction transaction) {
		this.transaction = transaction;
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
			transaction.commit();
		}
	}

}
