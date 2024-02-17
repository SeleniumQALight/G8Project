package data;

import libs.ConfigProvider;

// this class is for saving test data
//which is used in tests many times
public class TestData {
    public final static String VALID_LOGIN_UI = System.getProperty("defaultLogin", ConfigProvider.configHiddenProperties.login());
    public final static String VALID_PASSWORD_UI = "123456qwerty";

    public static String VALID_LOGIN_API = "apiolesia";
    public static String VALID_PASSWORD_API = "123456zaqwer";
}
