package libs;

import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

public class DB_Util_seleniumUsers {
    private Database mySQL_DataBase;
    Logger logger = Logger.getLogger(getClass());

    public ArrayList<Map<String, String>> getDataForLogin(String login) throws SQLException, ClassNotFoundException {
        mySQL_DataBase = MySQL_Database.getDataBase();
        logger.info("-".repeat(15) + " Connect to DB " + "-".repeat(15));

        ArrayList<Map<String, String>> dataFromSeleniumUsers =
                //mySQL_DataBase.selectTableAsMap("SELECT * FROM seleniumTable");
                mySQL_DataBase.selectTableAsMap(String.format("SELECT * FROM seleniumUsers where login = '%s'", login));
        logger.info(dataFromSeleniumUsers);
        logger.info(dataFromSeleniumUsers.size());
        mySQL_DataBase.quit();
        logger.info("-".repeat(15) + " Disconnect from DB " + "-".repeat(15));
        return dataFromSeleniumUsers;
    }

    public String getPassForLogin(String login) throws SQLException, ClassNotFoundException {
        mySQL_DataBase = MySQL_Database.getDataBase();
        logger.info("-".repeat(15) + " Connect to DB " + "-".repeat(15));
        String password = mySQL_DataBase.selectValue(String.format("SELECT password FROM seleniumUsers where login = '%s'", login));
        mySQL_DataBase.quit();
        logger.info("-".repeat(15) + " Disconnect from DB " + "-".repeat(15));
        return password;
    }

    public String getAliasForLogin(String login) throws SQLException, ClassNotFoundException {
        mySQL_DataBase = MySQL_Database.getDataBase();
        logger.info("-".repeat(15) + " Connect to DB " + "-".repeat(15));
        String alias = mySQL_DataBase.selectValue(String.format("SELECT alias FROM seleniumUsers where login = '%s'", login));
        mySQL_DataBase.quit();
        logger.info("-".repeat(15) + " Disconnect from DB " + "-".repeat(15));
        return alias;
    }
}
