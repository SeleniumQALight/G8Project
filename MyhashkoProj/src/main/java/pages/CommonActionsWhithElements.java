package pages;

import libs.ConfigProvider;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.Keys;

import java.time.Duration;

public class CommonActionsWhithElements {
    protected WebDriver webDriver;
    protected Logger logger = Logger.getLogger(getClass());
    protected WebDriverWait webDriverWait5, webDriverWait15;

    public CommonActionsWhithElements(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this); // ініціалізує всі елементи сторінки опираючись на @FindBy
        webDriverWait5 = new WebDriverWait(webDriver, Duration.ofSeconds(ConfigProvider.configProperties.TIME_FOR_DEFAULT_WAIT()));
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
        logger.error("Can not work with element" + e);
        Assert.fail("Can not work with element" + e);
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
            webDriverWait5.until(ExpectedConditions.elementToBeClickable(element));
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
    // select Text in DropDown
    protected void selectTextInDropDown(WebElement dropDown, String text) {
        try {
            Select select = new Select(dropDown);
            select.selectByVisibleText(text);
            logger.info(text + " was selected in DropDown" + getElementName(dropDown));
        }
            catch (Exception e) {
                printErrorAndStopTest(e);
            }
        }

    // select Value in DropDown
    protected void selectValueInDropDown(WebElement dropDown, String value) {
        try {
            Select select = new Select(dropDown);
            select.selectByValue(value);
            logger.info(value + " was selected in DropDown" + getElementName(dropDown));
        }
            catch (Exception e) {
                printErrorAndStopTest(e);
            }
        }
    protected void checkIsElementVisible(WebElement webElement) {
        Assert.assertTrue("Element is not visible", isElementDisplayed(webElement));
    }

    // check text in element

    protected void checkTextInElement(WebElement element, String expectedText) {
        try {
            String textFromElement = element.getText();
            Assert.assertEquals("Text in element not matched", expectedText, textFromElement);
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }
    protected void setCheckBoxIsThisPostUniqueChecked(WebElement checkBoxIsSelected) {
        if (!checkBoxIsSelected.isSelected()) {
            checkBoxIsSelected.click();
            logger.info("CheckBoxIsThisPostUnique was checked");
        } else {
            logger.info("CheckBoxIsThisPostUnique is already checked");
        }
    }

    protected void setCheckBoxIsThisPostUniqueUnchecked(WebElement checkBoxIsSelected) {
        if (checkBoxIsSelected.isSelected()) {
            checkBoxIsSelected.click();
            logger.info("CheckBoxIsThisPostUnique was unchecked");
        } else {
            logger.info("CheckBoxIsThisPostUnique is already unchecked");
        }
    }
    protected void setStatusOfCheckBoxIsThisPostUnique(WebElement checkBoxIsSelected, String checked) {
        switch (checked) {
            case "check":
                setCheckBoxIsThisPostUniqueChecked(checkBoxIsSelected);
                break;
            case "uncheck":
                setCheckBoxIsThisPostUniqueUnchecked(checkBoxIsSelected);
                break;
            default:
                logger.error("CheckBoxIsThisPostUnique should be check or unchecked");
                Assert.fail("CheckBoxIsThisPostUnique should be check or unchecked");
        }
    }

    public void pressTabKey(int numberOfTabs) {
        Actions actions = new Actions(webDriver);
        for (int i = 0; i < numberOfTabs; i++) {
            actions.sendKeys(Keys.TAB).build().perform();
        }
        logger.info("Tab key was pressed " + numberOfTabs + " times");
    }

    public void enterTextIntoField(String textForField) {
        Actions actions = new Actions(webDriver);
        actions.sendKeys(textForField).build().perform();
        logger.info(textForField + " was entered into field");
    }

    public void pressEnterKey() {
        Actions actions = new Actions(webDriver);
        actions.sendKeys(Keys.ENTER).build().perform();
        logger.info("Enter key was pressed");
    }
}
