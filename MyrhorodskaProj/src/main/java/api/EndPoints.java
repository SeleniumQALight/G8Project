package api;


public interface EndPoints {
        String BASE_URL = "https://aqa-complexapp.onrender.com";
        String POSTS_BY_USER = BASE_URL + "/api/postsByAuthor/{0}";//rest assure put x in link . It's parametr
        String LOGIN = BASE_URL + "/api/login";
        String CREATE_POST = BASE_URL + "/api/create-post";
        String DELETE_POST = BASE_URL + "/api/post/{0}";
}
