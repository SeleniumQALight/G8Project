package api;

import java.net.URI;

public interface EndPoints {
    String BASE_URL = "https://aqa-complexapp.onrender.com";
    String POSTS_BY_USER = BASE_URL + "/api/postsByAuthor/{0}";
    String CREATE_POST = BASE_URL + "/api/create-post";
    String LOGIN = BASE_URL +  "/api/login";
    String DELETE_POST = BASE_URL + "/api/post/{0}";
}
