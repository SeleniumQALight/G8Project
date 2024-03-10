package data;

import libs.ConfigProvider;

// class for storing test data
// we can use a lot of times
public class TestData {
    public final static String VALID_LOGIN_UI = System.getProperty("defaultLogin",ConfigProvider.configHiddenProperties.login());
    public final static String VALID_PASSWORD_UI = "123456qwerty";


    public final static String VALID_LOGIN_API = "romanapi";
    public final static String VALID_PASSWORD_API = "Roman123456!";

    public final static String VALID_LOGIN_API_BOOKS = "RomanSv";
    public final static String VALID_PASSWORD_API_BOOKS = "Roman123!";
    public static float privatBankBuyCours;
    public static float privatBankSaleCours;
    public static float privatBankBuyCoursUI;
    public static float privatBankSaleCoursUI;
}
