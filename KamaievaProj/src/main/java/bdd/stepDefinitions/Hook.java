package bdd.stepDefinitions;

import bdd.helpers.WebDriverHelper;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hook {

    WebDriverHelper webDriverHelper;
    public Hook(WebDriverHelper webdriverHelper){
        this.webDriverHelper = webdriverHelper;
    }
    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {
        webDriverHelper.quitDriver();
    }

}
