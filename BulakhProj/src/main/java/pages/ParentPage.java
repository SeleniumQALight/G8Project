package pages;

import libs.ConfigProvider;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;


import java.util.Set;


//все загальні методи для сторінок
abstract public class ParentPage extends CommonActionsWithElements{
    final String baseUrl = ConfigProvider.configProperties.base_url();

    //конструктор
    public ParentPage(WebDriver webDriver) {
        super(webDriver);
    }



    //метод для отримання частини url
    abstract protected String getRelativeUrl();

    // метод для перевірки чи відкрилась потрібна сторінка
    protected void checkUrl() {
        Assert.assertEquals("Invalid page"
                , baseUrl + getRelativeUrl()
                , webDriver.getCurrentUrl()

        );
    }
    // метод для перевірки чи відкрилась потрібна сторінка по патерну
    ////https://aqa-complexapp.onrender.com/post/64d21e84903640003414c338
    //// regex for 64d21e84903640003414c338
    //// [a-zA-Z0-9]{24}
    ////https://aqa-complexapp.onrender.com/post/[a-zA-Z0-9]
    protected void checkUrlWithPattern() {
        Assert.assertTrue("Invalid page \n"
                +"Expected result: " + baseUrl + getRelativeUrl() + "\n"
                +"Actual result: " + webDriver.getCurrentUrl()
                        , webDriver.getCurrentUrl()
                .matches(baseUrl + getRelativeUrl())
                );

    }

    public void openNewTab() {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) webDriver;
        if (jsExecutor != null) {
            jsExecutor.executeScript("window.open();");
        } else {
            System.out.println("JavascriptExecutor is not initialized.");
        }
    }


    public void refreshPage() {
        webDriver.navigate().refresh();
        logger.info("Page was refreshed");
    }

    public void switchToNextTab() {
        try {
            String currentHandle = webDriver.getWindowHandle();
            Set<String> handles = webDriver.getWindowHandles();
            for (String handle : handles) {
                if (!handle.equals(currentHandle)) {
                    webDriver.switchTo().window(handle);
                    return;
                }
            }
        } catch (Exception e) {
            System.out.println("Failed to switch to the next tab: " + e.getMessage());
        }
    }

    public  void switchToTheMainTab() {
        try {
            String currentHandle = webDriver.getWindowHandle();
            webDriver.switchTo().window(webDriver.getWindowHandles().toArray()[0].toString());
        } catch (Exception e) {
            System.out.println("Failed to switch to the main tab: " + e.getMessage());
        }
    }

    public  void closeTab() {
        try {
            String currentHandle = webDriver.getWindowHandle();
            webDriver.close();
        } catch (Exception e) {
            System.out.println("Failed to close the tab: " + e.getMessage());
        }
    }
}


