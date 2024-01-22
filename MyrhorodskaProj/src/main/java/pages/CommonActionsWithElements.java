package pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CommonActionsWithElements {
    protected WebDriver webDriver;//save browser //доступний в нащадках і в цьому класі (protected)

    protected Logger logger = Logger.getLogger(getClass());//save logger
    protected WebDriverWait webDriverWait10, webDriverWait15;

    public CommonActionsWithElements(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this); //ініціалізує всі елементи (передаємо вебдрайвер і цей клас) опираючись на анотації FindBy
        webDriverWait10 = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        webDriverWait15 = new WebDriverWait(webDriver, Duration.ofSeconds(15));

    }


    protected void enterTextInToInput(WebElement element, String text) {
        try {
            element.clear();
            element.sendKeys(text);
            logger.info(text + " was inputted into input" + getElementName(element));
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
            logger.error(" Can't work with element ");
            Assert.fail(" Can't work with element ");
        }
    }


    protected void clickOnElement (String locator) {
        try {
            clickOnElement(webDriver.findElement(By.xpath(locator)));
        } catch (Exception e) {
            logger.error(" Can't work with locator ");
            Assert.fail(" Can't work with locator ");
        }
    }

    protected boolean isElementDisplayed(WebElement element) {
        try {
            boolean state = element.isDisplayed();
            if (state) {
                logger.info("Element " + getElementName(element) + " is displayed -> " + state);
                return true;
            } else {
                logger.info("Element is not displayed ");
                return false;
            }
        } catch (Exception e) {
            logger.error("Can not work with element ");
       //     Assert.fail("Can not work with element ");
            return false;
        }
    }
    //select text in dropDown


    // select Text in dropDown
    protected void selectTextInDropDown(WebElement dropDown, String text) {
        try {
            Select select = new Select(dropDown);
            select.selectByVisibleText(text);
            logger.info(text + " was selected in DropDown " + getElementName(dropDown));
        } catch (Exception e) {
            logger.error("Can't work with element");
            Assert.fail("Can't work with element");
        }
    }

    // select Value in dropDown
    protected void selectValueInDropDown(WebElement dropDown, String value) {
        try {
            Select select = new Select(dropDown);
            select.selectByValue(value);
            logger.info(value + " was selected in DropDown " + getElementName(dropDown));
        } catch (Exception e) {
            logger.error("Can't work with element");
            Assert.fail("Can't work with element");
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
            logger.error("Can't work with element");
            Assert.fail("Can't not work with element");
        }
    }
    protected void checkIsElementNotVisible(WebElement webElement) {
        Assert.assertFalse("Element is visible", isElementDisplayed(webElement));
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
}