package ca.magex.quartz;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class HelloQuartz {

	public static void main(String[] args) throws Exception {
		CronTrigger trigger = TriggerBuilder
				.newTrigger()
				.withIdentity("schedName", "appName")
				.withSchedule(CronScheduleBuilder.cronSchedule("0/15 * * * * ?"))
				.build();
		
		JobDetail jobDetail = JobBuilder
				.newJob(HelloJob.class)				
				.build();
		
		Scheduler scheduler = new StdSchedulerFactory().getScheduler();
		scheduler.start();
		scheduler.scheduleJob(jobDetail, trigger);
	}
	
}
