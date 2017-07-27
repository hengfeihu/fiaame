package com.assetman.fiaame;

import io.ebean.EbeanServer;
import io.ebean.EbeanServerFactory;
import io.ebean.config.ServerConfig;
import org.apache.log4j.Logger;
import org.avaje.datasource.DataSourceConfig;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * 配置EbeanServer
 * Created by hengfeihu on 2017/7/25.
 */
@Component
public class MyEbeanFactory implements FactoryBean<EbeanServer> {
    private Logger log = Logger.getLogger(MyEbeanFactory.class);

    @Override
    public EbeanServer getObject() throws Exception {
        ServerConfig config = new ServerConfig();
        try {
            config.setName("db");
            Properties properties = new Properties();
            properties.load(this.getClass().getClassLoader().getResourceAsStream("application.properties"));
            DataSourceConfig dataSourceConfig = new DataSourceConfig();
            dataSourceConfig.setDriver(properties.getProperty("spring.datasource.driver-class-name"));
            dataSourceConfig.setUrl(properties.getProperty("spring.datasource.url"));
            dataSourceConfig.setUsername(properties.getProperty("spring.datasource.username"));
            dataSourceConfig.setPassword(properties.getProperty("spring.datasource.password"));
            config.setDataSourceConfig(dataSourceConfig);
            config.setDefaultServer(true);
            config.setRegister(true);
            config.setDdlGenerate(false);
            config.setDdlRun(false);
        } catch (Exception e) {
            log.error("========================EbeanServer配置失败========================");
        }
        return EbeanServerFactory.create(config);
    }

    @Override
    public Class<?> getObjectType() {
        return EbeanServer.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
