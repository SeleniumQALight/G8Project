package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.HeaderElement;

import java.util.List;

public class PostPage extends ParentPage {

    @FindBy(xpath = "//div[contains(@class,'alert-success')]")
    private WebElement postCreatedBlock;
    @FindBy(xpath = "//div[contains(@class,'justify-content-between')]/h2")
    private WebElement titleField;
    @FindBy(xpath = "//div[@class='body-content']/p[not(i)]")
    private WebElement bodyField;


    private String thisIsPostUnique = "//div/p[text()='Is this post unique? : %s']";
    private String noteBlock = "//i[contains(text(),'Note: This post was written for')]/u[contains(text(),'%s')]";

    private WebElement getThisIsPostUniqueWebElement(String text) {
        return webDriver.findElement(By.xpath(String.format(thisIsPostUnique, text)));
    }

    private WebElement getNoteBlockWebElement(String text) {
        return webDriver.findElement(By.xpath(String.format(noteBlock, text)));
    }

    private pages.elements.HeaderElement headerElement;

    @FindBy(xpath = "//button[contains(@class,'delete-post-button')]")
    private WebElement deleteButton;

    public PostPage checkIsRedirectToPostPage() {
        checkUrlWithPatternUrl();
        return this;
    }

    public PostPage checkIsSuccessMessageDisplayes() {
        checkIsElementVisible(postCreatedBlock);
        return this;
    }

    public PostPage checkThisIsPostUniqueWebElementIsPresent(String value) {
        checkIsElementVisible(getThisIsPostUniqueWebElement(value));
        return this;
    }

    public PostPage checkNoteBlockWebElementIsPresent(String value) {
        checkIsElementVisible(getNoteBlockWebElement(value));
        return this;
    }



    public PostPage checkTextPresent(String expectedText) {
        checkTextInElement(postCreatedBlock, expectedText);
        return this;
    }

    public PostPage checkTitleTextIsEquals(String expectedText) {
        checkTextInElement(titleField, expectedText);
        return this;
    }

    public PostPage checkBodyTextIsEquals(String expectedText) {
        checkTextInElement(bodyField, expectedText);
        return this;
    }

    @Override
    protected String getRelativeUrl() {
        return "/post/[a-z0-9]*";
    }

    public HeaderElement getHeaderElement() {
        return headerElement = new pages.elements.HeaderElement((webDriver));
    }

    public PostPage(WebDriver webDriver) {
        super(webDriver);
    }

    public MyProfilePage clickOnDeleteButton(){
        clickOnElement(deleteButton);
        return new MyProfilePage(webDriver);
    }


}
