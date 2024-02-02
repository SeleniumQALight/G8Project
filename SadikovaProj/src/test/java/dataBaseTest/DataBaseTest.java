package dataBaseTest;

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

    }

    @After
    public void tearDown() throws SQLException {
        database.quit();
    }

    @Test
    public void testDataFromDB() throws SQLException, ClassNotFoundException {
        final String LOGIN = "g8_svamar";
        ArrayList<Map<String, String>> dataFromSeleniumTable =
                database.selectTableAsMap("SELECT * FROM seleniumTable");
        logger.info(dataFromSeleniumTable);
        logger.info(dataFromSeleniumTable.size());
        logger.info(dataFromSeleniumTable.get(0).get("login"));
        dataFromSeleniumTable = database.selectTableAsMap(String.format("SELECT * FROM seleniumTable WHERE login = '%s'", LOGIN));
        logger.info("Number of record with login " + LOGIN + " is " + dataFromSeleniumTable.size());


        int numberOfInsertedRows = database.changeTable("INSERT INTO seleniumTable VALUES('601', '%s', '%s')", LOGIN, "123444");
        logger.info(numberOfInsertedRows + " was inserted");
        int numberOfDeletedRows = database.changeTable("DELETE FROM seleniumTable WHERE login = '" + LOGIN +"'");
        logger.info(numberOfDeletedRows);

        logger.info("-----------------");
        DB_Util_seleniumTable dbUtilSeleniumTable = new DB_Util_seleniumTable();
        logger.info(dbUtilSeleniumTable.getPassForLogin(""));


    }



}
