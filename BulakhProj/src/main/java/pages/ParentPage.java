package pages;

import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;


import static pages.LoginPage.switchToNextTab;

//все загальні методи для сторінок
abstract public class ParentPage extends CommonActionsWithElements{
    final String baseUrl = "https://aqa-complexapp.onrender.com";
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

    public static void openNewTab(WebDriver driver) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        if (jsExecutor != null) {
            jsExecutor.executeScript("window.open();");
            switchToNextTab(driver);
        } else {
            System.out.println("JavascriptExecutor is not initialized.");
        }
    }


    public void refreshPage() {
        webDriver.navigate().refresh();
        logger.info("Page was refreshed");
    }
}

