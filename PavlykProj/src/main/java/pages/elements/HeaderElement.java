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

    public HeaderElement(WebDriver webDriver) {
        super(webDriver);
    }

    public CreatePostPage clickOnButtonCreatePost() {
        clickOnElement(buttonCreatePost);
        return new CreatePostPage(webDriver);
    }

    public boolean isButtonSignOutVisible() {
//        WebElement buttonSignOut = webDriver.findElement(By.xpath(".//button[text()='Sign Out']"));
        return isElementDisplayed(buttonSignOut);
    }
}