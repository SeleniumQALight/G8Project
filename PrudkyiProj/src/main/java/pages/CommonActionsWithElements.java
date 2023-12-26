package pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class CommonActionsWithElements {
    protected WebDriver webDriver;
    protected Logger logger = Logger.getLogger(getClass());

    public CommonActionsWithElements(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);// ініціалізує всі елементи опираючись на анотації @FindBy

    }

    protected void enterTextIntoInputLogin(WebElement input, String text) {
        try {
            input.clear();
            input.sendKeys(text);
            logger.info(text + " was inputted into input" + getElementName(input));
        } catch (Exception e) {
            logger.error("Can not work with element");
            Assert.fail("Can not work with element");
        }
    }

    private String getElementName(WebElement element) {
        try {
            return element.getAccessibleName();
        } catch (Exception e) {
            return "";
        }
    }

        protected void clickOnElement (WebElement element){
            try {
                String elementName = getElementName(element);
                element.click();
                logger.info("Element was clicked" + elementName);
            } catch (Exception e) {
                logger.error("Can not work with element" + getElementName(element));
                Assert.fail("Can not work with element");
            }
        }

        protected boolean isElementDisplayed (WebElement element){
            try {
                boolean state = element.isDisplayed();
                logger.info(state + " is element displayed");
                return state;
            } catch (Exception e) {
                logger.error("Can not work with element");
                return false;
            }
        }
    }
