package net.d80harri.coach.domain;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class DBInitializationRule implements TestRule {
	private DBInitializer init;
	
	public DBInitializationRule(DomainContext context) {
		init = context.getDbInitializer();
	}
	
	@Override
	public Statement apply(Statement base, Description description) {
		return new Statement() {
			
			@Override
			public void evaluate() throws Throwable {
				init.cleanMigrate();
				base.evaluate();
			}
		};
	}

}
