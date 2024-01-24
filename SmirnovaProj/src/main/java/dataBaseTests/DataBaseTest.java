package dataBaseTests;

import libs.DB_Util_seleniumTable;
import libs.Database;
import libs.MySQL_Database;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

public class DataBaseTest {
    private Database database;
    private Logger logger = Logger.getLogger(getClass());

    @Before
    public void setUp() throws SQLException, ClassNotFoundException {
        database = MySQL_Database.getDataBase();
        logger.info("--- Connected to DB -------");
    }

    @After
    public void tearDown() throws SQLException {
        database.quit();
        logger.info("--- Disconnected from DB -------");
    }

    @Test
    public void testDataFromDB() throws SQLException, ClassNotFoundException {
        final String LOGIN = "G8_smirnova";

        ArrayList<Map<String, String>> dataFromSeleniumTable =
                database.selectTableAsMap("select * from seleniumTable");
        logger.info(dataFromSeleniumTable);
        logger.info(dataFromSeleniumTable.size());
        logger.info(dataFromSeleniumTable.get(0).get("login"));

        dataFromSeleniumTable
                = database.selectTableAsMap(String.format("select * from seleniumTable where login = '%s'", LOGIN));
        logger.info("Number of rows with login " + LOGIN + " is " + dataFromSeleniumTable.size());

        int numberOfInsertedRows = database.changeTable(
                "INSERT INTO seleniumTable VALUES ('760', '%s', '%s')", LOGIN, "123456");
        logger.info("Number of inserted rows " + numberOfInsertedRows);
        dataFromSeleniumTable
                = database.selectTableAsMap(String.format("select * from seleniumTable where login = '%s'", LOGIN));
        logger.info("Number of rows with login " + LOGIN + " is " + dataFromSeleniumTable.size());


        int numberOfDeletedRows =
                database.changeTable("DELETE FROM seleniumTable WHERE login = '%s'", LOGIN);
        logger.info("Number of deleted rows " + numberOfDeletedRows);

        logger.info("----------------------");
        DB_Util_seleniumTable dbUtilSeleniumTable = new DB_Util_seleniumTable();
        logger.info(dbUtilSeleniumTable.getPassForLogin("G7_taras_r"));
    }
}
