package net.d80harri.coach.domain.config.db;

import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = DBConfiguration.class)
public class DBConfiguration {

	@Bean
	public DataSource dataSource(@Value("${db.driverClass}") String driverClass, @Value("${db.connectionUrl}") String connectionUrl, @Value("${db.userName}") String connectionUserName, @Value("${db.pwd}")String connectionPwd) {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(driverClass);
		dataSource.setUrl(connectionUrl);
		dataSource.setUsername(connectionUserName);
		dataSource.setPassword(connectionPwd);

		return dataSource;
	}
}
