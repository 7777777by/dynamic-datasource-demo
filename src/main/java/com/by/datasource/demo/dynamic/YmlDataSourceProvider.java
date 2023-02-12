package com.by.datasource.demo.dynamic;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.*;

@Component
@ConfigurationProperties(prefix = "spring.datasource.hikari")
@Slf4j
public class YmlDataSourceProvider implements DataSourceProvider {
    private List<Map<String, Map<String, DataSourceProperties>>> dataSources;

    public List<Map<String, Map<String, DataSourceProperties>>> getDataSources() {
        return dataSources;
    }

    public void setDataSources(List<Map<String, Map<String, DataSourceProperties>>> dataSources) {
        this.dataSources = dataSources;
    }

    private DataSource buildDataSource(DataSourceProperties prop) {
        DataSourceBuilder<?> builder = DataSourceBuilder.create();
        builder.driverClassName(prop.getDriverClassName());
        builder.url(prop.getUrl());
        builder.username(prop.getUsername());
        builder.password(prop.getPassword());
        return builder.build();
    }

    @Override
    public List<DataSource> provide() {
        List<DataSource> res = new ArrayList<>();
        Set<String> dbTypeSet = new HashSet<>();
        dataSources.forEach(dbTypeMap -> {
            for (Map.Entry<String, Map<String, DataSourceProperties>> dbTypeEntry : dbTypeMap.entrySet()) {
                String dbType = dbTypeEntry.getKey();
                dbTypeSet.add(dbType);
                Map<String, DataSourceProperties> dbMap = dbTypeEntry.getValue();
                for (Map.Entry<String, DataSourceProperties> entry : dbMap.entrySet()) {
                    DataSourceProperties dataSourceProperties = entry.getValue();
                    DataSource dataSource = buildDataSource(dataSourceProperties);
                    DynamicDataSourceService.addDataSource(dbType + "_" + entry.getKey(), dataSource);
                }
            }
        });
        log.info("支持的数据库类型：{}", dbTypeSet.toArray());
        return res;
    }

    @PostConstruct
    public void init() {
        provide();
    }
}
