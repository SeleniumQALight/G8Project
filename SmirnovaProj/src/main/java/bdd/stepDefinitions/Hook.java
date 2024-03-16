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
    public void setup() {
        System.out.println("Before hook");
    }

    @After
    public void teardown() {
        webDriverHelper.quitDriver();
    }
}
