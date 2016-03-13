package net.d80harri.coach.domain.repository;

public interface ITransactionManager {

	ITransactionHolder beginOrGet();

	ITransactionHolder getCurrentTransaction();

	void commitTransaction(ITransactionHolder transactionHolder);

}