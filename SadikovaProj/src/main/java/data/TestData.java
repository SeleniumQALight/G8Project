package data;

import libs.ConfigProvider;

public class TestData {

    public final static String VALID_LOGIN_UI = System.getProperty("defaultLogin", ConfigProvider.configHiddenProperties.login()) ,
            VALID_PASSWORD = "123456qwerty",
    INVALID_DATA = "tr",
   WRONG_LOGIN = "qaautotest";

    public final static String LOGIN_API = "qamasha";
    public final static String VALID_PASSWORD_API = "123456qwerty";






}
