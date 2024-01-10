package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.elements.HeaderElement;

public class HomePage extends ParentPage {
    private HeaderElement headerElement;

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public HeaderElement getHeader() {
        return new HeaderElement(webDriver);
    }

    public LoginPage redirectOnLoginPage (){
        return new LoginPage(webDriver);
    }

    public HomePage checkIsButtonCreateNewPostVisible() {
        getHeader().checkIsButtonCreateNewPostVisible();
        return this;
    }

    public HomePage checkIsButtonSignOutVisible() {
        getHeader().checkIsButtonSignOutVisible();
        return this;
    }

    public HomePage checkIsLinkMyProfileVisible() {
        getHeader().checkIsButtonMyProfileVisible();
        return this;
    }

    public HomePage checkIsSpanUserNameVisible() {
        getHeader().checkIsSpanUserNameVisible();
        return this;
    }

    public HomePage checkIsButtonChatVisible(){
        getHeader().checkIsButtonChatVisible();
        return this;
    }

//    public HomePage checkIsInputUsernameUnvisible() {
//        getHeader().checkIsElementInputUsernameUnvisible();
//        return this;
//    }
//
//    public HomePage checkIsInputPasswordUnvisible() {
//        getHeader().checkIsElementInputPasswordUnvisible();
//        return this;
//    }
//
//    public HomePage checkIsButtonSignInUnvisible(){
//        getHeader().checkIsElementButtonSignInUnvisible();
//        return this;
//    }

    public HomePage checkIsRedirectToHomePage() {
        getHeader().checkIsButtonSignOutVisible();
        return this;
    }
}
