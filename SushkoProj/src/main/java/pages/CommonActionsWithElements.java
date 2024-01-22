package pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;

public class CommonActionsWithElements {
    protected WebDriver webDriver;
    protected Logger logger = Logger.getLogger(getClass());
    protected WebDriverWait webDriverWait10, webDriverWait15;

    public CommonActionsWithElements(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this); // ініціалізує всі елементи сторінки опираючись на @FindBy
        webDriverWait10 = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        webDriverWait15 = new WebDriverWait(webDriver, Duration.ofSeconds(15));
    }

    protected void enterTextIntoInput(WebElement input, String text) {
        try{
            input.clear();
            input.sendKeys(text);
            logger.info(text + " was inputted into input" + getElementName(input));
        }catch (Exception e){
            logger.error("Can not work with element");
            Assert.fail("Can not work with element");
        }
    }

    private String getElementName(WebElement webElement) {
        try {
            return webElement.getAccessibleName();
        }catch (Exception e){
            return "";
        }
    }

    protected void clickOnElement(WebElement element) {
        try{
            webDriverWait10.until(ExpectedConditions.elementToBeClickable(element));
            String elementName = getElementName(element);
            element.click();
            logger.info("Element was clicked " + elementName);
        }catch (Exception e){
            logger.error("Can not work with element");
            Assert.fail("Can not work with element");
        }
    }

    protected void clickOnElement(String locator){
        try {
            clickOnElement(webDriver.findElement(By.xpath(locator)));
        } catch (Exception e) {
            logger.error("Can not work with element: " + locator);
            Assert.fail("Can not work with element: " + locator);
        }
    }

    protected boolean isElementDisplayed(WebElement element) {
        try{
            boolean state = element.isDisplayed();
            logger.info("Element " + getElementName(element) + " is displayed -> " + state);
            return state;
        }catch (Exception e){
            logger.info("Element is displayed -> false");
            return false;
        }
    }

    // select Text in dropDown
    protected void selectTextInDropDown(WebElement dropDown, String text) {
        try {
            Select select = new Select(dropDown);
            select.selectByVisibleText(text);
            logger.info(text + " was selected in DropDown " + getElementName(dropDown));
        }catch (Exception e){
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
        }catch (Exception e){
            logger.error("Can not work with element");
            Assert.fail("Can not work with element");
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
        try{
            String textFromElement = element.getText();
            Assert.assertEquals("Text in element not matched", expectedText, textFromElement);
        }catch (Exception e){
            logger.error("Can not work with element");
            Assert.fail("Can not work with element");
        }
    }

    public boolean isCheckboxSelected(WebElement checkbox){
        boolean checkboxStatus = checkbox.isSelected();
        return checkboxStatus;
    }

    public void setCheckboxSelected(WebElement checkbox){
        if (!isCheckboxSelected(checkbox)) {
            checkbox.click();
            logger.info("Checkbox is selected");

        } else {
            logger.info("Checkbox already was selected");
        }
    }

    public void setCheckboxUnSelected(WebElement checkbox){
        if (isCheckboxSelected(checkbox)) {
            checkbox.click();
            logger.info("Checkbox is unselected");

        } else {
            logger.info("Checkbox already was unselected");
        }
    }

    public void setStatusForCheckbox(WebElement checkbox, String checkboxStatus){
        if (checkboxStatus.equals("check")) {
            setCheckboxSelected(checkbox);
        } else if (checkboxStatus.equals("uncheck")) {
            setCheckboxUnSelected(checkbox);
        } else {
            logger.info("Wrong status for checkbox is passed");
            Assert.fail("Wrong status for checkbox is passed");
        }
    }

    public void openNewTabWithJS(){
        try {
            ((JavascriptExecutor) webDriver).executeScript("window.open()");
            logger.info("New tab was opened");
        }catch (Exception e){
            logger.info("New tab was not opened");
            Assert.fail("New tab was not opened");
        }
    }

    public void switchToTab(int num_of_tab){
        try {
            ArrayList<String> tabs = new ArrayList<String>(webDriver.getWindowHandles());
            webDriver.switchTo().window(tabs.get(num_of_tab));
            logger.info("Switched to tab №" + num_of_tab);
        }catch (Exception e){
            logger.info("Can not switched to tab №" + num_of_tab);
            Assert.fail("Can not switched to tab №" + num_of_tab);
        }
    }

    public void switchToMainTab(){
        switchToTab(0);
    }

    public void closeTab(int num_of_tab){
        try {
            switchToTab(num_of_tab);
            webDriver.close();
            logger.info("Tab was closed");
        }catch (Exception e){
            logger.info("Tab was not closed");
            Assert.fail("Tab was not closed");
        }
    }

    public void closeTabAndSwitchToMainTab(int num_of_tab){
        closeTab(num_of_tab);
        switchToMainTab();
    }

    public void refreshPage() {
        try {
            webDriver.navigate().refresh();
            logger.info("Page was refreshed");
        }catch (Exception e) {
            logger.error("Page was not refreshed");
            Assert.fail("Page was not refreshed");
        }
    }

    public void pressTheTabKey() {
        try {
            Actions actions = new Actions(webDriver);
            actions.sendKeys("\t").build().perform();  // "\t" - це символ табуляції
            logger.info("Tab was clicked");
        }catch (Exception e){
            logger.info("Tab was not clicked");
            Assert.fail("Tab was not clicked");
        }
    }

    public void pressTheEnterKey(WebElement webElement) {
        try {
            Actions actions = new Actions(webDriver);
            actions.sendKeys(webElement, "\n").build().perform();  // "\n" - це символ нового рядка (Enter)
            logger.info("Enter was clicked");
        }catch (Exception e){
            logger.info("Enter was not clicked");
            Assert.fail("Enter was not clicked");
        }
    }

    public void enterTextWithoutGettingElement(String input){
        try {
            Actions actions = new Actions(webDriver);
            actions.sendKeys(input);
            actions.build().perform();
            logger.info("Text was entered");
        } catch (Exception e){
            logger.info("Text was not entered");
            Assert.fail("Text was not entered");
        }
    }

    public void tabToElementWithActions(WebElement webElement) {
        try {
            Actions actions = new Actions(webDriver);
            actions.sendKeys(webElement, "\t").build().perform();

            // Очікувати, доки елемент стане активним
            actions.click(webElement).perform();

            logger.info("Tabbed to the element: " + getElementName(webElement));
        } catch (Exception e) {
            logger.info("Failed to tab to the element: " + getElementName(webElement));
            Assert.fail("Failed to tab to the element: " + getElementName(webElement));
        }
    }
}
