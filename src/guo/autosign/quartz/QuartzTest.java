package guo.autosign.quartz;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import guo.tool.CalendarHelp;
import java.util.Date;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

public class QuartzTest {
	
//	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public void test(){
		System.out.println("test...");
		Date begin = CalendarHelp.getDate("2012-11-28 10:10:10");
		Date end = CalendarHelp.getDate("2012-11-29 09:54:10");
		
		try {
			Scheduler s = StdSchedulerFactory.getDefaultScheduler();
			
			for(int i=0;i<6;i++){
				System.out.println("Add guo"+i);
				 JobDetail job = newJob(AutoSignQuartzJob.class)
					            .withIdentity("guo"+i, "guo"+i)
					            .build();
				 Trigger trigger = newTrigger()
						 	.withIdentity("guo"+i, "guo"+i)
						 	.startAt(begin)
						 	.endAt(end)
						 	.forJob(job)
						 	.withSchedule(simpleSchedule()
						 			.withIntervalInMilliseconds(getTime(1, 5))
						 			.withRepeatCount(SimpleTrigger.REPEAT_INDEFINITELY))
						 	.build();
				 s.scheduleJob(job, trigger);
			}
			 s.start();
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public long getTime(int interval_Type,int time_interval){
		int time = 0;
		switch(interval_Type){
		case 1: 
			time = time_interval * 1000; break;
		case 2: 
			time = time_interval * 60 * 1000; break;
		case 3: 
			time = time_interval * 60 * 60 * 1000; break;
		case 4: 
			time = time_interval * 24 * 60 * 60 * 1000; break;
		case 5: 
			time = time_interval * 7 * 24 * 60 * 60 * 1000 ; break;
		case 6: 
			time = time_interval * 30 * 24 * 60 * 60 * 1000; break;
		case 7: 
			time = 0;break;
		default:
			time = 0;break;
		}
		return time;
	}
	
	public static void main(String[] args){
		System.out.println("Begin`````````");
		QuartzTest q = new QuartzTest();
		q.test();
	}
}
