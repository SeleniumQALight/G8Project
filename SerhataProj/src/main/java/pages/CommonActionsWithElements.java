package pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
        PageFactory.initElements(webDriver, this);//ініціалізує всі елементи сторінки опираючись на @FindBy
        webDriverWait10 = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        webDriverWait15 = new WebDriverWait(webDriver, Duration.ofSeconds(15));
    }

    protected void enterTextInToInput(WebElement element, String text) {
        try {
            element.clear();
            element.sendKeys(text);
            logger.info(text + " was inputted into input " + getElementName(element));
        } catch (Exception e) {
            logger.error("Can not work with element");
            Assert.fail("Can not work with element");
        }
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
            logger.error("Can not work with element");
            Assert.fail("Can not work with element");
        }
    }

    protected boolean isElementDisplayed(WebElement element) {
        try {
            boolean state = element.isDisplayed();
            logger.info(state + " Element is displayed " + getElementName(element));
            return state;
        } catch (Exception e) {
            logger.info("Element is not displayed " + getElementName(element));
            return false;
        }
    }

    // select Text in dropDown
    protected void selectTextInDropDown(WebElement dropDown, String text) {
        try {
            Select select = new Select(dropDown);
            select.selectByVisibleText(text);
            logger.info(text + " was selected in DropDown " + getElementName(dropDown));
        }
        catch (Exception e) {
            logger.error("Can not work with element");
            Assert.fail("Can not work with element");
        }
    }

    // select Value in dropDown
    protected void selectValueInDropDown(WebElement dropDown, String value) {
        try {
            Select select = new Select(dropDown);
            select.selectByValue(value);
            logger.info(value + " was selected in DropDown " + getElementName(dropDown));
        }
        catch (Exception e) {
            logger.error("Can not work with element");
            Assert.fail("Can not work with element");
        }
    }

    protected void checkIsElementVisible(WebElement webElement) {
        Assert.assertTrue("Element is not visible", isElementDisplayed(webElement));
    }

    //check text in element
    protected void checkTextInElement(WebElement element, String expectedText) {
        try {
            String textFromElement = element.getText();
            Assert.assertEquals("Text in element not matched", expectedText, textFromElement);
        } catch (Exception e) {
            logger.error("Can not work with element");
            Assert.fail("Can not work with element");
        }
    }

    protected void checkIsElementNotVisible(WebElement webElement) {
        Assert.assertFalse("Element is visible", isElementDisplayed(webElement));
    }

    protected void selectCheckbox(WebElement checkbox, String elementName) {
        try {
            if (!checkbox.isSelected()) {
                checkbox.click();
                logger.info(elementName + " was selected");
            } else {
                logger.info(elementName + " is already selected.");
            }
        } catch (Exception e) {
            logger.error("Can not work with checkbox element");
            Assert.fail("Can not work with checkbox element");
        }
    }

    protected void unselectCheckbox(WebElement checkbox, String elementName) {
        try {
            if (checkbox.isSelected()) {
                checkbox.click();
                logger.info(elementName + " was unselected");
            } else {
                logger.info(elementName + " is already unselected.");
            }
        } catch (Exception e) {
            logger.error("Can not work with checkbox element");
            Assert.fail("Can not work with checkbox element");
        }
    }

    protected void setCheckboxState(WebElement checkbox, String elementName, String state) {
        try {
            if (state.toLowerCase().equals("check")) {
                selectCheckbox(checkbox, elementName);
            } else if (state.toLowerCase().equals("uncheck")) {
                unselectCheckbox(checkbox, elementName);
            }
        } catch (Exception e) {
            logger.error("Can not work with checkbox element");
            Assert.fail("Can not work with checkbox element");
        }
    }

    public void openNewTabInBrowser(){
        try{
            ((JavascriptExecutor)webDriver).executeScript("window.open()");
            logger.info("New tab was opened");
        }catch (Exception e){
            logger.error("Can not open new tab");
            Assert.fail("Can not open new tab");
        }
    }

    public void closeTabAndSwitchToMainPage() {
        try {
            webDriver.close();
            switchToTabInBrowser(0);
            logger.info("Tab was closed and switched to main page");
        } catch (Exception e) {
            logger.error("Can not close tab and switch to main page");
            Assert.fail("Can not close tab and switch to main page");
        }
    }

    public void switchToTabInBrowser(int i) {
        try {
            webDriver.switchTo().window(webDriver.getWindowHandles().toArray()[i].toString());
            logger.info("Switched to tab with index " + i);
        } catch (Exception e) {
            logger.error("Can not switch to tab with index " + i);
            Assert.fail("Can not switch to tab with index " + i);
        }
    }

    public void refreshPage() {
        try {
            webDriver.navigate().refresh();
            logger.info("Page was refreshed");
        } catch (Exception e) {
            logger.error("Can not refresh page");
            Assert.fail("Can not refresh page");
        }
    }

    public void pressTabKeyNTimes(int n) {
        try{
            Actions actions = new Actions(webDriver);
            for (int i = 0; i < n; i++) {
                actions.sendKeys(Keys.TAB).build().perform();
            }
            logger.info("Tab key was pressed " + n + " times");
        }catch (Exception e){
            logger.error("Can not work with element");
            Assert.fail("Can not work with element");
        }
    }

    public void pressEnterKey() {
        try{
            Actions actions = new Actions(webDriver);
            actions.sendKeys(Keys.ENTER).build().perform();
            logger.info("Enter key was pressed");
        }catch (Exception e){
            logger.error("Can not work with element");
            Assert.fail("Can not work with element");
        }
    }
}
