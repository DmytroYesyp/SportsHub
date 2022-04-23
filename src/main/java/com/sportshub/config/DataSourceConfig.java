package com.sportshub.config;

import net.ttddyy.dsproxy.listener.logging.SLF4JLogLevel;
import net.ttddyy.dsproxy.support.ProxyDataSourceBuilder;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class DataSourceConfig implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        if (bean instanceof DataSource) {
            DataSource dataSourceBean = (DataSource) bean;
            return ProxyDataSourceBuilder.create(dataSourceBean).name("DataSource proxy")
                    .logQueryBySlf4j(SLF4JLogLevel.INFO).build();
        }

        return bean;
    }
}
