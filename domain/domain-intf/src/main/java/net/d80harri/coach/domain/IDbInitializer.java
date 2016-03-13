package net.d80harri.coach.domain;

public interface IDbInitializer {

	void cleanMigrate();

	void migrate();

	void clean();

}