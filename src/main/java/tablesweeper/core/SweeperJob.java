package tablesweeper.core;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tablesweeper.gui.ClientData;

import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: wli
 * Date: 7/30/13
 * Time: 1:39 PM
 * To change this template use File | Settings | File Templates.
 */
public class SweeperJob implements Job {

    private static final Logger logger = LoggerFactory.getLogger(SweeperJob.class);

    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDataMap dataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        ClientData clientData = (ClientData) dataMap.get("clientData");

        String url = "jdbc:sqlserver://" + clientData.getIp() + ":" + clientData.getPort();

        TableSweeper sweeper = new TableSweeper(clientData.getDriver(),
                url,
                clientData.getUsername(),
                clientData.getPassword());

        try {
            logger.info("开始清理表[" + clientData.getTableName() + "]....");
            sweeper.cleanTable(clientData.getTableName());
            logger.info("清理完成....");
        } catch (SQLException e) {
            logger.error("发生错误!", e);
            throw new JobExecutionException(e);
        }
    }
}
