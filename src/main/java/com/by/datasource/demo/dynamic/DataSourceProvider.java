package com.by.datasource.demo.dynamic;

import javax.sql.DataSource;
import java.util.List;

public interface DataSourceProvider {
    List<DataSource> provide();
}
