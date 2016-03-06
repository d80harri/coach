package net.d80harri.coach.domain;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DbInitializingBean implements ApplicationListener<ContextRefreshedEvent> {

	private final Flyway flyway;

	@Autowired
	public DbInitializingBean(Flyway flyway) {
		this.flyway = flyway;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		this.flyway.migrate();
		
	}
}
