package com.geek.study.sql;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.lang.Nullable;

/**
 * @Author: siyan.liu
 * @Date: 2021/11/7
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    @Nullable
    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSourceSwitch.getDataSource();
    }
}
