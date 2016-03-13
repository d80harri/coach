package net.d80harri.coach.domain;

import org.flywaydb.core.Flyway;
import org.hibernate.SessionFactory;

import net.d80harri.coach.domain.exercise.IExerciseRepository;
import net.d80harri.coach.domain.repository.ISessionManager;
import net.d80harri.coach.domain.repository.ITransactionManager;

public interface IDomainContext {

	Flyway getFlyway();

	IExerciseRepository getExerciseRepository();

	ITransactionManager getTransactionManager();

	ISessionManager getSessionManager();

	SessionFactory getSessionFactory();

	IDbInitializer getDbInitializer();

}