package pages;

import org.openqa.selenium.WebDriver;
import pages.privatBank.HomePagePrivatBank;

public class PageProvider {
    private WebDriver webDriver;

    public PageProvider(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public LoginPage loginPage() {
        return new LoginPage(webDriver);
    }

    public HomePage homePage() {
        return new HomePage(webDriver);
    }

    public PostPage getPostPage() {
        return new PostPage(webDriver);
    }

    public MyProfilePage getMyProfilePage() {
        return new MyProfilePage(webDriver);
    }

    public HomePagePrivatBank homePagePrivatBank() {
        return new HomePagePrivatBank(webDriver);
    }
}
