package net.d80harri.coach.domain.repository;

public class DomainCoreConfiguration {
	private String driverClass = "org.h2.Driver";
	private String connectionUrl = "jdbc:h2:~/coach;AUTO_SERVER=TRUE";
	private String connectionUserName = "root";
	private String connectionPwd = "passme";
	private String hibernateDialect = "org.hibernate.dialect.H2Dialect";
	
	public String getDriverClass() {
		return driverClass;
	}

	public DomainCoreConfiguration setDriverClass(String driverClass) {
		this.driverClass = driverClass;
		return this;
	}
	
	public String getHibernateDialect() {
		return hibernateDialect;
	}
	
	public DomainCoreConfiguration setHibernateDialect(String hibernateDialect) {
		this.hibernateDialect = hibernateDialect;
		return this;
	}

	public String getConnectionUrl() {
		return connectionUrl;
	}

	public DomainCoreConfiguration setConnectionUrl(String connectionUrl) {
		this.connectionUrl = connectionUrl;
		return this;
	}

	public String getConnectionUserName() {
		return connectionUserName;
	}

	public DomainCoreConfiguration setConnectionUserName(String connectionUserName) {
		this.connectionUserName = connectionUserName;
		return this;
	}

	public String getConnectionPwd() {
		return connectionPwd;
	}

	public DomainCoreConfiguration setConnectionPwd(String connectionPwd) {
		this.connectionPwd = connectionPwd;
		return this;
	}

}