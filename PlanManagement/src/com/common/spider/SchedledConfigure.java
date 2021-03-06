package com.common.spider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

@Configuration
@PropertySource("classpath:config.properties")
public class SchedledConfigure {

	@Autowired
	private Environment environment;

	@Bean("detailFactoryBean")
	public MethodInvokingJobDetailFactoryBean detailFactoryBean(JandanPicTask scheduledTasks) {
		MethodInvokingJobDetailFactoryBean mFactoryBean = new MethodInvokingJobDetailFactoryBean();
		mFactoryBean.setTargetObject(scheduledTasks);
		// 这儿设置对应的方法名 与执行具体任务调度类中的方法名对应
		mFactoryBean.setTargetMethod("work");
		mFactoryBean.setConcurrent(false);
		return mFactoryBean;
	}

	@Bean(name = "cronTriggerBean")
	public CronTriggerFactoryBean cronTriggerBean(MethodInvokingJobDetailFactoryBean detailFactoryBean) {
		CronTriggerFactoryBean trigger = new CronTriggerFactoryBean();
		trigger.setJobDetail(detailFactoryBean.getObject());
		trigger.setCronExpression(environment.getProperty("cron"));// 每5秒执行一次
		return trigger;

	}

	@Bean
	public SchedulerFactoryBean schedulerFactory(CronTriggerFactoryBean cronTriggerBean) {
		SchedulerFactoryBean schedulerFactory = new SchedulerFactoryBean();
		schedulerFactory.setTriggers(cronTriggerBean.getObject());
		return schedulerFactory;
	}
}
