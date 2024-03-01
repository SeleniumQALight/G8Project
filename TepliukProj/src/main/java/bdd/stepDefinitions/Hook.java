package bdd.stepDefinitions;

import bdd.helpers.WebDriverHelper;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hook {
    WebDriverHelper webDriverHelper;

    public Hook(WebDriverHelper webDriverHelper) {
        this.webDriverHelper = webDriverHelper;
    }

    @Before
    public void setUp() {
        //WebDriver
        //PageProvider
    }

    @After
    public void tearDown() {
        webDriverHelper.quitDriver();
    }
}
