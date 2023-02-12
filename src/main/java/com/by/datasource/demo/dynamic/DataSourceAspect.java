package com.by.datasource.demo.dynamic;

import com.by.datasource.demo.dto.SchemaDTO;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Aspect
@Component
@Order(-1)
@Slf4j
public class DataSourceAspect {
    @Pointcut("execution( * com.by.datasource.demo.mapper.*.*(..))")
    public void pointCut() {}

    @Before("pointCut()")
    public void choiceDataSource(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        Object[] params = joinPoint.getArgs();
        List<Object> schemas = Arrays.stream(params).filter(param -> param.getClass().isAssignableFrom(SchemaDTO.class)).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(schemas)) {
            DynamicDataSourceService.resetDB();
            log.debug("方法 {} 不需动态路由数据源，选择默认数据源", method.getName());
            return;
        }
        SchemaDTO schema = (SchemaDTO) schemas.get(0);
        DynamicDataSourceService.switchDB(schema.getSchema());
        log.debug("方法 {} 动态路由数据源【{}】", method.getName(), schema.getSchema());
    }
}
