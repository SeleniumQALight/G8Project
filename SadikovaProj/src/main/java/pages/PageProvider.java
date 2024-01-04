package pages;

import org.openqa.selenium.WebDriver;
import pages.MainPage.LoginForm;
import pages.MainPage.RegistrationForm;
import pages.elements.HeaderElement;

public class PageProvider {

    private WebDriver webDriver;

    public PageProvider(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public LoginForm loginPage() {
        return new LoginForm(webDriver);
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

    public RegistrationForm registrationForm(){return new RegistrationForm(webDriver);}
}
