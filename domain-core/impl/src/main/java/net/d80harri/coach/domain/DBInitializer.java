package net.d80harri.coach.domain;

import org.flywaydb.core.Flyway;

import net.d80harri.coach.domain.repository.IDbInitializer;

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
}
