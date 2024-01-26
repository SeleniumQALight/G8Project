package data;

import libs.ConfigProvider;

// class for storing test data
// we can use a lot of times
public class TestData {
    public final static String VALID_LOGIN_UI = System.getProperty("defaultLogin",ConfigProvider.configHiddenProperties.login());
    public final static String VALID_PASSWORD_UI = "123456qwerty";
}
