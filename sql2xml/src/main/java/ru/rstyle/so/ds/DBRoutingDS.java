package ru.rstyle.so.ds;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import ru.rstyle.so.domain.Semaphore;

public class DBRoutingDS extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {

        if (Semaphore.getVendor().equals("oracle")) {
            return "ORACLE";
        } else if (Semaphore.getVendor().equals("db2")) {
            return "DB2";
        } else {
            return "POSTGRESQL";
        }

    }
}
