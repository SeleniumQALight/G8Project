package bdd.stepDefinitions;

import api.ApiHelper;
import bdd.helpers.WebDriverHelper;
import data.TestData;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hook {
    WebDriverHelper webDriverHelper;
    ApiHelper apiHelper = new ApiHelper();

    public Hook(WebDriverHelper webDriverHelper){
        this.webDriverHelper = webDriverHelper;
    }

    @Before(order = 0)
    public void setup() {

    }


    @After(order = 0)
    public void tearDown() {
        webDriverHelper.quitDriver();
    }

    @Before(value = "@deleteAllPostsForDefaultUser", order = 50)
    @After(value = "@deleteAllPostsForDefaultUser", order = 50)
    public void deletePostsForDefaultUser(){
        apiHelper.deleteAllPostsTillPresent();
    }

    @Before
    public void setDefaultCursData(){
        TestData.api_curs_buy = "";
        TestData.api_curs_sale = "";
        TestData.ui_curs_buy = "";
        TestData.ui_curs_sale = "";
    }

}
