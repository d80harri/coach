package net.d80harri.coach.domain;

import org.flywaydb.core.Flyway;

import net.d80harri.coach.domain.DomainContext.DomainConfiguration;

public class DBInitializer implements IDbInitializer {
	private Flyway flyway;
	
	public DBInitializer(Flyway flyway) {
		this.flyway = flyway;
	}
	
	@Override
	public void clean() {
		this.flyway.clean();
	}
	
	@Override
	public void migrate() {
		this.flyway.migrate();
	}
	
	@Override
	public void cleanMigrate() {
		clean();
		migrate();
	}
	
	public static void main(String[] args) {
		DomainContext context = new DomainContext(new DomainConfiguration()
				.setConnectionUrl("jdbc:h2:~/coach.domain.it;AUTO_SERVER=TRUE")); // TODO: fill those from arguments
		IDbInitializer init = new DBInitializer(context.getFlyway());
		init.cleanMigrate();
	}
}