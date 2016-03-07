package net.d80harri.coach.domain.config.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DbProperties {
	private final String cacheProvider = "org.hibernate.cache.internal.NoCacheProvider";
	private final String connectionPoolSize = "1";
	private final String defaultSchema = "PUBLIC";
	private final String connectionUrl;
	private final String connectionUserName;
	private final String connectionPwd;
	private final String driverClass;

	@Autowired
	public DbProperties(@Value("${db.connectionUrl:jdbc:h2:~/coach_default;AUTO_SERVER=TRUE}") String connectionUrl,
			@Value("${db.userName:root}") String connectionUserName, @Value("${db.pwd:passme}") String connectionPwd,
			@Value("${db.driverClass:org.h2.Driver}") String driverClass) {
		this.connectionUrl = connectionUrl;
		this.connectionUserName = connectionUserName;
		this.connectionPwd = connectionPwd;
		this.driverClass = driverClass;
	}

	public String getCacheProvider() {
		return cacheProvider;
	}

	public String getConnectionPoolSize() {
		return connectionPoolSize;
	}

	public String getDefaultSchema() {
		return defaultSchema;
	}

	public String getConnectionUrl() {
		return connectionUrl;
	}

	public String getConnectionUserName() {
		return connectionUserName;
	}

	public String getConnectionPwd() {
		return connectionPwd;
	}

	public String getDriverClass() {
		return driverClass;
	}
}
