package pages;

import libs.ConfigProvider;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class CommonActionsWithElements {
    protected WebDriver webDriver;
    protected Logger logger = Logger.getLogger(getClass());
    protected WebDriverWait webDriverWait05, webDriverWait15;

    public CommonActionsWithElements(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this); // initialize all elements
        webDriverWait05 = new WebDriverWait(webDriver, Duration.ofSeconds(ConfigProvider.configProperties.TIME_FOR_DEFAULT_WAIT()));
        webDriverWait15 = new WebDriverWait(webDriver, Duration.ofSeconds(ConfigProvider.configProperties.TIME_FOR_EXPLICIT_WAIT_LOW()));
    }

    protected void enterTextIntoInput(WebElement input, String text) {
        try {
            input.clear();
            input.sendKeys(text);
            logger.info(text + " was inputted into input" + getElementName(input));
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    private void printErrorAndStopTest(Exception e) {
        logger.error("Can not work with element " + e);
        Assert.fail("Can not work with element " + e);
    }

    private String getElementName(WebElement webElement) {
        try {
            return webElement.getAccessibleName();
        } catch (Exception e) {
            return "";
        }
    }

    protected void clickOnElement(WebElement element) {
        try {
            webDriverWait05.until(ExpectedConditions.elementToBeClickable(element));
            String elementName = getElementName(element);
            element.click();
            logger.info("Element was clicked " + elementName);
        } catch (Exception e) {

        }
    }

    protected void clickOnElement(String locator) {
        try {
            clickOnElement(webDriver.findElement(By.xpath(locator)));
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected boolean isElementDisplayed(WebElement element) {
        try {
            boolean state = element.isDisplayed();
            logger.info("Element " + getElementName(element) + " is element displayed --> " + state);
            return state;
        } catch (Exception e) {
            logger.info("Element is not displayed");
            return false;
        }
    }

    protected WebElement findElementByXpath(String xpath) {
        By locator = org.openqa.selenium.By.xpath(xpath);
        return findElementByLocator(locator);
    }

    protected WebElement findElementByLocator(By locator) {
        try {
            WebElement element = webDriver.findElement(locator);
            return element;
        } catch (Exception e) {
            return null;
        }
    }

    //select Text in dropdown
    protected void selectTextInDropDown(WebElement dropDown, String text) {
        try {
            Select select = new Select(dropDown);
            select.selectByVisibleText(text);
            logger.info(text + " was selected in dropdown " + getElementName(dropDown));
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    //select Value in dropdown
    protected void selectValueInDropDown(WebElement dropDown, String value) {
        try {
            Select select = new Select(dropDown);
            select.selectByValue(value);
            logger.info(value + " was selected in dropdown " + getElementName(dropDown));
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected void checkIsElementVisible(WebElement WebElement) {
        Assert.assertTrue("Element is not visible", isElementDisplayed(WebElement));
    }

    protected void checkIsElementVisible(By locator) {

        WebElement element = findElementByLocator(locator);
        Assert.assertTrue("Element is not visible", isElementDisplayed(element));
    }


    protected void checkIsElementNotVisible(WebElement webElement) {
        Assert.assertFalse("Element is visible", isElementDisplayed(webElement));
    }

    protected void checkTextInElement(WebElement element, String expectedText) {
        try {
            String textFromElement = element.getText();
            Assert.assertEquals("Text in element not matched", expectedText, textFromElement);
        } catch (Exception e) {
            logger.error("Can not get text from element " + e);
            Assert.fail("Can not get text from element " + e);
        }
    }
}

