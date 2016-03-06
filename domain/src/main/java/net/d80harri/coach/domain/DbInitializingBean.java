package net.d80harri.coach.domain;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DbInitializingBean implements ApplicationListener<ContextRefreshedEvent> {

	private final Flyway flyway;
	private final boolean automigrate;

	@Autowired
	public DbInitializingBean(Flyway flyway, @Value("${db.migrate.auto:true}") boolean automigrate) {
		this.flyway = flyway;
		this.automigrate = automigrate;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if (automigrate)
			this.migrate();
	}
	
	public void migrate() {
		this.flyway.migrate();
	}
	
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(DomainConfiguration.class);
		DbInitializingBean initBean = ctx.getBean(DbInitializingBean.class);
		initBean.migrate();
	}
}
