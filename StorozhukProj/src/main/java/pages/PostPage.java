package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.HeaderElement;

public class PostPage extends ParentPage {
    @FindBy(xpath = ".//div[@class='alert alert-success text-center']")
    private WebElement successMessage;

    private HeaderElement headerElement;
    @FindBy(xpath = ".//button[@class='delete-post-button text-danger']")
    private WebElement buttonDelete;
    @FindBy(xpath = ".//h2")
    private WebElement postTitle;

    @FindBy(xpath = ".//div[@class='container py-md-5 container--narrow']/div[5]/p")
    private WebElement postBody;

//    @FindBy(xpath = ".//p[starts-with(text(),'Is this post unique? :')]")
//    private WebElement postUniqueInfoMessage;

    @FindBy(xpath = ".//i[contains(text(), 'Note: This post was written for')]/u")
    private WebElement postVisibilityInfoMessage;

    public PostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "/post/[a-zA-Z0-9]*";
    }

    public PostPage checkIsRedirectToPostPage() {
        checkUrlWithPattern();
        //TODO check element
        return this;
    }

    public PostPage checkIsSuccessMessageDisplayed() {
        checkIsElementVisible(successMessage);
        return this;
    }

    public PostPage checkTextInSuccessMessage(String text) {
        checkTextInElement(successMessage, text);
        return this;
    }

    public HeaderElement getHeader() {
        return headerElement = new HeaderElement(webDriver);
    }

    public PostPage checkTextInPostTitle(String posTextTitle) {
        checkTextInElement(postTitle, posTextTitle);
        return this;
    }

    public PostPage checkTextInPostBody(String postTextBody) {
        checkTextInElement(postBody, postTextBody);
        return this;
    }

    //public PostPage checkTextInUniquePostInfoMessage(boolean option) {
     //   if (option) {
    //        checkTextInElement(postUniqueInfoMessage, "Is this post unique? : yes");
     //   } else {
     //       checkTextInElement(postUniqueInfoMessage, "Is this post unique? : no");
     //   }
     //   return this;
    //}

    private By getPostUniqueInfoMessageNoLocator(String CheckboxStatus) {
        if (CheckboxStatus.equals("check")) {
            return By.xpath(".//p[starts-with(text(),'Is this post unique? : yes')]");
        } else if (CheckboxStatus.equals("uncheck")){
            return By.xpath(".//p[starts-with(text(),'Is this post unique? : no')]");
        } else {
            throw new IllegalArgumentException("CheckboxStatus value should be 'check' or 'uncheck'");
        }
    }

    public PostPage checkTextInUniquePostInfoMessage(String isCheckboxSelected )  {
        checkIsElementVisible(getPostUniqueInfoMessageNoLocator(isCheckboxSelected));
        return this;
    }

    public PostPage checkTextInPostNote(String noteText) {
        checkTextInElement(postVisibilityInfoMessage, noteText);
        return this;
    }


    public MyProfilePage clickOnDeletePostButton() {
        clickOnElement(buttonDelete);
        return new MyProfilePage(webDriver);
    }

}
