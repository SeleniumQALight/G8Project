package data;

import libs.ConfigProvider;

public class TestData {
    public final static String DEFAULT_VALID_LOGIN_UI = System.getProperty("defaultLogin" , ConfigProvider.configHiddenProperties.login());
    public final static String DEFAULT_VALID_PASSWORD_UI = "123456qwerty";

    public final static String VALID_LOGIN_API = "tymofiiapi";
    public final static String VALID_PASSWORD_API = "123456qwerty";
}
