package data;
import libs.ConfigProvider;
// клас для зберігання тестових даних
// які будуть використовуватися в тестах БАГАТО РАЗІВ

public class TestData {
    public final static String VALID_LOGIN_UI = System.getProperty("defaultLogin", ConfigProvider.configHiddenProperties.login());
    public final static String VALID_PASSWORD_UI = "123456qwerty";

    public final static String VALID_LOGIN_API = "aszyre123456";
    public final static String VALID_PASSWORD_API = "aszyre123456";

    public final static String VALID_LOGIN_API_DEMOQA = "aszyre123456";
    public final static String VALID_PASSWORD_API_DEMOQA = "Aszyre123456@";

    public static String api_curs_buy;
    public static String api_curs_sale;
    public static String ui_curs_buy;
    public static String ui_curs_sale;
}
