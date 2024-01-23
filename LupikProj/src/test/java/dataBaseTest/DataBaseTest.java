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

    private Database dataBase;
    private Logger logger = Logger.getLogger(getClass());


    @Before
    public void setUp() throws SQLException, ClassNotFoundException {
        dataBase =MySQL_Database.getDataBase();

    }

    @After
    public void tearDown() throws SQLException {
        dataBase.quit();
    }


    @Test
    public void testDataFromDB() throws SQLException, ClassNotFoundException {
        final String LOGIN = "G8_lupik_o";
        ArrayList <Map<String, String>> dataFromSeleniumTable =
        dataBase.selectTableAsMap("SELECT * From seleniumTable");
        logger.info(dataFromSeleniumTable);
        logger.info(dataFromSeleniumTable.size());
        logger.info(dataFromSeleniumTable.get(0).get("login"));

        dataBase.selectTableAsMap(String.format( "SELECT * From seleniumTable WHERE LOGIN= '%s'",LOGIN));
        logger.info("Number of records with "+ LOGIN + "is" +dataFromSeleniumTable.size());
         int numberOfInseretRows =
                 dataBase.changeTable("INSERT INTO seleniumTable VALUES('3451','%s','%s')", LOGIN,"54321");
         logger.info(numberOfInseretRows + " was inserted");
        dataBase.selectTableAsMap(String.format( "SELECT * From seleniumTable WHERE LOGIN= '%s'",LOGIN));
        logger.info("Number of records with "+ LOGIN + "is" +dataFromSeleniumTable.size());

        int numberOfDeletedRows = dataBase.changeTable("Delete FROM seleniumTable WHERE LOGIN = '" + LOGIN + "'");
        logger.info("Deleted rows:" + numberOfDeletedRows);

        logger.info("------------------------------------");

        DB_Util_seleniumTable dbUtilSeleniumTable = new DB_Util_seleniumTable();
        logger.info(dbUtilSeleniumTable.getPassForLogin("G7_taras_r"));
    }


}
