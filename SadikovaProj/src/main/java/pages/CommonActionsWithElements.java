package pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CommonActionsWithElements {
    protected WebDriver webDriver;
    protected Logger logger = Logger.getLogger(getClass());

    public CommonActionsWithElements(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this); // инициализирует все элементы на странице отмеченные аннотацией @FindBy
    }

    private String getElementName(WebElement webElement) {
        try {
            return webElement.getAccessibleName();
        } catch (Exception e) {
            return "";
        }
    }

    protected void enterTextIntoInput(WebElement input, String text) {
        try {
            input.clear();
            input.sendKeys(text);
            logger.info(text + " was inputted into input" + getElementName(input));
        } catch (Exception e) {
            logger.error("Can not work with element");
            Assert.fail("Can not work with element");
        }
    }

    protected void clickOnElement(WebElement element) {
        try {
            String elementName = getElementName(element);
            element.click();
            logger.info("Element was clicked " + elementName);
        } catch (Exception e) {
            logger.error("Can not work with element");
            Assert.fail("Can not work with element");
        }
    }

    protected boolean isElementDisplayed(WebElement element) {
        try {
            boolean state = element.isDisplayed();
            logger.info("Element " + getElementName(element) + " is displayed -> " + state);
            return state;
        } catch (Exception e) {
            logger.error("Element is displayed -> " + false);
            return false;
        }
    }

    protected void checkIsElementVisible(WebElement webElement) {
        Assert.assertTrue("Element is not visible", isElementDisplayed(webElement));
    }
    // select Text in dropDown
    protected void selectTextInDropDown(WebElement dropDown, String text) {
        try {
            Select select = new Select(dropDown);
            select.selectByVisibleText(text);
            logger.info(text + " was selected in DropDown" + getElementName(dropDown));
        } catch (Exception e) {
            logger.error("Can not work with element");
            Assert.fail("Can not work with element");
        }
    }

    // select Value in dropDown
    protected void selectValueInDropDown(WebElement dropDown, String value) {
        try {
            Select select = new Select(dropDown);
            select.selectByValue(value);
            logger.info(value + " was selected in DropDown" + getElementName(dropDown));
        } catch (Exception e) {
            logger.error("Can not work with element");
            Assert.fail("Can not work with element");
        }
    }

    public void checkTextInElement(WebElement element, String expectedText){
        String actualText = element.getText();
        System.out.println(actualText);
        Assert.assertEquals(actualText, expectedText);
    }
}
