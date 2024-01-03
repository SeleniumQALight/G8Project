package pages.elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.CommonActionsWithElements;
import pages.CreatePostPage;

// описывает элементы, которые есть в хедере залогиненного пользователя
public class HeaderElement extends CommonActionsWithElements {


    // create post button
    @FindBy(xpath = "//*[@class='btn btn-sm btn-success mr-2']")
    private WebElement buttonCreatePost;

    @FindBy(xpath = ".//button[text()='Sign Out']")
    private WebElement buttonSignOut;

    @FindBy(xpath = ".//a[text()='Create Post']")
    private WebElement createPostLink;

    @FindBy(xpath = ".//img[@data-original-title='My Profile']/..")
    private WebElement myProfileLink;

    @FindBy(xpath = ".//span[contains(@class,'text-white')and not(@data-original-title)]")
    private WebElement userNameIcon;

    public HeaderElement(WebDriver webDriver) {
        super(webDriver);
    }

    public CreatePostPage clickOnButtonCreatePost() {
        clickOnElement(buttonCreatePost);
        return new CreatePostPage(webDriver);
    }

    public boolean isButtonSignOutDisplayed() {
//        WebElement buttonSignOut = webDriver.findElement(By.xpath(".//button[text()='Sign Out']"));
        return isElementDisplayed(buttonSignOut);
    }

    public HeaderElement isButtonSignOutVisible() {
        checkIsElementVisible(buttonSignOut);
        return this;
    }

    public HeaderElement isButtonCreatePostVisible() {
        checkIsElementVisible(buttonCreatePost);
        return this;
    }

    public HeaderElement isMyProfileLinkVisible() {
        checkIsElementVisible(myProfileLink);
        return this;
    }

    public HeaderElement isUserNameIconVisible() {
        checkIsElementVisible(userNameIcon);
        return this;
    }

    public HeaderElement checkIsHeaderForUserVisible() {
        isButtonSignOutVisible();
        isButtonCreatePostVisible();
        isMyProfileLinkVisible();
        isUserNameIconVisible();
        logger.info("Header for user is visible");
        return this;
    }
}
