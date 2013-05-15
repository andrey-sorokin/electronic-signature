package ru.rstyle.so.ds;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import ru.rstyle.so.domain.Semaphore;

public class DBRoutingDS extends AbstractRoutingDataSource {
	@Override
	protected Object determineCurrentLookupKey() {
		
		if (Semaphore.vendor.equals("oracle"))
			return "ORACLE";
		else if (Semaphore.vendor.equals("db2"))
			return "DB2";
		else
			return "POSTGRESQL";
	}
}
