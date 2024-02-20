package data;

import libs.ConfigProvider;

//клас для зберігання тестових даних
//які ми будемо використовувати в тестах багато разів
public class TestData {
    public final static String VALID_LOGIN_UI = System.getProperty( "defaultLogin", ConfigProvider.configHiddenProperties.login());
    public final static String VALID_PASSWORD_UI = "123456qwerty";
    public final static String VALID_LOGIN_API = "greg";
    public final static String VALID_PASSWORD_API = "monoment123456";
}
