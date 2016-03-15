package net.d80harri.coach.domain;

import net.d80harri.coach.domain.exercise.ExerciseRepository;
import net.d80harri.coach.domain.exercise.IExerciseRepository;
import net.d80harri.coach.domain.repository.IDomainCoreContext;

public class DomainContext implements IDomainContext {
	private IExerciseRepository exerciseRepository;
	private IDomainCoreContext coreContext;

	public DomainContext(IDomainCoreContext coreContext) {
		this.coreContext = coreContext;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.d80harri.coach.domain.IDomainContext#getExerciseRepository()
	 */
	@Override
	public IExerciseRepository getExerciseRepository() {
		if (this.exerciseRepository == null) {
			initExerciseRepository();
		}
		return this.exerciseRepository;
	}

	private void initExerciseRepository() {
		this.exerciseRepository = new ExerciseRepository(coreContext.getSessionManager(),
				coreContext.getTransactionManager());
	}

}
