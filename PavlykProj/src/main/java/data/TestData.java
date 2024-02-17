package data;

import libs.ConfigProvider;

// Класс для хранения тестовых данных
// которые будут использоваться в тестах МНОГО РАЗ
public class TestData {

    //public final static String VALID_LOGIN_UI = ConfigProvider.configHiddenProperties.login();
    public final static String VALID_LOGIN_UI = System.getProperty("defaultLogin",ConfigProvider.configHiddenProperties.login());
    public final static String VALID_PASSWORD_UI = "123456qwerty";
    public final static String SMALL_INVALID_TEXT = "tr";

    public final static String VALID_LOGIN_API = "nikitaapi";
    public final static String VALID_PASSWORD_API = "123456qwerty";
}
