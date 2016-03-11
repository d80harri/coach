package net.d80harri.coach.domain.repository;

import java.util.Stack;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TransactionManager {
	private Logger logger = LoggerFactory.getLogger(TransactionManager.class);
	
	private SessionManager sessionManager;
	private ThreadLocal<Stack<TransactionHolder>> transactions = new ThreadLocal<>();

	public TransactionManager(SessionManager sessionManager) {
		this.sessionManager = sessionManager;
	}

	public TransactionHolder beginOrGet() {
		logger.debug("Begin transaction");
		TransactionHolder currentTransaction = getCurrentTransaction();
		if (currentTransaction == null) {
			currentTransaction = new TransactionHolder(sessionManager.createTransaction(), this);
			getTransactions().push(currentTransaction);
		}

		return currentTransaction;
	}

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

	public void commitTransaction(TransactionHolder transactionHolder) {
		TransactionHolder nextHolder = getTransactions().pop();
		if (nextHolder != transactionHolder) {
			throw new IllegalStateException();
		}
		transactionHolder.getTransaction().commit();
		logger.debug("Transaction commited");
	}

}
