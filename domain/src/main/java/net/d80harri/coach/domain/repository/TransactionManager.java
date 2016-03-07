package net.d80harri.coach.domain.repository;

import java.util.Stack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransactionManager {
	private SessionManager sessionManager;
	private ThreadLocal<Stack<TransactionHolder>> transactions = new ThreadLocal<>();

	@Autowired
	public TransactionManager(SessionManager sessionManager) {
		this.sessionManager = sessionManager;
	}

	public TransactionHolder beginOrGet() {
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

	}

}
