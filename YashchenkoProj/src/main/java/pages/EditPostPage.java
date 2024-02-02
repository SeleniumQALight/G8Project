package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.HeaderElement;

public class EditPostPage extends ParentPage{
    public EditPostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @FindBy(xpath = "//button[@class='btn btn-primary']")
    private WebElement saveUpdatesButton;

    @FindBy(xpath = ".//input[@id='post-title']")
    private WebElement inputTitle;

    @Override
    protected String getRelativeUrl() {
        return "/post/[a-zA-Z0-9]*/edit";
    }

    public EditPostPage enterTextInToInputTitle(String title) {
        enterTextIntoInput(inputTitle, title);
        return this;
    }

    public EditPostPage clickOnSaveUpdated(){
        clickOnElement(saveUpdatesButton);
        return this;
    }

    public EditPostPage checkIsRedirectedToEditPostPage(){
        checkCurrentUrlWithPattern();
        return this;
    }

    public HeaderElement getHeader() {
        return new HeaderElement(webDriver);
    }

}
