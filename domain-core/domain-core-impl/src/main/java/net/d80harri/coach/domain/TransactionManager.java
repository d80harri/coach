package net.d80harri.coach.domain;

import java.util.Stack;

import net.d80harri.coach.domain.repository.ISessionManager;
import net.d80harri.coach.domain.repository.ITransactionHolder;
import net.d80harri.coach.domain.repository.ITransactionManager;

public class TransactionManager implements ITransactionManager {
	
	private ISessionManager sessionManager;
	private ThreadLocal<Stack<TransactionHolder>> transactions = new ThreadLocal<>();

	public TransactionManager(ISessionManager sessionManager) {
		this.sessionManager = sessionManager;
	}

	/* (non-Javadoc)
	 * @see net.d80harri.coach.domain.repository.ITransactionManager#beginOrGet()
	 */
	@Override
	public ITransactionHolder beginOrGet() {
		TransactionHolder currentTransaction = getCurrentTransaction();
		if (currentTransaction == null) {
			currentTransaction = new TransactionHolder(sessionManager.createTransaction(), this);
			getTransactions().push(currentTransaction);
		}

		return currentTransaction;
	}

	/* (non-Javadoc)
	 * @see net.d80harri.coach.domain.repository.ITransactionManager#getCurrentTransaction()
	 */
	@Override
	public TransactionHolder getCurrentTransaction() {
		TransactionHolder result;
		if (getTransactions().isEmpty()) {
			result = null;
		} else {
			result = getTransactions().peek();
		}
		return result;
	}

	protected Stack<TransactionHolder> getTransactions() {
		Stack<TransactionHolder> result = transactions.get();
		if (result == null) {
			result = new Stack<>();
			transactions.set(result);
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see net.d80harri.coach.domain.repository.ITransactionManager#commitTransaction(net.d80harri.coach.domain.repository.TransactionHolder)
	 */
	@Override
	public void commitTransaction(ITransactionHolder transactionHolder) {
		ITransactionHolder nextHolder = getTransactions().pop();
		if (nextHolder != transactionHolder) {
			throw new IllegalStateException();
		}
		transactionHolder.getTransaction().commit();
	}

}
