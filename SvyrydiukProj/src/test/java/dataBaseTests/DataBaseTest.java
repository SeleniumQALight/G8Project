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
import java.util.HashMap;
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
        final String LOGIN = "g8_roman_sv";
        ArrayList<Map<String, String>> dataFromSeleniumTable = database.selectTableAsMap("SELECT * FROM seleniumTable");
        logger.info(dataFromSeleniumTable);
        logger.info(dataFromSeleniumTable.size());
        logger.info(dataFromSeleniumTable.get(0).get("login"));
        logger.info(dataFromSeleniumTable.get(1).get("login"));

        dataFromSeleniumTable = database.selectTableAsMap(String.format("SELECT * FROM seleniumTable WHERE login = '%s'", LOGIN));
        logger.info("Number of records with login = " + LOGIN + " is " + dataFromSeleniumTable.size());

        int numberOfInsertedRows = database.changeTable("INSERT INTO seleniumTable VALUES ('210123475', '%s', '%s')", LOGIN, "qwer43210");
        logger.info("Number of inserted rows is " + numberOfInsertedRows);

        dataFromSeleniumTable = database.selectTableAsMap(String.format("SELECT * FROM seleniumTable WHERE login = '%s'", LOGIN));
        logger.info("Number of records with login = " + LOGIN + " is " + dataFromSeleniumTable.size());

        int numberOfDeletedRows = database.changeTable("DELETE FROM seleniumTable WHERE login = '" + LOGIN + "'");
        logger.info("Number of deleted rows is " + numberOfDeletedRows);

        logger.info("----------------------------------------------------------------");

        DB_Util_seleniumTable db_util = new DB_Util_seleniumTable();
        logger.info(db_util.getPassForLogin("G7_taras_r"));

    }

}
