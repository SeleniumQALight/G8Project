package dataBasetests;

import libs.DB_Util_seleniumTable;
import libs.Database;
import libs.MySQL_Database;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

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
        final String LOGIN = "G8_kamaieva";

        ArrayList<Map<String, String>> dataFromSeleniumTable =
                database.selectTableAsMap("SELECT * FROM seleniumTable");
        logger.info(dataFromSeleniumTable);
        logger.info(dataFromSeleniumTable.size());
        logger.info(dataFromSeleniumTable.get(0).get("login"));

        dataFromSeleniumTable =
                database.selectTableAsMap(String.format("SELECT * FROM seleniumTable WHERE login = '%s'", LOGIN));
        logger.info("Number of records with login = " + LOGIN + " is " + dataFromSeleniumTable.size());

        int numberOfInsertedRows = database.changeTable("INSERT INTO seleniumTable VALUES ('898', '%s','%s')", LOGIN, "7qwerty777");
        logger.info(numberOfInsertedRows + " rows were inserted");

        int numberOfDeletedRows = database.changeTable("DELETE FROM seleniumTable WHERE login = '%s'", LOGIN);
        logger.info(numberOfDeletedRows + " rows were deleted");

        DB_Util_seleniumTable db_util_seleniumTable = new DB_Util_seleniumTable();
        logger.info(db_util_seleniumTable.getPassForLogin("G7_taras_r"));
    }
}
