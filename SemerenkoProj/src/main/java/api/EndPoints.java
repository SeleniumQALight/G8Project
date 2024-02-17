package api;

import java.net.URI;

public interface EndPoints {
    String BASE_URL = "https://aqa-complexapp.onrender.com";
    String POST_BY_USER = BASE_URL + "/api/postsByAuthor/{0}";  // параметр підстановки USER_NAME .get(EndPoints.POST_BY_USER, USER_NAME)
    String LOGIN = BASE_URL+"/api/login";
}
