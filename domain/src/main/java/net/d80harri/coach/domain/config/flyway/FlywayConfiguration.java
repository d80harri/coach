package net.d80harri.coach.domain.config.flyway;

import javax.sql.DataSource;

import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import net.d80harri.coach.domain.config.db.DBConfiguration;

@Configuration
@Import({ DBConfiguration.class })
@ComponentScan(basePackageClasses=FlywayConfiguration.class)
public class FlywayConfiguration {
	@Bean
	public Flyway getFlyway(DataSource datasource) {
		Flyway result = new Flyway();
		result.setDataSource(datasource);
		result.setTable("__META__");
		return result;
	}
	
	@Bean
	 public static PropertySourcesPlaceholderConfigurer   propertySourcesPlaceholderConfigurer() {
	     PropertySourcesPlaceholderConfigurer p =  new PropertySourcesPlaceholderConfigurer();
	     p.setIgnoreResourceNotFound(true);

	    return p;
	 }
}
