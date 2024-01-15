package libs;

//клас ля зберігання тестових даних
//які будуть використовуватися в тестах багато разів
public class TestData {
    public final static String VALID_LOGIN_UI = "qaauto";
    public final static String VALID_PASSWORD_UI = "123456qwerty";
    public final static String INVALID_LOGIN_UI = "qaauto3";
    public final static String INVALID_PASSWORD_UI = "qwerty";
    public final static String POST_TITLE = "TC_001_semerenko_" + Util.getDateAndTimeFormatted();
    public final static String POST_BODY = "New Post Body";
    public final static String DROPDOWN_VALUE = "One Person";
}
