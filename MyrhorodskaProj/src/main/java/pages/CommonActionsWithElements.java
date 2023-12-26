package pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class CommonActionsWithElements {
  protected   WebDriver webDriver;//save browser //доступний в нащадках і в цьому класі (protected)

    protected Logger logger = Logger.getLogger(getClass());//save logger

    public CommonActionsWithElements(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this); //ініціалізує всі елементи (передаємо вебдрайвер і цей клас) опираючись на анотації FindBy
    }

    protected void enterTextInToInput(WebElement element, String text) {
        try {
            element.clear();
            element.sendKeys(text);
            logger.info(text + " was inputted into input");
        } catch (Exception e) {
            logger.error("Can not work with element");
            Assert.fail("Can not work with element");
        }
    }

    protected void clickOnElement(WebElement element) {
        try {
            element.click();
            logger.info("Element was clicked");
        } catch (Exception e) {
            logger.error("Can not work with element");
            Assert.fail("Can not work with element");
        }
    }
   protected boolean isElementDisplayed(WebElement element) {
        try {
            boolean state = element.isDisplayed();
            if (state) {
                logger.info("Element is displayed");
                return true;
            } else {
                logger.info("Element is not displayed");
                return false;
            }
        } catch (Exception e) {
            logger.error("Can not work with element");
            Assert.fail("Can not work with element");
            return false;
        }
    }
}
