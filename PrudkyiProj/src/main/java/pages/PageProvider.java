package pages;

import org.openqa.selenium.WebDriver;

public class PageProvider {
    private WebDriver webDriver;

    public PageProvider(WebDriver webDriver) {
        this.webDriver = webDriver;
    }
    public LoginPage loginPage() {
        return new LoginPage(webDriver);
    }

    public HomePages homePage() {
        return new HomePages(webDriver);
    }

    public PostPage getPostPage() {
        return new PostPage(webDriver);
    }
}
