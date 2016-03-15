package net.d80harri.coach.domain;

import org.hibernate.Transaction;

import net.d80harri.coach.domain.repository.ITransactionHolder;
import net.d80harri.coach.domain.repository.ITransactionManager;

public class TransactionHolder implements ITransactionHolder {
	private Transaction transaction;
	private ITransactionManager manager;
	private int references = 1;

	public TransactionHolder(Transaction transaction, ITransactionManager manager) {
		this.transaction = transaction;
		this.manager = manager;
	}

	/* (non-Javadoc)
	 * @see net.d80harri.coach.domain.repository.ITransactionHolder#getTransaction()
	 */
	@Override
	public Transaction getTransaction() {
		return transaction;
	}
	
	/* (non-Javadoc)
	 * @see net.d80harri.coach.domain.repository.ITransactionHolder#begin()
	 */
	@Override
	public void begin() {
		if (references == 0) {
			transaction.begin();
		}
		references++;
	}

	/* (non-Javadoc)
	 * @see net.d80harri.coach.domain.repository.ITransactionHolder#close()
	 */
	@Override
	public void close() {
		references--;
		if (references == 0) {
			manager.commitTransaction(this);
		}
	}

}
