package data;

import libs.ConfigProvider;

// клас для зберігання тестових даних
// які будуть використовуватися в тестах БАГАТО РАЗІВ
public class TestData {
    public final static String VALID_LOGIN_UI = System.getProperty("defaultLogin", ConfigProvider.configHiddenProperties.login());
    public final static String VALID_PASSWORD_UI = "123456qwerty";

    public final static String ERROR_USERNAME = "Username must be at least 3 characters.";
    public final static String ERROR_USERNAME_SYMBOLS = "Username can only contain letters and numbers.";
    public final static String ERROR_EMAIL = "You must provide a valid email address.";
    public final static String ERROR_PASSWORD = "Password must be at least 12 characters.";
    public final static String ERROR_PASSWORD_CHARACTERS_COUNT = "Password cannot exceed 50 characters.";
    public final static String SEMICOLON = ";";

    public final static String VALID_LOGIN_API = "vladyslava";
    public final static String VALID_PASSWORD_API = "123456qweqwe";

    public final static String VALID_LOGIN_API_DEMOQA = "vladyslava2";
    public final static String VALID_PASSWORD_API_DEMOQA = "Qwer1234!";

    public static float buyRatePrivatAPI;
    public static float saleRatePrivatAPI;
    public static float buyRatePrivatUI;
    public static float saleRatePrivatUI;
}
