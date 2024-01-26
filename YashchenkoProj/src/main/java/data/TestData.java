package data;

import libs.ConfigProvider;

public class TestData {
    public final static String DEFAULT_VALID_LOGIN_UI = System.getProperty("defaultLogin" , ConfigProvider.configHiddenProperties.login());
    public final static String DEFAULT_VALID_PASSWORD_UI = "123456qwerty";


}
