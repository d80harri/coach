package net.d80harri.coach.domain.config.db;

import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(ignoreResourceNotFound=false, value="classpath:domain.properties")
@ComponentScan(basePackageClasses=DBConfiguration.class)
public class DBConfiguration {

	@Bean
	public DataSource dataSource(DbProperties properties) {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(properties.getDriverClass());
		dataSource.setUrl(properties.getConnectionUrl());
		dataSource.setUsername(properties.getConnectionUserName());
		dataSource.setPassword(properties.getConnectionPwd());

		return dataSource;
	}
}
