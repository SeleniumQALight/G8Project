package api.demoQA;

public interface DemoQaEndPoints {
    String DEMO_QA_BASE_URL = "https://demoqa.com";
    String DEMO_QA_LOGIN = DEMO_QA_BASE_URL + "/Account/v1/Login";
    String DEMO_QA_GET_USER_BOOKS = DEMO_QA_BASE_URL + "/Account/v1/User/{0}";
    String DEMO_QA_DELETE_BOOK = DEMO_QA_BASE_URL + "/BookStore/v1/Book";
    String DEMO_QA_GET_ALL_BOOKS = DEMO_QA_BASE_URL + "/BookStore/v1/Books";
    String DEMO_QA_ADD_BOOK = DEMO_QA_BASE_URL + "/BookStore/v1/Books";
    String DEMO_QA_DELETE_ALL_USER_BOOKS = DEMO_QA_BASE_URL + "/BookStore/v1/Books";
}
