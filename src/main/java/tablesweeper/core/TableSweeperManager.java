package tablesweeper.core;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import tablesweeper.gui.ClientData;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;
import static org.quartz.TriggerKey.triggerKey;

/**
 * Created with IntelliJ IDEA.
 * User: wli
 * Date: 7/30/13
 * Time: 1:38 PM
 * To change this template use File | Settings | File Templates.
 */
public class TableSweeperManager {


    public static final String TRIGGER_NAME = "myTrigger";
    public static final String TRIGGER_GROUP = "myTriggerGroup";
    public static final String JOB_NAME = "myJob";
    private Scheduler scheduler;


    private ClientData clientData;

    private static TableSweeperManager INSTANCE = null;

    public static TableSweeperManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TableSweeperManager();
        }
        return INSTANCE;
    }

    private TableSweeperManager() {

        try {
            scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.start();
        } catch (SchedulerException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public void startSweeperJob(int frequency) {
        try {
            scheduler.start();


            JobDataMap data = new JobDataMap();
            data.put("clientData", clientData);

            JobDetail job = newJob(SweeperJob.class)
                    .withIdentity(JOB_NAME)
                    .setJobData(data)
                    .build();



            Trigger trigger = newTrigger()
                    .withIdentity(triggerKey(TRIGGER_NAME, TRIGGER_GROUP))
                    .withSchedule(simpleSchedule()
                            .withIntervalInMinutes(frequency)
                            .repeatForever())
                    .startAt(DateBuilder.newDate().build())
                    .build();


            scheduler.scheduleJob(job, trigger);

        } catch (SchedulerException se) {
            se.printStackTrace();
        }
    }

    public void stopSweeperJob() {
        try {
            scheduler.unscheduleJob(triggerKey(TRIGGER_NAME, TRIGGER_GROUP));
        } catch (SchedulerException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public void setClientData(ClientData clientData) {
        this.clientData = clientData;
    }
}
