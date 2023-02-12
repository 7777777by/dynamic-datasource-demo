package com.by.datasource.demo.dynamic;

import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class DynamicDataSourceService {
    private static final Map<Object, Object> dataSourceMap = new HashMap<>();
    private static final ThreadLocal<String> dbKeys = ThreadLocal.withInitial(() -> null);

    /**
     * 动态添加一个数据源
     *
     * @param name       数据源的key
     * @param dataSource 数据源对象
     */
    public static void addDataSource(String name, DataSource dataSource) {
        DynamicDataSource dynamicDataSource = App.getContext().getBean(DynamicDataSource.class);
        dataSourceMap.put(name, dataSource);
        dynamicDataSource.setTargetDataSources(dataSourceMap);
        dynamicDataSource.afterPropertiesSet();
        log.info("添加了数据源:{}", name);
    }

    /**
     * 切换数据源
     *
     * @param dbKey 数据源的key
     */
    public static void switchDB(String dbKey) {
        dbKeys.set(dbKey);
    }

    /**
     * 重置数据源
     */
    public static void resetDB() {
        dbKeys.remove();
    }

    /**
     * 获取当前数据源
     *
     * @return 当前数据源
     */
    public static String currentDB() {
        return dbKeys.get();
    }
}
