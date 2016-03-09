package net.d80harri.coach.domain;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@Import({ DomainConfiguration.class })
// @PropertySource(ignoreResourceNotFound=false,
// value="classpath:/domain.properties")
public class DomainTestConfiguration {

	@Bean
	public Properties propertiesFromFile() throws IOException {
		Properties properties = new Properties();
		InputStream is = this.getClass().getResourceAsStream("/it.properties");
		if (is != null) {
			properties.load(is);
		}
		return properties;
	}
	
	@Bean
	public Properties systemProperties() {
		return System.getProperties();
	}
	
	@Bean
	public Properties itProperties(@Qualifier("propertiesFromFile") Properties fromFile, @Qualifier("systemProperties") Properties system) {
		Properties result = new Properties();
		result.putAll(fromFile);
		result.putAll(system);
		return result;
	}

	@Bean
	public PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer(@Qualifier("itProperties") Properties propertiesFromFile) {
		PropertySourcesPlaceholderConfigurer p = new PropertySourcesPlaceholderConfigurer();

		p.setProperties(propertiesFromFile);
		p.setIgnoreResourceNotFound(true);
		return p;
	}
}
