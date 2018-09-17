package com.java.datasorce.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import com.java.datasorce.DataSourceNames;
import com.java.datasorce.DynamicDataSource;
import com.java.datasorce.annotation.SelectDataSource;

import java.lang.reflect.Method;

@Aspect
@Component
public class DataSourceAspect implements Ordered {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Pointcut("@annotation(com.java.datasorce.annotation.SelectDataSource)")
    public void dataSourcePointCut() {

    }

    @Before("dataSourcePointCut()")
    public void before(JoinPoint point) {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();

        SelectDataSource ds = method.getAnnotation(SelectDataSource.class);
        if (ds == null) {
            DynamicDataSource.setDataSource(DataSourceNames.FIRST);
        } else {
            DynamicDataSource.setDataSource(ds.datasource());
        }

    }

    @After("dataSourcePointCut()")
    public void after(JoinPoint point) {
        DynamicDataSource.clearDataSource();
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
