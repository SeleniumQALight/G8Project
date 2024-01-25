package data;

import libs.ConfigProvider;

//клас для зберігання тестових даних
//які ми будемо використовувати в тестах багато разів
public class TestData {
    public final static String VALID_LOGIN_UI = System.getProperty( "defaultlogin", ConfigProvider.configHiddenProperties.login());
    public final static String VALID_PASSWORD_UI = "123456qwerty";
}
