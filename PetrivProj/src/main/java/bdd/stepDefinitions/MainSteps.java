package bdd.stepDefinitions;

import bdd.helpers.WebDriverHelper;
import pages.PageProvider;

public class MainSteps {
    WebDriverHelper webDriverHelper;
    PageProvider pageProvider;


    public MainSteps(WebDriverHelper webDriverHelper) {
        this.webDriverHelper = webDriverHelper;
        pageProvider = new PageProvider(webDriverHelper.getWebDriver());
    }
}
