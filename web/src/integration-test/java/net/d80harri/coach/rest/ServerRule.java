package net.d80harri.coach.rest;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

public class ServerRule implements TestRule {

	@Override
	public Statement apply(Statement base, Description description) {
		return new Statement() {
			
			@Override
			public void evaluate() throws Throwable {
				ConfigurableApplicationContext run = SpringApplication.run(Application.class);
				base.evaluate();
				run.close();
			}
		};
	}

}
