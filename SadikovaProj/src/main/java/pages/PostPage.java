package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PostPage extends ParentPage {

    @FindBy(xpath = "//div[contains(@class,'alert-success')]")
    private WebElement postCreatedBlock;

    public PostPage checkIsRedirectToPostPage() {

        return this;
    }

    public PostPage checkIsSuccessMessageDisplayes(){
        checkIsElementVisible(postCreatedBlock);
        return this;
    }

    public  PostPage checkTextPresent(String expectedText){
        checkTextInElement(postCreatedBlock, expectedText);
        return this;
    }

    public PostPage(WebDriver webDriver) {
        super(webDriver);
    }


}
