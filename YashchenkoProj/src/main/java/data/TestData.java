package data;

import libs.ConfigProvider;

import java.util.HashMap;

public class TestData {
    public final static String DEFAULT_VALID_LOGIN_UI = System.getProperty("defaultLogin" , ConfigProvider.configHiddenProperties.login());
    public final static String DEFAULT_VALID_PASSWORD_UI = "123456qwerty";

    public final static String VALID_LOGIN_API = "tymofiiapi";
    public final static String VALID_PASSWORD_API = "123456qwerty";

    public final static String DEMO_QA_VALID_LOGIN = "yashchenkodemo";
    public final static String DEMO_QA_VALID_PASSWORD = "Aa123456!";

    public static HashMap<String, Double> apiRates;
    public static HashMap<String, Double> uiRates;
}
