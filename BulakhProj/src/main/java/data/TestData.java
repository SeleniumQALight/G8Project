package data;

import libs.ConfigProvider;

//клас для зберігання тестових даних
// які будуть використовуватися в тестах БАГАТО РАЗІВ
public class TestData {
    public final static String VALID_LOGIN_UI = System.getProperty("defaultLogin", ConfigProvider.configHiddenProperties.login());
    public final static String VALID_PASSWORD_UI = "123456qwerty";

    public final static String VALID_LOGIN_API = "tanyaBU".toLowerCase();
    public final static String VALID_PASSWORD_API = "nfyz123456789";

    public final static String VALID_LOGIN_API_BOOK = "tanyabu";
    public final static String VALID_PASSWORD_API_BOOK = "Tanya123@";

    public static float privatBankBuyCourse;
    public static float privatBankSaleCourse;
    public static float privatBankBuyCourseWebPage;
    public static float privatBankSaleCourseWebPage;


}
