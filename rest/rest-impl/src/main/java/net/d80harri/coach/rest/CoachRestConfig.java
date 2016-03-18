package net.d80harri.coach.rest;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ejb.access.LocalStatelessSessionProxyFactoryBean;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;

import net.d80harri.coach.domain.SessionManager;
import net.d80harri.coach.domain.TransactionManager;
import net.d80harri.coach.domain.exercise.ExerciseRepository;
import net.d80harri.coach.domain.exercise.IExerciseRepository;
import net.d80harri.coach.domain.repository.ISessionManager;
import net.d80harri.coach.domain.repository.ITransactionManager;

@Configuration
public class CoachRestConfig {


	@Bean
	public SessionFactory sessionFactory(LocalSessionFactoryBean sessionFactoryBean) {
		return sessionFactoryBean.getObject();
	}
	
	@Bean
	public LocalSessionFactoryBean sessionFactoryBean(DataSource datasource) {
		LocalSessionFactoryBean result = new LocalSessionFactoryBean();
		result.setDataSource(datasource);
		return result;
	}
	
	@Bean
	public ITransactionManager transactionManager(ISessionManager sessionManager) {
		return new TransactionManager(sessionManager);
	}
	
	@Bean
	public ISessionManager sessionManager(SessionFactory sessionFactory) {
		return new SessionManager(sessionFactory);
	}
	
	@Bean
	public IExerciseRepository exerciseRepository(ITransactionManager transactionManager, ISessionManager sessionManager) {
		return new ExerciseRepository(sessionManager, transactionManager);
	}
}
