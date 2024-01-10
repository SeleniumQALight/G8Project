package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.HeaderElement;

public class PostPage extends ParentPage {

    @FindBy(xpath = ".//div[@class='alert alert-success text-center']")
    private WebElement successMessage;

    private String isThisPostUniqueValue = "//p[contains(text(), 'Is this post unique? : %s')]";
    private String createdPostTitle = "//h2[text()='%s']";
    private String createdPostBody = "//p[text()='%s']";

    private HeaderElement headerElement;

    public PostPage(WebDriver webDriver) {
        super(webDriver);
    }

    public PostPage checkIsRedirectedToPostPage() {
        // TODO check url
        // TODO check some element on page
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

    public PostPage checkIsThisPostUniqueValuePresent(String value) {
        checkIsElementVisible(webDriver.findElement(By.xpath(String.format(isThisPostUniqueValue, value))));
        return this;
    }

    public PostPage checkCreatedPostTitle(String value) {
        checkIsElementVisible(webDriver.findElement(By.xpath(String.format(createdPostTitle, value))));
        return this;
    }

    public PostPage checkCreatedPostBody(String value){
        checkIsElementVisible(webDriver.findElement(By.xpath(String.format(createdPostBody, value))));
        return this;
    }
}
