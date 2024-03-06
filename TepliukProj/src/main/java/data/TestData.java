package data;

import libs.ConfigProvider;

// клас для зберігання тестових даних
// які будуть використовуватись в тестах БАГАТО РАЗІВ
public class TestData {
    public final static String VALID_LOGIN_UI = System.getProperty("defaultLogin", ConfigProvider.configHiddenProperties.login());
    public final static String VALID_PASSWORD_UI = "123456qwerty";
    public final static String INVALID_USERNAME_UI = "tr";
    public final static String INVALID_EMAIL_UI = "tr";
    public final static String INVALID_PASSWORD_UI = "tr";

    public final static String VALID_LOGIN_API = "tepliuk";
    public final static String VALID_PASSWORD_API = "123456qwerty";

    public final static String VALID_LOGIN_DEMO_QA_API = "atepliuk";
    public final static String VALID_PASSWORD_DEMO_QA_API = "1qazXSW@";
}