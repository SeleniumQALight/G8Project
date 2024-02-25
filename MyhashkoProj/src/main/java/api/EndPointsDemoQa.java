package api;

public interface EndPointsDemoQa {
    String BASE_URL = "https://demoqa.com";
    String LOGIN = BASE_URL + "/Account/v1/Login";

    String All_Books = BASE_URL + "/BookStore/v1/Books";
    String DELETE_BOOKS = BASE_URL + "/BookStore/v1/Book";
    String GET_BOOKS_BY_USER_ID = BASE_URL + "/Account/v1/User/{0}";
}
