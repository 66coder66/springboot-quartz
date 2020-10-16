package com.pbs.job.conf;

import org.quartz.Scheduler;
import org.quartz.TriggerListener;
import org.quartz.spi.JobFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

@Configuration
public class QuartzConfig {
    // 配置文件路径
    private static final String QUARTZ_CONFIG = "/quartz.properties";
    // 按照自己注入的数据源自行修改
    //@Qualifier("pbsDB")
    @Autowired
    private DataSource dataSource;

    @Autowired
    private ApplicationContext applicationContext;

    private AutoWiredSpringBeanToJobFactory pbsJobFactory;

    public QuartzConfig(AutoWiredSpringBeanToJobFactory jobFactory){
        this.pbsJobFactory = jobFactory;
    }
    /**
     * 从quartz.properties文件中读取Quartz配置属性
     * @return
     * @throws IOException
     */
    @Bean
    public Properties quartzProperties() throws IOException {
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.setLocation(new ClassPathResource(QUARTZ_CONFIG));
        propertiesFactoryBean.afterPropertiesSet();
        return propertiesFactoryBean.getObject();
    }

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() throws IOException {
        SchedulerFactoryBean factoryBean = new SchedulerFactoryBean();
        factoryBean.setJobFactory(pbsJobFactory);
        factoryBean.setOverwriteExistingJobs(true);
        factoryBean.setAutoStartup(true); // 设置自行启动
        // 延时启动，应用启动1秒后
        factoryBean.setStartupDelay(1);
        factoryBean.setQuartzProperties(quartzProperties());
        factoryBean.setDataSource(dataSource);// 使用应用的dataSource替换quartz的dataSource
        TriggerListener listener = new MonitorTriggerListener();//记录日志
        factoryBean.setGlobalTriggerListeners(listener);
        return factoryBean;
    }
    @Bean(name = "scheduler")
    public Scheduler scheduler() throws IOException{
        return schedulerFactoryBean().getScheduler();
    }
}
