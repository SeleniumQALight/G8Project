package data;

import libs.ConfigProvider;

//клас для зберігання тестових даних
//які будуть використовуватися в тестах БАГАТО РАЗІВ
public class TestData {
    public final static String VALID_LOGIN_UI = System.getProperty("defaultLogin",  ConfigProvider.configHiddenProperties.login());

    public final static String VALID_PASSWORD_UI = "123456qwerty";

    public final static String VALID_LOGIN_API = "lupiksasha";
    public final static String VALID_PASSWORD_API = "123456qwerty";

}

