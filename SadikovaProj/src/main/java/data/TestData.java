package data;

import libs.ConfigProvider;

public class TestData {

    public final static String VALID_LOGIN_UI = System.getProperty("defaultLogin", ConfigProvider.configHiddenProperties.login()) ,
            VALID_PASSWORD = "123456qwerty",
    INVALID_DATA = "tr",
   WRONG_LOGIN = "qaautotest";

    public final static String LOGIN_API = "mariiastest";
    public final static String VALID_PASSWORD_API = "123456789012";
    public final static String VALID_PASSWORD_API_DEMO_QA =   "P@ssw0rdabc";
    public final static String LOGIN_DEMO_QA = "mariiaqa";






}
