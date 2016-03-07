package net.d80harri.coach.domain;

import org.flywaydb.core.Flyway;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FlywayRule implements TestRule {

	private Flyway flyway;
	
	@Autowired
	public FlywayRule(Flyway flyway) {
		this.flyway = flyway;
	}
	
	@Override
	public Statement apply(Statement base, Description description) {
		return new Statement() {
			
			@Override
			public void evaluate() throws Throwable {
				flyway.clean();
				flyway.migrate();
				base.evaluate();
			}
		};
	}

}
