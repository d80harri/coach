package net.d80harri.coach.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class HibernateProperties {
	private final String hbm2ddlAuto;
	private final boolean showSql;
	private final String dialect;
	private final boolean quoteIdentifiers;

	@Autowired
	public HibernateProperties(@Value("${hibernate.hbm2ddlAuto:validate}") String hbm2ddlAuto,
			@Value("${hibernate.showSql:true}") boolean showSql,
			@Value("${hibernate.dialect:org.hibernate.dialect.H2Dialect}") String dialect,
			@Value("${hibernate.quoteIdentifiers:false}") boolean quoteIdentifiers) {
		super();
		this.hbm2ddlAuto = hbm2ddlAuto;
		this.showSql = showSql;
		this.dialect = dialect;
		this.quoteIdentifiers = quoteIdentifiers;
	}

	public String getHbm2ddlAuto() {
		return hbm2ddlAuto;
	}

	public boolean isShowSql() {
		return showSql;
	}

	public String getDialect() {
		return dialect;
	}

	public boolean isQuoteIdentifiers() {
		return quoteIdentifiers;
	}

}
