package api;

public interface EndPointsDemo {
    String BASE_URL = "https://demoqa.com";
    String LOGIN = BASE_URL + "/Account/v1/Login";
    String DELETE_ALL_USER_BOOKS = BASE_URL + "/BookStore/v1/Books?UserId={0}";
    String ALL_BOOKS = BASE_URL + "/BookStore/v1/Books";
    String ALL_USER_BOOKS = BASE_URL + "/Account/v1/User/{0}";
}