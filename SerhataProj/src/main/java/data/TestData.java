package data;

import libs.ConfigProvider;

// клас для зберігання тестових даних
//які будуть використовуватися в тестах БАГАТО РАЗІВ
public class TestData {
    public final static String VALID_LOGIN_UI = System.getProperty("defaultLogin", ConfigProvider.configHiddenProperties.login() );
    public final static String VALID_PASSWORD_UI = "123456qwerty";
    public final static String INVALID_DATA_FOR_SIGN_IN = "tr";
    public final static String VALID_LOGIN_API = "serhataapi";
    public final static String VALID_PASSWORD_API = "serhataolena0211";
    public final static String VALID_LOGIN_API_BOOKS = "OlenaSer";
    public final static String VALID_PASSWORD_API_BOOKS = "Olena123!";
}
