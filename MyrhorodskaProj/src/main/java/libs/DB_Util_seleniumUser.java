package libs;


import java.sql.SQLException;
import org.apache.log4j.Logger;

public class DB_Util_seleniumUser {
    private Database mySQL_DataBase;
    Logger logger = Logger.getLogger(getClass());

    public String getPassForLogin(String login) throws SQLException, ClassNotFoundException {
        mySQL_DataBase = MySQL_Database.getDataBase();
        logger.info("--- Connected to DB -------");

        String pass = mySQL_DataBase.selectValue(
                String.format("SELECT password FROM seleniumUsers WHERE login = '%s'", login)
        );
        mySQL_DataBase.quit();
        logger.info("--- Disconnected from DB -------");
        return pass;
    }

    public String getAliasForLogin(String login) throws SQLException, ClassNotFoundException {
        mySQL_DataBase = MySQL_Database.getDataBase();
        logger.info("--- Connected to DB -------");

        String pass = mySQL_DataBase.selectValue(
                String.format("SELECT alias FROM seleniumUsers WHERE login = '%s'", login)
        );
        mySQL_DataBase.quit();
        logger.info("--- Disconnected from DB -------");
        return pass;
    }
}