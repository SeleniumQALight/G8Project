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
    private HeaderElement headerElement;

    private String postUniqueLocator = ".//p[text()='Is this post unique? : %s']";

    private String postTitleElement = "//a[contains(@class,'list-group-item-action')]/strong[text()='%s']";

    @FindBy(xpath = ".//button[@class='delete-post-button text-danger']")
    private WebElement buttonDelete;

    @FindBy(xpath = ".//h2")
    private WebElement postTitleOnPage;

    @FindBy(xpath = "//div[@class='container py-md-5 container--narrow']/div[5]/p")
    private WebElement postBodyOnPage;

    @FindBy(xpath = "//div[3]/p")
    private WebElement postDropdownOnPage;

    @FindBy(xpath = "//div[4]/p")
    private WebElement checkboxText;

    @FindBy(xpath = "//a[@data-original-title='Edit']")
    private WebElement editButton;

    @FindBy(xpath = ".//input[@id='post-title']")
    private WebElement inputTitle;

    @FindBy(xpath = ".//form[contains(@action, '/post/')]//button")
    private WebElement buttonSaveUpdates;

    public PostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "/post/[a-zA-Z0-9]*";
    }

    public PostPage checkIsRedirectToPostPage() {
        checkUrlWithPattern();
        // TODO Check element
        return this;
    }

    private WebElement getIsThisPostUniqueElement(String checkboxText) {
        return webDriver.findElement(
                By.xpath(String.format(postUniqueLocator, checkboxText)));
    }

    public PostPage checkIsSuccessMessageDisplayed() {
        checkIsElementVisible(successMessage);
        return this;
    }

    public PostPage checkTextInSuccessMessage(String text) {
        checkTextInElement(successMessage, text);
        return this;
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

    public PostPage checkIsThisPostUniqueTextPresent (String checkboxText) {
        checkIsElementVisible(getIsThisPostUniqueElement(checkboxText));
        return this;
    }




    public HeaderElement getHeader() {
        return headerElement = new HeaderElement(webDriver);
    }

    public MyProfilePage clickOnDeleteButton() {
        clickOnElement(buttonDelete);
        return new MyProfilePage(webDriver);

    }

    public PostPage clickOnPostWithTitle(String postTitle) {
        clickOnElement(webDriver.findElement(By.xpath(String.format(".//a[text()='%s']", postTitle))));
        return this;
    }

    public PostPage clickOnEditButton() {
        clickOnElement(editButton);
        return this;
    }

    public PostPage enterTitleInToInputTitle (String title) {
        enterTextInToInput(inputTitle, title);
        return this;
    }

    public PostPage clickOnSaveUpdatesButton() {
        clickOnElement(buttonSaveUpdates);
        return this;
    }



    public PostPage checkIsPostUniqueStateConfirmExpectedValue(String valueOfPostUnique){
        WebElement isPostUnique = webDriver.findElement(By.xpath(String.format(postUniqueLocator, valueOfPostUnique)));
        Assert.assertTrue("The post is not in the expected state or not found.", isElementDisplayed(isPostUnique));
        return this;
    }

    public PostPage clickOnPostTitle(String postTitle) {
        clickOnElement(webDriver.findElement(By.xpath(String.format(postTitleElement, postTitle))));
        return new PostPage(webDriver);
    }
}
