package net.d80harri.coach.domain.repository;

import org.flywaydb.core.Flyway;
import org.hibernate.SessionFactory;

public interface IDomainCoreContext {

	Flyway getFlyway();

	ITransactionManager getTransactionManager();

	ISessionManager getSessionManager();

	SessionFactory getSessionFactory();

	IDbInitializer getDbInitializer();

}