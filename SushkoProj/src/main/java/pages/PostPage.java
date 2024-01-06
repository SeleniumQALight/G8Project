package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.HeaderElement;

public class PostPage extends ParentPage {
    @FindBy(xpath = ".//div[@class='alert alert-success text-center']")
    private WebElement successMessage;

    @FindBy(xpath = ".//p[contains(text(), 'Is this post unique?')]")
    private WebElement textIsThisPostUnique;

    private HeaderElement headerElement;

    public PostPage(WebDriver webDriver) {
        super(webDriver);
    }

    public PostPage checkIsRedirectToPostPage() {
        // TODO check url
        // TODO Check element
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

    public PostPage checkTextThisPostUnique() {
        checkTextInElement(textIsThisPostUnique, "Is this post unique? : yes");
        return this;
    }

}


//        - додати цей метод в наш тест по створенню поста (зі значенням check) і перевірку на наступному скріні (yes або no)
//
//        4. Зробити перевірку на сторінці postPage що ми бачимо текст тайтла, боді - такіж, з якими ми створювали пост
//        (порівняти текс з них з очикуваним, тим який передавали при створені).
//        А також перевірити що бачите текст Note: This post was written for One Person і Is this post unique? : yes/no
//        — це зробити параметризованим локатором (параметр буде yes чи no).

