package tablesweeper.core;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: wli
 * Date: 7/30/13
 * Time: 12:38 PM
 * To change this template use File | Settings | File Templates.
 */
public class TableSweeper {


    private final String driverClassName;
    private final String url;
    private final String username;
    private final String password;

    public TableSweeper(String driverClassName, String url, String username, String password) {

        this.driverClassName = driverClassName;
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public void cleanTable(String tableName) throws SQLException {

        DbUtils.loadDriver(this.driverClassName);

        Connection connection = getConnection();

        QueryRunner runner = new QueryRunner();

        runner.update(connection, "delete from " + tableName);

        if (!connection.getAutoCommit()) {
            DbUtils.commitAndClose(connection);
        }
    }

    private Connection getConnection() throws SQLException {
        Connection con = DriverManager.getConnection(url, username, password);
        return con;
    }
}
