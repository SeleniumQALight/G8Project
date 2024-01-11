package pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CommonActionsWithElements {
    protected WebDriver webDriver;
    protected Logger logger = Logger.getLogger(getClass());

    public CommonActionsWithElements(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this); // ініціалізує всі елементи сторінки опираючись на @FindBy
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
            String elementName = getElementName(element);
            element.click();
            logger.info("Element was clicked " + elementName);
        }catch (Exception e){
            logger.error("Can not work with element");
            Assert.fail("Can not work with element");
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
}
