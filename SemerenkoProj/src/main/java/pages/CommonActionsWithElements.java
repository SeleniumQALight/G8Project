package pages;

import libs.ConfigProvider;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.Set;

public class CommonActionsWithElements {
    protected WebDriver webDriver;
    protected Logger logger = Logger.getLogger(getClass());
    protected WebDriverWait webDriverWait10, webDriverWait15;
    protected JavascriptExecutor javascriptExecutor = (JavascriptExecutor) webDriver;

    public CommonActionsWithElements(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        webDriverWait10 = new WebDriverWait(webDriver, Duration.ofSeconds(ConfigProvider.configProperties.TIME_FOR_DEFAULT_WAIT()));
        webDriverWait15 = new WebDriverWait(webDriver, Duration.ofSeconds(ConfigProvider.configProperties.TIME_FOR_EXPLICIT_WAIT_LOW()));
    }


    protected Set<String> openNewTab() {
        String currentTab = webDriver.getWindowHandle();
        logger.info("currentTab: " + currentTab);
        javascriptExecutor.executeScript("window.open()");
        Set<String> tabSet = webDriver.getWindowHandles();
        for (String tabName : tabSet
        ) {
            logger.info("tab: " + tabName);
        }
        return tabSet;
    }

    protected void switchBetweenTabs(int tabNumber, Set<String> tabSet) {
        if (tabNumber <= tabSet.size() && tabNumber > 0) {
            int counter = 1;
            for (String tabName : tabSet
            ) {
                if (counter == tabNumber) {
                    webDriver.switchTo().window(tabName);
                    logger.info("webDriver switched on tab " + tabName);
                }
                counter++;
            }
        } else {
            logger.info("Tab number " + tabNumber + " not exist. Count of tab: " + tabSet.size());
        }
    }

    protected void closeTabs() {
        String tabName = webDriver.getWindowHandle();
        webDriver.close();
        logger.info("Tab " + tabName + " was closed");
    }

    protected void refreshPages() {
        String url = webDriver.getCurrentUrl();
        webDriver.navigate().refresh(); // refresh page
        logger.info("page " + url + " was refreshed");
    }

    protected void enterTextIntoInput(WebElement element, String text) {
        try {
            element.clear();
            element.sendKeys(text);
            logger.info(text + " was inputted into input " + getElementName(element));
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    private void printErrorAndStopTest(Exception e) {
        logger.error("Can not work with element " + e);
        Assert.fail("Can not work with element " + e);
    }

    protected WebElement getElement(String locator, String param) {
        try {
            return webDriver.findElement(By.xpath(String.format(locator, param)));
        } catch (Exception e) {
            return null;
        }
    }

    protected String getElementName(WebElement webElement) {
        try {
            return webElement.getAccessibleName();
        } catch (Exception e) {
            return "";
        }
    }

    protected String getElementProperty(WebElement webElement, String name) {
        try {
            return webElement.getDomProperty(name);
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
            clickOnElement(webDriver.findElement(By.xpath(String.format(locator))));
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
            logger.error("Can not work with element");
            //Assert.fail("Can not work with element");
            return false;
        }
    }

    protected boolean isElementDisplayed(WebElement element, String elementName) {
        try {
            boolean state = element.isDisplayed();
            logger.info("Element " + getElementName(element) + " is displayed -> " + state);
            return state;
        } catch (Exception e) {
            logger.error("Can not work with " + elementName);
            //Assert.fail("Can not work with element");
            return false;
        }
    }

    //select text in dropDown
    protected void selectTextInDropDown(WebElement dropDown, String text) {
        try {
            Select select = new Select(dropDown);
            select.selectByVisibleText(text);
            logger.info(text + " was selected in DropDown " + getElementName(dropDown));
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    //select value in dropDown
    protected void selectValueInDropDown(WebElement dropDown, String value) {
        try {
            Select select = new Select(dropDown);
            select.selectByValue(value);
            logger.info(value + " was selected in DropDown " + getElementName(dropDown));
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected void checkIsElementVisible(WebElement webElement) {
        Assert.assertTrue("Element is not visible", isElementDisplayed(webElement));
    }

    protected void checkIsElementVisible(WebElement webElement, String elementName) {
        Assert.assertTrue("Element " + elementName + " is not visible", isElementDisplayed(webElement, elementName));
    }

    protected void checkIsElementUnvisible(WebElement webElement, String elementName) {
        Assert.assertFalse("Element " + elementName + " is visible", isElementDisplayed(webElement, elementName));
    }

    //check text in element
    protected void checkTextInElement(WebElement element, String expectedText) {
        try {
            String textFromElement = element.getText();
            Assert.assertEquals("Text in element not matched", expectedText, textFromElement);
            logger.info("Text in element matched");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }
}
