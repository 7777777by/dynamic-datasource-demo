package com.by.datasource.demo.dynamic;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDataSource extends AbstractRoutingDataSource {
    @Value("${spring.datasource.default-db-key}")
    private String defaultDbKey;

    @Override
    protected Object determineCurrentLookupKey() {
        String currentDB = DynamicDataSourceService.currentDB();
        if (currentDB == null) {
            return defaultDbKey;
        }
        return currentDB;
    }
}
