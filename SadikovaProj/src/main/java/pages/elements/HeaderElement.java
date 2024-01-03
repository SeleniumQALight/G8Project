package pages.elements;

import jdk.jfr.Description;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.CommonActionsWithElements;
import pages.CreatePostPage;
import pages.LoginPage;

// описание элементов в хедере

public class HeaderElement extends CommonActionsWithElements {

    LoginPage loginPage = new LoginPage(webDriver);

    /**
     * User
     */
    @FindBy(xpath = "//a[text()='Create Post']")
    private WebElement createPostButton;
    @FindBy(xpath = ".//button[text()='Sign Out']")
    private WebElement signOutButton;
    @FindBy(xpath = "//a[contains(@href,'/profile')]/..//span[contains(@class,'text-white') and not (contains(@class,'header-chat-icon'))]")
    private WebElement userName;
    @FindBy(xpath = "//a[contains(@href,'/profile/')]/img")
    private WebElement myProfileIcon;
    @FindBy(xpath = "//a[contains(@class,'header-search-icon')]")
    private WebElement searchLink;
    @FindBy(xpath = "//span[contains(@class,'header-chat-icon')]")
    private WebElement chatIcon;

    /**
     *VISIBLE
     */
    public HeaderElement signOutButtonIsVisible() {
        checkIsElementVisible(signOutButton);
        return this;
    }

    public HeaderElement searchLinkIsVisible() {
        checkIsElementVisible(searchLink);
        return this;
    }

    public HeaderElement chatIconIsVisible() {
        checkIsElementVisible(chatIcon);
        return this;
    }

    public HeaderElement createPostButtonIsVisible() {
        checkIsElementVisible(createPostButton);
        return this;
    }

    public HeaderElement myProfileIconIsVisible() {
        checkIsElementVisible(myProfileIcon);
        return this;
    }

    public HeaderElement userNameIsVisible() {
        checkIsElementVisible(userName);
        return this;
    }

    /**
     * NOT visible
     */
    public HeaderElement signOutButtonIsNotVisible() {
        checkElementIsNotDisplayed(signOutButton);
        return this;
    }

    public HeaderElement searchLinkIsNotVisible() {
        checkElementIsNotDisplayed(searchLink);
        return this;
    }

    public HeaderElement chatIconIsNotVisible() {
        checkElementIsNotDisplayed(chatIcon);
        return this;
    }

    public HeaderElement createPostButtonIsNotVisible() {
        checkElementIsNotDisplayed(createPostButton);
        return this;
    }

    public HeaderElement myProfileIconIsNotVisible() {
        checkElementIsNotDisplayed(myProfileIcon);
        return this;
    }

    public HeaderElement userNameIsNotVisible() {
        checkElementIsNotDisplayed(userName);
        return this;
    }

    /**
     * CLICKS
     */

    public CreatePostPage clickCreatePostButton() {
        clickOnElement(createPostButton);
        return new CreatePostPage(webDriver);
    }

    public HeaderElement clickSignOutButton() {
        clickOnElement(signOutButton);
        return new HeaderElement(webDriver);
    }


    @Description("Проверить отображение всех элементов для пользователя в хедере")
    public HeaderElement assertUserLoginHeaderElements() {
        searchLinkIsVisible();
        chatIconIsVisible();
        userNameIsVisible();
        myProfileIconIsVisible();
        createPostButtonIsVisible();
        checkIsElementVisible(signOutButton);
        loginPage.loginFieldNotVisible();
        loginPage.passwordFieldNotVisible();
        loginPage.signInButtonIsNotVisible();
        return this;
    }

    @Description("Проверить отображение всех элементов для гостя в хедере")
    public HeaderElement assertGuestHeaderHasElements() {
        searchLinkIsNotVisible();
        chatIconIsNotVisible();
        userNameIsNotVisible();
        myProfileIconIsNotVisible();
        createPostButtonIsNotVisible();
        checkElementIsNotDisplayed(signOutButton);
        loginPage.passwordFieldIsVisible();
        loginPage.loginFieldIsVisible();
        loginPage.signInButtonIsVisible();
        return this;
    }


    public HeaderElement(WebDriver webDriver) {
        super(webDriver);
    }
}
