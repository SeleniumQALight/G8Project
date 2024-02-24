package apiDemoqa;

public interface EndPointsDemoqa {

    String BASE_URL = "https://demoqa.com";
    String LOGIN = BASE_URL + "/Account/v1/Login";

    String BOOKS_IN_BOOKSTORE = BASE_URL + "/BookStore/v1/Books";
    String DELETE_BOOKS = BASE_URL + "/BookStore/v1/Book";
    String BOOKS_BY_USER = BASE_URL + "/Account/v1/User/{0}";
}