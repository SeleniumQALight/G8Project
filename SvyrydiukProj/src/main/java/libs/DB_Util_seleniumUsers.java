package libs;

import org.apache.log4j.Logger;

import java.sql.SQLException;

public class DB_Util_seleniumUsers {
    private Database mySQL_DataBase;
    Logger logger = Logger.getLogger(getClass());

    public String getPassForLogin(String login) throws SQLException, ClassNotFoundException {
        mySQL_DataBase = MySQL_Database.getDataBase();
        logger.info("--- Connected to DB -------");

        String pass = mySQL_DataBase.selectValue(
                String.format("SELECT passWord FROM seleniumUsers WHERE login = '%s'", login)
                                                );
        mySQL_DataBase.quit();
        logger.info("--- Disconnected from DB -------");
        return pass;
    }

    public String getAliasForPost(String login) throws SQLException, ClassNotFoundException {
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
