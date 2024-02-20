package data;

import libs.ConfigProvider;

//class of saving data for tests which we will use a lot of times
public class TestData {
    public final static String VALID_LOGIN_UI = System.getProperty("defaultLogin", ConfigProvider.configHiddenProperties.login());
    public final static String VALID_PASSWORD_UI = "123456qwerty";

    public final static String VALID_LOGIN_API = "apikamaieva";
    public final static String VALID_PASSWORD_API = "1234567qwerty";
}
