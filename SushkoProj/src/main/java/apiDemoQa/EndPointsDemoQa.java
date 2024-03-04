package apiDemoQa;

public interface EndPointsDemoQa {
    String BASE_URL = "https://demoqa.com";
    String LOGIN = BASE_URL + "/Account/v1/Login";
    String BOOKS_BY_USER = BASE_URL + "/Account/v1/User/{0}";
    String LIST_OF_BOOKS = BASE_URL + "/BookStore/v1/Books";
}
