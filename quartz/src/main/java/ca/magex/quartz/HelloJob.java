package ca.magex.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class HelloJob implements Job {
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		System.out.println("Hello Quartz! it is now " + context.getScheduledFireTime());
	}
	
}