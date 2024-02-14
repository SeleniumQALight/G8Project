package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.HeaderElement;

public class EditPostPage extends ParentPage {
    @FindBy(xpath = ".//a[text()='« Back to post permalink']")
    private WebElement linkBackToPost;

    @FindBy(xpath = ".//input[@id='post-title']")
    private WebElement inputTitle;

    @FindBy(xpath = ".//textarea[@id='post-body']")
    private WebElement inputBody;

    @FindBy(tagName = "select")  // .//select
    private WebElement dropDownSelectValue;

    @FindBy(xpath = ".//button[@class='btn btn-primary']")
    private WebElement buttonSaveUpdates;

    @FindBy(xpath = ".//input[@type='checkbox']")
    private WebElement checkBox;

    @FindBy (xpath = ".//div[text()='Post successfully updated.']")
    private WebElement messageSuccessEditPost;

    public EditPostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "/create-post";
    }

    // check if we are on the Create Post page
    public EditPostPage checkIsRedirectToCreatePostPage() {
        //TODO
        Assert.assertTrue("Invalid page - not Create Post Page", isElementDisplayed(inputTitle));
        return this;
    }

    // fill in the title field
    public EditPostPage enterTextIntoInputTitle(String title) {
        enterTextIntoInput(inputTitle, title);
        return this;
    }

    public EditPostPage enterTextIntoInputBody(String body) {
        enterTextIntoInput(inputBody, body);
        return this;
    }

    //select Text in dropDowm
    public EditPostPage selectTextInDropDown(String textInDropDown) {
        selectTextInDropDown(dropDownSelectValue, textInDropDown);
        return this;
    }

    //select value in dropDown
    public EditPostPage selectValueInDropDown(String valueInDropDown) {
        selectValueInDropDown(dropDownSelectValue, valueInDropDown);
        return this;
    }

    public EditPostPage setCheckedStateOfCheckBox() {
        try {
            if (getElementProperty(checkBox, "checked").equals("false")) {
                clickOnElement(checkBox);
            } else {
                logger.info("CheckBox has checked state. Set checked state don't need");
            }
        } catch (Exception e) {
            logger.info("Set checked state for checkBox incomplete");
        }
        return this;
    }

    public EditPostPage setUncheckedStateOfCheckBox() {
        try {
            if (getElementProperty(checkBox, "checked").equals("true")) {
                clickOnElement(checkBox);
            } else {
                logger.info("CheckBox has unchecked state. Set unchecked state don't need");
            }
        } catch (Exception e) {
            logger.info("set unchecked state for checkBox incomplete");
        }
        return this;
    }

    public EditPostPage setStateOfCheckBox(String state) {
        if (state.equals("check")) {
            setCheckedStateOfCheckBox();
        } else if (state.equals("uncheck")) {
            setUncheckedStateOfCheckBox();
        } else {
            logger.info("Input state for checkBox is undefined");
        }
        return this;
    }

    public EditPostPage clickOnSaveUpdates() {
        clickOnElement(buttonSaveUpdates);
        return this;
    }

    public EditPostPage checkIsSuccessPostUpdateMessageVisible(){
        isElementDisplayed(messageSuccessEditPost);
        return this;
    }

    public MyProfilePage redirectOnProfilePage(){
        return new MyProfilePage(webDriver);
    }

    public HeaderElement getHeader() {
        return new HeaderElement(webDriver);
    }
}
