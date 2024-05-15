package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.HeaderElement;

public class PostPage extends ParentPage{
    @FindBy(xpath = ".//div[@class='alert alert-success text-center']")
    private WebElement successMessage;

    HeaderElement headerElement;

    @FindBy(xpath = ".//button[@class='delete-post-button text-danger']")
    private WebElement buttonDelete;

    @FindBy(xpath = ".//h2")
    private WebElement postTitle;

    @FindBy(xpath = "//div[@class='body-content']/p[not(i)]")
    private WebElement postBody;

    @FindBy(xpath = ".//div[@class='body-content']/p/i/u")
    private WebElement expectedStatus;

    @FindBy(xpath = "//div[@class='body-content']/p/i/u")
    private WebElement dropdownValue;

    private String postUniqueLocator = ".//p[contains(text(),'Is this post unique? : %s')]";
    private String postDropdownLocator = ".//u[contains(text(),'%s')]";

    public PostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "/post/[a-zA-Z0-9]*";
    }


    public PostPage checkIsRedirectToPostPage() {
        checkUrlWithPattern();
        return this;
    }

    public PostPage checkIsSuccessMassageDisplayed() {
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

    public MyProfilePage clickOnDeleteButton() {
        clickOnElement(buttonDelete);
        return new MyProfilePage(webDriver);
    }

    public PostPage checkPostTitleIsPresentOnPostPage(String text) {
        checkTextInElement(postTitle, text);
        return this;
    }

    public PostPage checkPostBodyIsPresentOnPostPage(String text) {
        checkTextInElement(postBody, text);
        return this;
    }

    public PostPage checkDropdownValueIsPresentOnPostPage(String dropdownValue) {
        WebElement dropdownValueIsPresent = webDriver.findElement(By.xpath(String.format(postDropdownLocator, dropdownValue)));
        Assert.assertTrue("The post is not in the expected state or not found.", isElementDisplayed(dropdownValueIsPresent));
        return this;
    }

    public PostPage checkIsPostUniqueStateConfirmExpectedValue(String checkboxValue) {
        WebElement isPostUnique = webDriver.findElement(By.xpath(String.format(postUniqueLocator, checkboxValue)));
        Assert.assertTrue("The post is not in the expected state or not found.", isElementDisplayed(isPostUnique));
        return this;
    }
}
