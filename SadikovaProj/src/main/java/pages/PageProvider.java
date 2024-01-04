package pages;

import org.openqa.selenium.WebDriver;
import pages.elements.HeaderElement;

public class PageProvider {

    private WebDriver webDriver;

    public PageProvider(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public LoginPage loginPage() {
        return new LoginPage(webDriver);
    }

    public HeaderElement headerElement() {
        return new HeaderElement(webDriver);
    }

    public HomePage homePage() {
        return new HomePage(webDriver);
    }

    public CreatePostPage createPostPage() {
        return new CreatePostPage(webDriver);
    }

    public PostPage postPage() {
        return new PostPage(webDriver);
    }

    public MyProfilePage myProfilePage(){return new MyProfilePage(webDriver);}
}
