package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.HeaderElement;

public class PostPage extends ParentPage {
    @FindBy(xpath = ".//div[@class='alert alert-success text-center']")
    private WebElement successMessage;

    @FindBy(xpath = ".//button[@class='delete-post-button text-danger']")
    private WebElement buttonDelete;

    private HeaderElement HeaderElement;
    public PostPage(WebDriver webDriver) {
        super(webDriver);
}
     public PostPage checkIsRedirectToPostPage() {
        // TODO check url
        // TODO Check element
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
        return HeaderElement = new HeaderElement(webDriver);
    }

    public MyProfilePage clickOnDeleteButton() {
        clickOnElement(buttonDelete);

        return new MyProfilePage(webDriver);
    }
}

