package ca.magex.aop;

import org.aspectj.lang.ProceedingJoinPoint;

public class MyLoggingAspect {

	public Object log(ProceedingJoinPoint call) throws Throwable {
		System.out.println("Log: " + call.getSignature());
		for (int i = 0; i < call.getArgs().length; i++) {
			System.out.println("\t" + call.getArgs()[i]);
		}
		return call.proceed();
	}

}
