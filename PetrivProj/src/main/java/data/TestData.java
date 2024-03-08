package data;

import libs.ConfigProvider;

// клас для зберігання тестових даних, які будуть використовуватись в тестах БАГАТО разів
public class TestData {
    public final static String VALID_LOGIN_UI = System.getProperty("defaultLogin", ConfigProvider.configHiddenProperties.login());
    public final static String VALID_PASSWORD_UI = "123456qwerty";

    public final static String VALID_LOGIN_API = "yuliiaapi";
    public final static String VALID_PASSWORD_API = "123456qwerty";

    public final static String VALID_LOGIN_DEMO_QA_API = "yuliiapetriv";
    public final static String VALID_PASSWORD_DEMO_QA_API = "!123Qwerty";

    public static Double USD_BUY_RATE_UI = 0.0;
    public static Double USD_SALE_RATE_UI = 0.0;

    public static Double EUR_BUY_RATE_UI = 0.0;
    public static Double EUR_SALE_RATE_UI = 0.0;
}
