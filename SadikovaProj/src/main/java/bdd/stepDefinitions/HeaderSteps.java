package bdd.stepDefinitions;

import bdd.helpers.WebDriverHelper;
import io.cucumber.java.en.When;

public class HeaderSteps extends MainSteps{


    public HeaderSteps(WebDriverHelper webDriverHelper) {
        super(webDriverHelper);
    }

    @When("I click on button MyProfile On Header Element")
    public void iClickOnButtonMyProfileOnHeaderElement() {
        pageProvider.headerElement().clickOnMyProfileButton();
    }
}
