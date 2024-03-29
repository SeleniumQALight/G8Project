package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.HeaderElement;

public class PostPage extends ParentPage{
    @FindBy(xpath = ".//div[@class='alert alert-success text-center']")
    private WebElement successMessage;
    @FindBy(xpath = "//h2")
    private WebElement titleOnPostPage;
    @FindBy(xpath = ".//div[@class='body-content'][2]//p")
    private WebElement BodyOnPostPage;
    @FindBy(xpath = "//i[contains(text(),' Note: This post was written for ')]")

    private HeaderElement headerElement;
    @FindBy(xpath = ".//button[@data-original-title='Delete']")
    private WebElement buttonDelete;
    private String checkBoxIsThisPostUnique ="//div/p[contains(text(),'Is this post unique? : %s')]";
    private String noteOnPostPageValue = "//i//u[contains(text(),'%s')]";


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

    public MyProfilePage clickOnDeleteButton() {
        clickOnElement(buttonDelete);
        return new MyProfilePage(webDriver);
    }

    public PostPage chekStatusOfCheckBoxIsThisPostUniqueOnPostPage(String text) {
        checkIsElementVisible(webDriver.findElement(By.xpath(String.format(checkBoxIsThisPostUnique, text))));
        return this;
    }

    public PostPage checkTitleOnPostPageEqualsTileOnCreatePostPage() {
        checkTextInElement(titleOnPostPage, CreatePostPage.getEnteredTitle());
        logger.info("Title on Post page equals title on Create Post page");
            return this;
        }

    public PostPage checkBodyOnPostPageEqualsTileOnCreatePostPage() {
        checkTextInElement(BodyOnPostPage, CreatePostPage.getEnteredBody());
        logger.info("Body on Post page equals body on Create Post page");
        return this;
    }

    public PostPage checkOfNoteTextValue(String valueInDropDown) {
        checkIsElementVisible(webDriver.findElement(By.xpath(String.format(noteOnPostPageValue, valueInDropDown))));

        return this;
    }
}
