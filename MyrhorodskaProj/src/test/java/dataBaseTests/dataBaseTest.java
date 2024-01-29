package dataBaseTests;


import libs.DB_Util_seleniumTable;
import libs.DB_Util_seleniumUser;
import libs.Database;
import libs.MySQL_Database;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;


public class dataBaseTest {
    private Database database;
    private Logger logger = Logger.getLogger(getClass());

    @Before
    public void setUp() throws SQLException, ClassNotFoundException {
        database = MySQL_Database.getDataBase();


    }
    @After
    public void tearDown() throws SQLException{
        database.quit();

    }
    @Test
    public void testDataFromDB () throws SQLException, ClassNotFoundException {
        final String LOGIN = "G8_Myrhor";
        ArrayList<Map<String,String>> dataFromSeleniumTable =
                database.selectTableAsMap("SELECT * FROM seleniumTable");
        logger.info(dataFromSeleniumTable);
        logger.info(dataFromSeleniumTable.size());
        logger.info(dataFromSeleniumTable.get(0).get("login"));
        dataFromSeleniumTable =
                database.selectTableAsMap(String.format("SELECT * FROM seleniumTable WHERE login = '%s'",LOGIN));
        logger.info("Number of record with login"+LOGIN + "is" + dataFromSeleniumTable.size());

        int numberOfInsertedRows = database.changeTable("INSERT INTO seleniumTable VALUE('405','%s','%s')",LOGIN,"12345");
        logger.info(numberOfInsertedRows + " was inserted ");

        dataFromSeleniumTable =
                database.selectTableAsMap(String.format("SELECT * FROM seleniumTable WHERE login = '%s'",LOGIN));
        logger.info("Number of record with login"+LOGIN + "is" + dataFromSeleniumTable.size());

        int numberOfDeleteRows = database.changeTable("DELETE FROM seleniumTable WHERE login = '" + LOGIN + "'");
        logger.info(numberOfDeleteRows);

        logger.info("-------------------------");


        DB_Util_seleniumTable dbUtilSeleniumTable = new DB_Util_seleniumTable();
        logger.info(dbUtilSeleniumTable.getPassForLogin("G7_taras_r"));7

        DB_Util_seleniumUser dbUtilSeleniumUsers = new DB_Util_seleniumUser();
        logger.info(dbUtilSeleniumUsers.getPassForLogin("newqaauto"));
    }
}


