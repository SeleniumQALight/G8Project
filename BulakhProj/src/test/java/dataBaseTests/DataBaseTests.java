package dataBaseTests;

import libs.DB_Util_seleniumTable;
import libs.Database;
import libs.MySQL_Database;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pages.LoginPage;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

public class DataBaseTests {
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
        final String LOGIN = "G8_TBu94";

        ArrayList<Map<String, String>> dataFromSeleniumTable =
                database.selectTableAsMap("SELECT * FROM seleniumTable");
        logger.info(dataFromSeleniumTable);
        logger.info(dataFromSeleniumTable.size());
        logger.info(dataFromSeleniumTable.get(0).get("login"));
        dataFromSeleniumTable =
                database.selectTableAsMap(String.format("SELECT * FROM seleniumTable  WHERE login = '%s'", LOGIN));
        logger.info("Number of records with login " + LOGIN +" is " + dataFromSeleniumTable.size());

        int numberOFInsertRows =
                database.changeTable("INSERT INTO seleniumTable VALUES('541', '%s', '%s')", LOGIN, "16121");
        logger.info(numberOFInsertRows + " was inserted");

        dataFromSeleniumTable =
                database.selectTableAsMap(String.format("SELECT * FROM seleniumTable  WHERE login = '%s'", LOGIN));
        logger.info("Number of records with login " + LOGIN +" is " + dataFromSeleniumTable.size());

        int numberOFDeleteRows =
                database.changeTable("DELETE FROM seleniumTable WHERE login = ' " + LOGIN + "'");
        logger.info(numberOFDeleteRows);

        logger.info("---------------");

        DB_Util_seleniumTable dbUtilSeleniumTable = new DB_Util_seleniumTable();
        logger.info(dbUtilSeleniumTable.getPassForLogin("G8_TBu94"));

    }
}
