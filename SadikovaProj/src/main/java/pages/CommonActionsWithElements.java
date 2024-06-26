package pages;

import libs.ConfigProvider;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CommonActionsWithElements {
    protected WebDriver webDriver;

    protected Logger logger = Logger.getLogger(getClass());
    protected WebDriverWait webDriverWait10, webDriverWait15;

    public CommonActionsWithElements(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this); // инициализирует все элементы на странице отмеченные аннотацией @FindBy
        webDriverWait10 = new WebDriverWait(webDriver, Duration.ofSeconds(ConfigProvider.configProperties.TIME_FOR_DEFAULT_WAIT()));
        webDriverWait15 = new WebDriverWait(webDriver, Duration.ofSeconds(ConfigProvider.configProperties.TIME_FOR_EXPLICIT_WAIT_LOW()));

    }

    /**
     * Actions
     */
    private String getElementName(WebElement webElement) {
        try {
            return webElement.getAccessibleName();
        } catch (Exception e) {
            return "";
        }
    }

    protected void enterTextIntoInput(WebElement input, String text) {
        webDriverWait10.until(ExpectedConditions.elementToBeClickable(input));
        try {
            input.clear();
            input.sendKeys(text);
            logger.info(text + " was inputted into input" + getElementName(input));
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    private void printErrorAndStopTest(Exception e) {
        logger.error("Can not work with element");
        Assert.fail("Can not work with element");
    }

    protected void clickOnElement(WebElement element) {
        try {
            webDriverWait10.until(ExpectedConditions.elementToBeClickable(element));
            String elementName = getElementName(element);
            element.click();
            logger.info("Element was clicked " + elementName);
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected void clickOnElement(String locator) {
        try {
            clickOnElement(webDriver.findElement(By.xpath(locator)));
        } catch (Exception e) {
            logger.error("Can not work with locator");
            Assert.fail("Can not work with locator");
        }
    }

    public void goToWebPage(String url) {
        try {
            webDriver.get(url);
            logger.info("Page was opened: " + url);
        } catch (Exception e) {
            logger.error("Can not open page: " + url);
            Assert.fail("Can not open page");
        }
    }

    /**
     * Checkers
     */
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

    protected void selectCheckbox(WebElement element) {
        try {
            if (!element.isSelected()) {
                element.click();
                logger.info("Element " + getElementName(element) + " was clicked. Checkbox is selected");
            } else {
                logger.info("Element " + getElementName(element) + " is selected");
            }

        } catch (Exception e) {
            logger.error("Error: " + e);
        }
    }

    protected void unselectCheckbox(WebElement element) {
        try {
            if (element.isSelected()) {
                element.click();
                logger.info("Element " + getElementName(element) + " was clicked. Checkbox is unselected");
            } else {
                logger.info("Element wasn't " + getElementName(element) + " clicked. Checkbox is unselected");
            }

        } catch (Exception e) {
            logger.error("Error: " + e);
        }
    }

    protected void checkElementIsNotDisplayed(WebElement element) {
        Assert.assertFalse("Element is not visible", isElementDisplayed(element));
    }

    protected void checkIsElementVisible(WebElement webElement) {
        Assert.assertTrue("Element is visible", isElementDisplayed(webElement));
    }


    // select Text in dropDown
    protected void selectTextInDropDown(WebElement dropDown, String text) {
        try {
            Select select = new Select(dropDown);
            select.selectByVisibleText(text);
            logger.info(text + " was selected in DropDown" + getElementName(dropDown));
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    // select Value in dropDown
    protected void selectValueInDropDown(WebElement dropDown, String value) {
        try {
            Select select = new Select(dropDown);
            select.selectByValue(value);
            logger.info(value + " was selected in DropDown" + getElementName(dropDown));
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    public void checkTextInElement(WebElement element, String expectedText) {
        checkIsElementVisible(element);
        String actualText = element.getText();
        Assert.assertEquals(actualText, expectedText);
        logger.info("Text visible: " + expectedText);

    }

    public void selectCheckbox(WebElement element, String exectedState) {
        if (exectedState.equals("check")) {
            selectCheckbox(element);
        } else {
            if (exectedState.equals("uncheck")) {
                unselectCheckbox(element);

            }
        }

    }

    public void pressTabKey() {
        Actions actions = new Actions(webDriver);
        actions.sendKeys(Keys.TAB).build().perform();
        logger.info("Tab pressed");
    }

    public void enterTextWithKeys(WebElement element, String text) {
        element.sendKeys(Keys.CONTROL + "a");
        element.sendKeys(Keys.DELETE);
        element.sendKeys(text);
        logger.info("Filled field: " + text);
    }

    public void pressEnter(WebElement element) {
        webDriverWait10.until(ExpectedConditions.elementToBeClickable(element));
        element.sendKeys(Keys.ENTER);
        logger.info("Enter key pressed on the element");
    }


}
