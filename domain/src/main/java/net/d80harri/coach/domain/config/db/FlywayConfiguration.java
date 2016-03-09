package net.d80harri.coach.domain.config.db;

import javax.sql.DataSource;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@Import({ DBConfiguration.class })
@ComponentScan(basePackageClasses=FlywayConfiguration.class)
public class FlywayConfiguration {
	@Bean
	public Flyway flyway(DataSource datasource, @Value("${db.migrate.auto:false}") boolean automigrate) {
		Flyway result = new Flyway();
		result.setDataSource(datasource);
		result.setTable("__META__");
		if (automigrate)
			result.migrate();
		return result;
	}
	
	@Bean
	 public static PropertySourcesPlaceholderConfigurer   propertySourcesPlaceholderConfigurer() {
	     PropertySourcesPlaceholderConfigurer p =  new PropertySourcesPlaceholderConfigurer();
	     p.setIgnoreResourceNotFound(true);
	     p.setProperties(System.getProperties());

	    return p;
	 }
	
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(FlywayConfiguration.class);
		Flyway initBean = ctx.getBean(Flyway.class);
		initBean.migrate();
	}
}
