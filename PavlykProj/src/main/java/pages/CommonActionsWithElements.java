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
    protected WebDriverWait webDriverWait05, webDriverWait15;

    public CommonActionsWithElements(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this); // инициализирует все элементы на странице отмеченные аннотацией @FindBy
        webDriverWait05 = new WebDriverWait(webDriver, Duration.ofSeconds(ConfigProvider.configProperties.TIME_FOR_DEFAULT_WAIT()));
        webDriverWait15 = new WebDriverWait(webDriver, Duration.ofSeconds(ConfigProvider.configProperties.TIME_FOR_EXPLICIT_WAIT_LOW()));
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
            //webDriverWait10.until(e->element.isDisplayed());
            webDriverWait05.withMessage("Element is not clickable").until(ExpectedConditions.elementToBeClickable(element));
            String elementName = getElementName(element);
            element.click();
            logger.info("Element was clicked " + elementName);
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected void clickOnElement (String locator) {
        try {
            clickOnElement(webDriver.findElement(By.xpath(locator)));
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected boolean isElementDisplayed(WebElement element) {
        try {
            boolean state = element.isDisplayed();
            logger.info("Element " + getElementName(element) + " is displayed (" + state + ")");
            return state;
        } catch (Exception e) {
            logger.info("Element is not displayed (false) "  + e);
            return false;
        }
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

    protected void checkIsElementVisible(WebElement webElement) {
        Assert.assertTrue("Element is not visible", isElementDisplayed(webElement));
    }

    protected void checkIsElementNotVisible(WebElement webElement) {
        Assert.assertFalse("Element is visible", isElementDisplayed(webElement));
    }

    //check text in element
    protected void checkTextInElement(WebElement element, String expectedText) {
        try {
            String textFromElement = element.getText();
            Assert.assertEquals("Text in element not matched", expectedText, textFromElement);
            logger.info("Text '" + expectedText + "' in element matched");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected void checkCheckbox(WebElement checkbox) {
        try {
            if (!checkbox.isSelected()) {
                checkbox.click();
                logger.info("Checkbox was checked");
            } else {
                logger.info("Checkbox is already checked");
            }
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected void uncheckCheckbox(WebElement checkbox) {
        try {
            if (checkbox.isSelected()) {
                checkbox.click();
                logger.info("Checkbox was unchecked");
            } else {
                logger.info("Checkbox is already unchecked");
            }
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected void  setCheckboxState(WebElement checkbox, String state) {
        if (state.equals("check")) {
            checkCheckbox(checkbox);
        } else if (state.equals("uncheck")) {
            uncheckCheckbox(checkbox);
        } else {
            logger.error("State should be 'check' or 'uncheck'");
            Assert.fail("State should be 'check' or 'uncheck'");
        }
    }

    //press button ENTER on keyboard using Actions class
    // can be used in tests directly
    public void pressEnterKey(){
        try{
            Actions actions = new Actions(webDriver);
            actions.sendKeys(Keys.ENTER).build().perform();
            logger.info("Enter key was pressed");
        }catch (Exception e){
            printErrorAndStopTest(e);
        }
    }

    /**
     * Enter text into input using Actions class
     * Just enter text without specify element
     * Сan be used in tests directly
     * @param text
     */
    public void enterTextIntoInputActions(String text) {
        try {
            Actions actions = new Actions(webDriver);
            actions.sendKeys(text).build().perform();
            logger.info(text + " was inputted ");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    //press button TAB on keyboard using Actions class
    // can be used in tests directly
    public void pressTabKey(int iterations){
        try{
            Actions actions = new Actions(webDriver);
            for (int i = 0; i < iterations; i++) {
                actions.sendKeys(Keys.TAB).build().perform();
                logger.info("Tab key was pressed");
            }
        }catch (Exception e){
            printErrorAndStopTest(e);
        }
    }

    /**
     * Press key on keyboard using Actions class
     * Сan be used in tests directly
     * @param key
     */
    public void pressKey(Keys key){
        try{
            Actions actions = new Actions(webDriver);
            actions.sendKeys(key).build().perform();
            logger.info("Tab key was pressed");
        }catch (Exception e){
            printErrorAndStopTest(e);
        }
    }

    /**
     * Open new tab in browser by javascript
     */
    public void openNewTabInBrowser(){
        try{
            ((JavascriptExecutor)webDriver).executeScript("window.open()");
            logger.info("New tab was opened");
        }catch (Exception e){
            logger.error("Can not open new tab " + e);
            Assert.fail("Can not open new tab " + e);
        }
    }

    /**
     * Switch to tab in browser by index
     */
    public void switchToTabInBrowser(int tabIndex) {
        try {
            webDriver.switchTo().window(webDriver.getWindowHandles().toArray()[tabIndex].toString());
            logger.info("Switched to tab with index " + tabIndex);
        } catch (Exception e) {
            logger.error("Can not switch to tab with index " + tabIndex + " " + e);
            Assert.fail("Can not switch to tab with index " + tabIndex + " " + e);
        }
    }

    /**
     * refresh page
     */
    public void refreshPage() {
        try {
            webDriver.navigate().refresh();
            logger.info("Page was refreshed");
        } catch (Exception e) {
            logger.error("Can not refresh page " + e);
            Assert.fail("Can not refresh page " + e);
        }
    }

    /**
     * close tab and switch to main page
     */
    public void closeTabAndSwitchToMainPage() {
        try {
            webDriver.close();
            switchToTabInBrowser(0);
            logger.info("Tab was closed and switched to main page");
        } catch (Exception e) {
            logger.error("Can not close tab and switch to main page " + e);
            Assert.fail("Can not close tab and switch to main page " + e);
        }
    }
}
