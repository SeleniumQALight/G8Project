package pages;


        import org.junit.Assert;
        import org.openqa.selenium.WebDriver;
        import org.openqa.selenium.WebElement;
        import org.openqa.selenium.support.FindBy;
        import pages.elements.HeaderElement;

public class HomePage extends ParentPage {
    private HeaderElement headerElement;

    @FindBy(xpath = "//a[text()='Create Post']")
    private WebElement buttonCreatePost;

    @FindBy(xpath = "//a[@class=\"mr-2\"]")
    private WebElement buttonMyProfile;

    @FindBy(xpath = "//span[@class='text-white mr-2']")
    private WebElement userName;


    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public HomePage checkIsRedirectToHomePage() {
        // TODO check url
        Assert.assertTrue("Invalid page - not Home page"
                , getHeader().isButtonSignOutVisible());
        return this;
    }

    public HeaderElement getHeader() {
        return new HeaderElement(webDriver);
    }

    public boolean isButtonCreatePostVisible() {
        return  buttonCreatePost.isDisplayed();

    }

    public boolean isButtonMyProfileVisible() {
        return buttonMyProfile.isDisplayed();
    }

    public boolean isUserNameVisible() {
        return userName.isDisplayed();
    }




}
