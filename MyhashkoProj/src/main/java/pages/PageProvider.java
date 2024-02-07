package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public class PageProvider {
    private WebDriver webDriver;

    public PageProvider(WebDriver webDriver) {
        this.webDriver = webDriver;
    }
    @Step
    public LoginPage loginPage() {
        return new LoginPage(webDriver);
    }
    @Step
    public HomePage homePage() {
        return new HomePage(webDriver);
    }
    @Step
    public PostPage getPostPage() {
        return new PostPage(webDriver);
    }
}
