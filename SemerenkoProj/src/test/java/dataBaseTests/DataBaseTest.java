package dataBaseTests;

import libs.DB_Util_seleniumUsers;
import libs.Database;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

public class DataBaseTest {
    private Database database;
    private Logger logger = Logger.getLogger(getClass());

   /* @Before
    public void setUp() throws SQLException, ClassNotFoundException {
        database = MySQL_Database.getDataBase();
    }

    @After
    public void tearDown() throws SQLException {
        database.quit();*/


    @Test
    public void testDataFromDB() throws SQLException, ClassNotFoundException {
        final String LOGIN = "G8Semerenko";

        // data request
        ArrayList<Map<String, String>> dataFromSeleniumTable =
                database.selectTableAsMap("SELECT * FROM seleniumTable");
        //database.selectTableAsMap(String.format("SELECT * FROM seleniumTable where login = '%s'", LOGIN));
        logger.info(dataFromSeleniumTable);
        logger.info(dataFromSeleniumTable.size());
        logger.info(dataFromSeleniumTable.get(0).get("login"));

        dataFromSeleniumTable =
                database.selectTableAsMap(String.format("SELECT * FROM seleniumTable WHERE login = '%s'", LOGIN));
        logger.info("number of records with login " + LOGIN + " is " + dataFromSeleniumTable.size());
//

        int numberOfInsertRows = database.changeTable("INSERT INTO seleniumTable VALUES('1013', '%s', '%s')", LOGIN, "1234567890");
        logger.info(numberOfInsertRows + " was inserted");
//
        dataFromSeleniumTable =
                database.selectTableAsMap(String.format("SELECT * FROM seleniumTable WHERE login = '%s'", LOGIN));
        logger.info("number of records with login " + LOGIN + " is " + dataFromSeleniumTable.size());
//
//        int numberOfDeletedRows = database.changeTable("DELETE FROM seleniumTable WHERE login = '%s'", LOGIN);
//        logger.info(numberOfDeletedRows);
//
//        logger.info("------------");
//
//        DB_Util_seleniumTable dbUtilSeleniumTable = new DB_Util_seleniumTable();
//        logger.info(dbUtilSeleniumTable.getPassForLogin("G7_taras_r"));
    }

        @Test
        public void  getUserPassFromDB () throws SQLException, ClassNotFoundException {
            DB_Util_seleniumUsers dbUtilSeleniumUsers = new DB_Util_seleniumUsers();
            dbUtilSeleniumUsers.getDataForLogin("newqaauto");
            logger.info(dbUtilSeleniumUsers.getPassForLogin("newqaauto"));
            logger.info(dbUtilSeleniumUsers.getAliasForLogin("newqaauto"));

    }
}
