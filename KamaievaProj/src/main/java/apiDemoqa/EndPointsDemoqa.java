package apiDemoqa;

public interface EndPointsDemoqa {
    String BASE_URL = "https://demoqa.com";
    String ACCOUNT_LOGIN = BASE_URL + "/Account/v1/Login";
    String ACCOUNT_GET_USER_BY_ID = BASE_URL + "/Account/v1/User/{0}";
    String BOOK_STORE_LIST_OF_BOOKS = BASE_URL + "/BookStore/v1/Books";
    String BOOK_STORE_LIST_OF_BOOKS_BY_USER = BASE_URL + "/BookStore/v1/Books?UserId={0}";
}
