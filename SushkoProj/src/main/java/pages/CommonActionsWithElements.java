package pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CommonActionsWithElements {
    protected WebDriver webDriver;
    protected Logger logger = Logger.getLogger(getClass());

    public CommonActionsWithElements(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    protected void enterTextIntoInput(WebElement input, String text) {
        try{
            input.clear();
            input.sendKeys(text);
            logger.info(text + " was inputted into input");
        }catch (Exception e){
            logger.error("Can not work with element");
            Assert.fail("Can not work with element");
        }
    }

    protected void clickOnElement(WebElement element) {
        try{
            element.click();
            logger.info("Element was clicked");
        }catch (Exception e){
            logger.error("Can not work with element");
            Assert.fail("Can not work with element");
        }
    }

    protected boolean isElementDisplayed(WebElement element) {
        try{
            boolean state = element.isDisplayed();
            logger.info("Element is displayed -> " + state);
            return state;
        }catch (Exception e){
            logger.info("Element is displayed -> false");
            return false;
        }
    }

}
