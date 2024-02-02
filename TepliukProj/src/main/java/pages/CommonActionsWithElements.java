package pages;

import libs.ConfigProvider;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
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
        PageFactory.initElements(webDriver, this); // ініціалізує всі елементи сторінки опираючись на @FindBy
        webDriverWait10 = new WebDriverWait(webDriver, Duration.ofSeconds(ConfigProvider.configProperties.TIME_FOR_DEFAULT_WAIT()));
        webDriverWait15 = new WebDriverWait(webDriver, Duration.ofSeconds((ConfigProvider.configProperties.TIME_FOR_EXPLICIT_WAIT_LOW())));
    }

    protected void enterTextIntoInput(WebElement input, String text) {
        try {
            input.clear();
            input.sendKeys(text);
            logger.info(text + " was inputted into input " + getElementName(input));
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    private void printErrorAndStopTest(Exception e) {
        printErrorAndStopTest(e);
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
            printErrorAndStopTest(e);
        }
    }


    protected boolean isElementDisplayed(WebElement element) {
        try {
            boolean state = element.isDisplayed();
            logger.info("Element " + getElementName(element) + " is displayed -> " + state);
            return state;
        } catch (Exception e) {
            logger.info("Element is displayed -> false");
            return false;
        }
    }

    //select text in dropdown
    protected void selectTextInDropDown(WebElement dropDown, String text) {
        try {
            Select select = new Select(dropDown);
            select.selectByVisibleText(text);
            logger.info(text + " was selected in Dropdown" + getElementName(dropDown));
        } catch (Exception e) {
            printErrorAndStopTest(e);

        }
    }

    //select Value in dropdown
    protected void selectValueInDropDown(WebElement dropDown, String value) {
        try {
            Select select = new Select(dropDown);
            select.selectByValue(value);
            logger.info(value + " was selected in Dropdown " + getElementName(dropDown));
        } catch (Exception e) {
            printErrorAndStopTest(e);

        }
    }

    protected void checkIsElementVisible(WebElement webElement) {
        Assert.assertTrue("Element is not visible", isElementDisplayed(webElement));
    }

    protected void checkIsElementNotVisible(WebElement webElement) {
        Assert.assertFalse("Element is visible", isElementDisplayed(webElement));
    }


    //check text is element
    protected void checkTextInElement(WebElement element, String expectedText) {
        try {
            String textFromElement = element.getText();
            Assert.assertEquals("Text in element not matched", expectedText, textFromElement);
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected void checkCheckBox(WebElement element) {
        try {
            if (!element.isSelected()) {
                element.click();
                logger.info("Checkbox was checked");
            }
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected void uncheckCheckBox(WebElement element) {
        try {
            if (element.isSelected()) {
                element.click();
                logger.info("Checkbox was unchecked");
            }
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected void setStateToCheckBox(WebElement element, String state) {
        try {
            if (state.equalsIgnoreCase("check")) {
                checkCheckBox(element);
            } else if (state.equalsIgnoreCase("uncheck")) {
                uncheckCheckBox(element);
            } else {
                logger.error("State should be 'check' or 'uncheck'");
                Assert.fail("State should be 'check' or 'uncheck'");
            }
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }


    //press button ENTER on keyboard using Actions class
    protected void pressEnterKey() {
        try {
            Actions actions = new Actions(webDriver);
            actions.sendKeys(Keys.ENTER).build().perform();
            logger.info("Enter key was pressed");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }


    public void openNewTabInBrowser() {
        try {
            if (webDriver instanceof JavascriptExecutor) {
                ((JavascriptExecutor) webDriver).executeScript("window.open()");
                logger.info("New tab was opened");
            } else {
                throw new UnsupportedOperationException("JavascriptExecutor not supported");
            }
        } catch (Exception e) {
            logger.error("Can not open new tab", e);
            Assert.fail("Can not work new tab");
        }
    }


    public void switchToNewTab(int tabNumber) {
        try {
            webDriver.switchTo().window(webDriver.getWindowHandles().toArray()[tabNumber].toString());
            logger.info("Switch to new tab");
        } catch (Exception e) {
            logger.error("Failed to switch to the new tab", e);
            Assert.fail("Failed to switch to the new tab");
        }
    }


    public void closeTab(int tabNumber) {
        try {
            webDriver.switchTo().window(webDriver.getWindowHandles().toArray()[tabNumber].toString());
            webDriver.close();
            logger.info("New tab was closed and switch to main tab");
        } catch (Exception e) {
            logger.error("Failed to close the new tab", e);
            Assert.fail("Failed to close the new tab");
        }
    }

    public void refreshPage() {
        try {
            webDriver.navigate().refresh();
            logger.info("Page was refreshed");
        } catch (Exception e) {
            logger.error("Failed to refresh the page", e);
            Assert.fail("Failed to refresh the page");
        }
    }
}