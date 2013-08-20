package tablesweeper;

import tablesweeper.core.TableSweeper;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: wli
 * Date: 7/30/13
 * Time: 12:26 PM
 * To change this template use File | Settings | File Templates.
 */
public class TableSweeperTest {


    public static final String TABLE_NAME = "TEST";
    public static final String TABLE_NAME_1 = "TEST1";
    public static final String DB_DRIVER = "org.h2.Driver";
    public static final String DB_URL = "jdbc:h2:mem:test";
    public static final String DB_USERNAME = "sa";
    public static final String DB_PASSWORD = "";

    @Before
    public void setup() throws SQLException {
        Connection connection = getConnection();

        QueryRunner runner = new QueryRunner();

        runner.update(connection, "create table " + TABLE_NAME + "(id integer)");
        runner.update(connection, "create table " + TABLE_NAME_1 + "(id integer)");

        String insert_sql = "insert into " + TABLE_NAME + " values(?)";
        String insert_sql_1 = "insert into " + TABLE_NAME_1 + " values(?)";

        Object[][] params = new Object[100][1];
        for (int i = 0; i < params.length; i++) {
            params[i][0] = i;
        }

        runner.batch(connection, insert_sql, params);
        runner.batch(connection, insert_sql_1, params);


    }

    @After
    public void tearDown() throws SQLException {
        Connection connection = getConnection();

        QueryRunner runner = new QueryRunner();

        runner.update(connection, "drop table " + TABLE_NAME);
        runner.update(connection, "drop table " + TABLE_NAME_1);

        DbUtils.closeQuietly(connection);

    }

    @Test
    public void test_sweep_table() throws SQLException {
        TableSweeper sweeper = new TableSweeper(DB_DRIVER, DB_URL, DB_USERNAME, DB_PASSWORD);
        sweeper.cleanTable(TABLE_NAME);


        QueryRunner runner = new QueryRunner();

        Connection connection = getConnection();

        Integer result = runner.query(connection, "select count(*) from test", new ResultSetHandler<Integer>() {
            @Override
            public Integer handle(ResultSet rs) throws SQLException {
                rs.next();
                Integer result = rs.getInt(1);
                return result;
            }
        });

        assertEquals(0, result.intValue());

        DbUtils.closeQuietly(connection);

    }

    @Test
    public void test_sweep_multiple_tables() throws SQLException {
        TableSweeper sweeper = new TableSweeper(DB_DRIVER, DB_URL, DB_USERNAME, DB_PASSWORD);
        sweeper.cleanTable(TABLE_NAME + " " + TABLE_NAME_1);
        sweeper.cleanTable(TABLE_NAME + "    " + TABLE_NAME_1);   //test multiple spaces


        QueryRunner runner = new QueryRunner();

        Connection connection = getConnection();

        Integer result = runner.query(connection, "select count(*) from test", new ResultSetHandler<Integer>() {
            @Override
            public Integer handle(ResultSet rs) throws SQLException {
                rs.next();
                Integer result = rs.getInt(1);
                return result;
            }
        });

        Integer result1 = runner.query(connection, "select count(*) from test1", new ResultSetHandler<Integer>() {
            @Override
            public Integer handle(ResultSet rs) throws SQLException {
                rs.next();
                Integer result = rs.getInt(1);
                return result;
            }
        });

        assertEquals(0, result.intValue());
        assertEquals(0, result1.intValue());

        DbUtils.closeQuietly(connection);

    }

    private static Connection getConnection() throws SQLException {
        DbUtils.loadDriver(DB_DRIVER);

        return DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
    }

}
