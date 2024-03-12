package api;

public interface EndPoints {
    String BASE_URL = "https://aqa-complexapp.onrender.com";
    String POSTS_BY_USER = BASE_URL + "/api/postsByAuthor/{0}";
    String LOGIN =BASE_URL + "/api/login";
    String CREATE_POST = BASE_URL + "/api/create-post";
    String DELETE_POST = BASE_URL + "/api/post/{0}";
    String DEMOQA_BASE_URL = "https://demoqa.com";
    String DEMOQA_LOGIN = DEMOQA_BASE_URL + "/Account/v1/Login";
    String BOOKS = DEMOQA_BASE_URL + "/BookStore/v1/Books";
    String DELETE_BOOKS = DEMOQA_BASE_URL + "/BookStore/v1/Book";
    String BOOKS_BY_USER = DEMOQA_BASE_URL + "/Account/v1/User/{0}";
    String BASE_URL_PRIVATBANK = "https://api.privatbank.ua/p24api";
    String PUB_INFO = "/pubinfo";
}
