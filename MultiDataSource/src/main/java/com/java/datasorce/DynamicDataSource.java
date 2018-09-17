package com.java.datasorce;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.Map;
/**
 * 
 *
 * @package com.java.datasorce
 * @file DynamicDataSource.java 创建时间:2018年9月17日上午10:52:15
 * @title 标题（要求能简洁地表达出类的功能和职责）
 * @description  在Spring 2.0.1中引入了AbstractRoutingDataSource, 
 * 该类充当了DataSource的路由中介, 能有在运行时, 根据某种key值来动态切换到真正的DataSource上。
 *
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

    public DynamicDataSource(DataSource defaultTargetDataSource, Map<Object, Object> targetDataSources) {
        super.setDefaultTargetDataSource(defaultTargetDataSource);
        super.setTargetDataSources(targetDataSources);
        super.afterPropertiesSet();
    }

    //Spring不能动态切数据源, 因为determineCurrentLookupKey()在aop拦截之前执行，怎么解决？
    //Spring不能动态切数据源, 因为determineCurrentLookupKey()在aop拦截之前执行？
    //在aop拦截器执行完后，不在执行determineCurrentLookupKey()方法
    
    //自定义拦截器,拦截业务层的所有方法,然后根据方法名(get、query、find、select走主库)
    //(save、add、update、modify、delete走从库)判断走主库还是从库。然后设置数据源类型，
    //数据源类型存放在ThreadLocal中,这样在继承AbstractRoutingDataSource中重写
    //determineCurrentLookupKey（）方法中通过ThreadLocal中获取
    //DataSourceHolder.getDataSourceType();
    @Override
    protected Object determineCurrentLookupKey() {
        return getDataSource();
    }

    public static void setDataSource(String dataSource) {
        contextHolder.set(dataSource);
    }

    public static String getDataSource() {
        return contextHolder.get();
    }

    public static void clearDataSource() {
        contextHolder.remove();
    }

}
