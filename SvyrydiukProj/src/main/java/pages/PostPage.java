package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.HeaderElement;

public class PostPage extends ParentPage {
    @FindBy(xpath = ".//div[@class='alert alert-success text-center']")
    private WebElement successMessage;

    @FindBy(xpath = ".//h2")
    private WebElement postTitleOnPage;

    private HeaderElement headerElement;
    @FindBy(xpath = "//div[@class='container py-md-5 container--narrow']/div[5]/p")
    private WebElement postBodyOnPage;

    @FindBy(xpath = "//div[3]/p")
    private WebElement postDropdownOnPage;

    @FindBy(xpath = "//div[4]/p")
    private WebElement checkboxText;


    public PostPage(WebDriver webDriver) {
        super(webDriver);
    }

    public PostPage checkIsRedirectToPostPage() {
        // TODO check url
        //TODO check element
        return this;
    }

    public PostPage checkIsSuccessMessageDisplayed() {
        checkIsElementVisible(successMessage);
        return this;
    }

    public PostPage checkTextInSuccessMessage(String text) {
        checkTextInElement(successMessage, text);
        return this;
    }

    public HeaderElement getHeader() {
        return headerElement = new HeaderElement(webDriver);
    }

    public PostPage checkIsCreatedPostHasTitle(String postTitle) {
        checkTextInElement(postTitleOnPage, postTitle);
        return this;
    }

    public PostPage checkIsCreatedPostHasBody(String postBody) {
        checkTextInElement(postBodyOnPage, postBody);
        return this;
    }

    public PostPage checkIsCreatedPostHasValueInDropDown(String valueInDropDown) {
        checkTextInElement(postDropdownOnPage, "Note: This post was written for " +valueInDropDown);
        return this;
    }

    public void checkCheckboxStatus(String checkboxValue) {
        checkTextInElement(checkboxText, "Is this post unique? : " + checkboxValue);
    }
}
